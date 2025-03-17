package by.dima.model.data.command.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.command.model.CommandAbstract;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowCommand extends CommandAbstract {

    private CollectionController collectionController;

    public ShowCommand(CollectionController collectionController) {
        super("show", "Display all elements in the collection.");
        this.collectionController = collectionController;
    }

    @Override
    public void execute() {
        Models models = collectionController.getModels();
        if (models.sizeArray() == 0) {
            System.err.println("Your collections is Empty!\nYou can add new element between insert command!");
        } else {
            System.out.println(collectionController.getModels());
        }
    }
}
