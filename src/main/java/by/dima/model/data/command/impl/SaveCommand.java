package by.dima.model.data.command.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.command.model.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCommand implements Command {

    private String key = "save";
    private final CollectionController collectionController;

    public SaveCommand(CollectionController collectionController) {
        this.collectionController = collectionController;
    }

    @Override
    public void execute() {
        collectionController.saveCollection();
        System.err.println("Your changes was saving!");
    }
}
