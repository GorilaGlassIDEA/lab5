package by.dima.model.commands;

import by.dima.model.commands.model.Command;
import by.dima.model.common.CommandDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Getter
public class CommandManager {
    private final Map<String, Command> commandMap;


    @Autowired
    public CommandManager(List<Command> commands) {
        commandMap = commands.stream()
                .collect(Collectors.toMap(
                        command -> command.getKey(),
                        command -> command
                ));
    }

    public CommandDTO execute(Command command) {
        command.execute();
        return command.getCommandDTO();
    }

}
