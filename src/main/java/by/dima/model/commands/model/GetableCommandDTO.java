package by.dima.model.commands.model;

import by.dima.model.common.CommandDTO;

public interface GetableCommandDTO {

    default CommandDTO getCommandDTO() {
        return new CommandDTO();
    }
}
