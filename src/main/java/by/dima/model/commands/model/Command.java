package by.dima.model.commands.model;


import javax.naming.Name;

/**
 * Этот интерфейс является абстракцией для каждой реализации команды
 */
public interface Command extends GetableCommandDTO, Nameable {
    default void execute() {
    }

    default void setArgs(String arg) {
    }
}
