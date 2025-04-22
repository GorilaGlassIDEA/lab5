package by.dima.model.commands.impl;

import by.dima.model.commands.model.Command;
import by.dima.model.common.CommandDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ShowCommand implements Command {
    private String key = "show";


    @Override
    public CommandDTO getCommandDTO() {
        return new CommandDTO(key);
    }
}
