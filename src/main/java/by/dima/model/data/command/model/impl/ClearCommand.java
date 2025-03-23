package by.dima.model.data.command.model.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.command.model.model.CommandAbstract;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClearCommand extends CommandAbstract {
    private final CollectionController collectionController;

    public ClearCommand(CollectionController collectionController) {
        super("clear", "Clear command helps you with clearing collection!");
        this.collectionController = collectionController;
    }

    @Override
    public void execute() {
        collectionController.resetModels();
    }

}
