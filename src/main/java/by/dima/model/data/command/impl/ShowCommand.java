package by.dima.model.data.command.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.model.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowCommand implements Command {

    private String key = "show";
    private CollectionController collectionController;

    public ShowCommand(CollectionController collectionController) {
        this.collectionController = collectionController;
    }

    @Override
    public void execute() {
        System.out.print(collectionController.getModels());
    }
}
