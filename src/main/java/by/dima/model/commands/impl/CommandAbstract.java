package by.dima.model.commands.impl;

import by.dima.model.commands.model.Command;
import by.dima.model.common.CommandDTO;
import lombok.Data;

@Data
public abstract class CommandAbstract implements Command {
    private final Long userId;
    private CommandDTO commandDTO;
    private String key;

    public CommandAbstract(Long userId, String key) {
        this.userId = userId;
        this.key = key;
    }

    @Override
    public void execute() {
        if (commandDTO == null) {
            commandDTO = new CommandDTO();
        }
        commandDTO.setUserID(userId);
        commandDTO.setNameCommand(key);
    }
}
