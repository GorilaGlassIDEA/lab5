package by.dima.model.commands.model;

import by.dima.model.common.CommandDTO;
import lombok.Data;

@Data
public abstract class CommandAbstract implements Command {
    private final Long userId;
    private CommandDTO commandDTO;
    private String key;
    private String arg;

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

    @Override
    public void setArgs(String arg) {
        this.arg = arg;
    }
}
