package by.dima.model.data.command.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.model.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoCommand implements Command {
    private String key = "info";
    private CollectionController collectionController;

    public InfoCommand(CollectionController collectionController) {
        this.collectionController = collectionController;
    }


    @Override
    public void execute() {
        Models models = collectionController.getModels();
        if (models.sizeArray() == 0) {
            System.err.println("Your collections is Empty!\nYou can add new element between insert command!");
        } else {
            System.out.println("Type: " + models.getType());
            System.out.println("Date: " + models.getZonedDateTime());
            System.out.println("Size: " + models.sizeArray());
        }
    }
}
