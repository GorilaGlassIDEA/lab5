package by.dima.model.commands.model;


import by.dima.model.common.CommandDTO;

import javax.naming.Name;

/**
 * Этот интерфейс является абстракцией для каждой реализации команды
 */
public interface Command extends GetableCommandDTO, Nameable {
    default void execute() {
    }

    void setCommandDTO(CommandDTO commandDTO);

    default void setArgs(String arg) {
    }
}
