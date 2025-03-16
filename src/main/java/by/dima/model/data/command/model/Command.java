package by.dima.model.data.command.model;

public interface Command extends Nameable {
    void execute();

    default void setArgs(String arg) {
        // default method for command which has more than one arg (update {id} insert {id})
    }
}
