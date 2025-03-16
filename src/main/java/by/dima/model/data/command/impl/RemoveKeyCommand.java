package by.dima.model.data.command.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.command.model.CommandAbstract;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveKeyCommand extends CommandAbstract {

    private final CollectionController collectionController;
    private String[] args;
    private Long removeKey;

    public RemoveKeyCommand(CollectionController collectionController) {
        super("remove_key {id}", "Remove an element by its key.");
        this.collectionController = collectionController;
    }


    @Override
    public void execute() {
        try {
            removeKey = Long.parseLong(args[0]);
            collectionController.removeElem(removeKey);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Incorrect args or you remember to write key for removing!");
        }
    }
}
