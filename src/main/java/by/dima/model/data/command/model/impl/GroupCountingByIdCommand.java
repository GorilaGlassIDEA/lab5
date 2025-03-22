package by.dima.model.data.command.model.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.command.model.model.CommandAbstract;

public class GroupCountingByIdCommand extends CommandAbstract {
    private final CollectionController collectionController;

    public GroupCountingByIdCommand(CollectionController collectionController) {
        super("group_counting_by_id", "Group collection elements by the value of the id field and display the number of elements in each group.");
        this.collectionController = collectionController;
    }

    @Override
    public void execute() {
        System.out.println(collectionController.getObjGroup().getMapGroup());
    }
}
