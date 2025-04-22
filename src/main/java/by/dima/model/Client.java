package by.dima.model;

import by.dima.model.commands.CommandManager;
import by.dima.model.commands.model.Command;
import by.dima.model.common.AnswerDTO;
import by.dima.model.common.CommandDTO;
import by.dima.model.logging.factory.LoggerWrapper;
import by.dima.model.parser.DeserializableAnswerDTO;
import by.dima.model.parser.RouteParserToJson;
import by.dima.model.parser.SerializableCommandDTO;
import by.dima.model.request.Clientable;
import by.dima.model.util.GetSecondArgFromArgsUtil;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.logging.*;

@Setter
@Getter
@NoArgsConstructor
@Component
public class Client {
    private Logger logger;
    private Clientable clientRequestUDP;
    private AnswerDTO answerDTO;
    private SerializableCommandDTO serializableCommandDTO;
    private DeserializableAnswerDTO deserializableAnswerDTO;
    private CommandManager manager;
    private CommandDTO commandDTO;

    @Autowired
    public Client(Logger logger, Clientable clientRequestUDP, SerializableCommandDTO serializableCommandDTO, DeserializableAnswerDTO deserializableAnswerDTO, CommandManager manager) {
        this.logger = logger;
        this.clientRequestUDP = clientRequestUDP;
        this.serializableCommandDTO = serializableCommandDTO;
        this.deserializableAnswerDTO = deserializableAnswerDTO;
        this.manager = manager;
    }

    public AnswerDTO sendCommandReceiveAnswer(String commandString) {
        String commandStringClean = GetSecondArgFromArgsUtil.getFirstArg(commandString);
        String commandArg = GetSecondArgFromArgsUtil.getSecondArg(commandString);

        if (manager.getCommandMap().containsKey(commandStringClean)) {
            if (!commandArg.isBlank()) {
                Command command = manager.getCommandMap().get(commandStringClean);
                command.setArgs(commandArg);
                commandDTO = manager.execute(command);
            } else {
                Command command = manager.getCommandMap().get(commandStringClean);
                commandDTO = manager.execute(command);
            }
            clientRequestUDP.makePost(serializableCommandDTO.serial(commandDTO));
            answerDTO = deserializableAnswerDTO.deserial(clientRequestUDP.makeGet());
            return answerDTO;
        } else {
            return new AnswerDTO("Не удалось найти команду с именем: " + commandStringClean);
        }
    }
}
