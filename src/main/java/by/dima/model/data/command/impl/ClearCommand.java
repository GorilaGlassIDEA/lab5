package by.dima.model.data.command.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.model.Command;
import by.dima.model.service.files.io.write.WriteableFile;
import lombok.Getter;
import lombok.Setter;

public class ClearCommand implements Command {
    @Getter
    @Setter
    private String key = "clear";
    private final CollectionController collectionController;

    public ClearCommand(CollectionController collectionController) {
        this.collectionController = collectionController;
    }

    @Override
    public void execute() {
        collectionController.resetModels();
    }

}
