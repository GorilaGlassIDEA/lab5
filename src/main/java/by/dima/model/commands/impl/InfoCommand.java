package by.dima.model.commands.impl;

import by.dima.model.commands.model.Command;
import by.dima.model.common.CommandDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Команда выводящая информацию по всем элементам коллекции на данный момент
 */
@Getter
@Setter
@Component
public class InfoCommand implements Command {
    private StringBuilder builder;
    private Logger logger;
    private String key = "info";

    @Override
    public CommandDTO getCommandDTO() {
        return new CommandDTO(key);
    }
}
