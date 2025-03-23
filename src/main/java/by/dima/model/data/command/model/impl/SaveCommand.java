package by.dima.model.data.command.model.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.command.model.model.CommandAbstract;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCommand extends CommandAbstract {

    private final CollectionController collectionController;

    public SaveCommand(CollectionController collectionController) {
        super("save", "Save the collection to a file.");
        this.collectionController = collectionController;
    }

    @Override
    public void execute() {
        collectionController.saveCollection();
        System.err.println("Your changes was saving!");
    }
}
