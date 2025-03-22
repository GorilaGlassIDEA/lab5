package by.dima.model.data.command.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.command.model.CommandAbstract;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveKeyCommand extends CommandAbstract {

    private final CollectionController collectionController;
    private Long arg;

    public RemoveKeyCommand(CollectionController collectionController) {
        super("remove_key", "Remove an element by its key.");
        this.collectionController = collectionController;
    }


    @Override
    public void execute() {
        collectionController.removeElem(arg);
    }

    @Override
    public void setArgs(String arg) {
        try {
            this.arg = Long.parseLong(arg);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect arg!");
        }
    }
}
