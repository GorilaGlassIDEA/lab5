package by.dima.model.data.command.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.command.model.CommandAbstract;
import by.dima.model.data.route.model.main.FillOutRouteModelUsingScanner;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;

import by.dima.model.data.route.model.main.RouteBuilder;
import lombok.Getter;
import lombok.Setter;

public class UpdateCommand extends CommandAbstract {
    @Getter
    @Setter
    private Long id;
    private final FillOutRouteModelUsingScanner routeCreator;
    private final CollectionController collectionController;

    public UpdateCommand(FillOutRouteModelUsingScanner routeCreator, CollectionController collectionController) {
        super("update {id}", "Update an element by its ID.");
        this.routeCreator = routeCreator;
        this.collectionController = collectionController;
    }

    @Override
    public void execute() {
        if (id != null) {
            Route newRoute = routeCreator.createRoute(new RouteBuilder(), id);
            collectionController.updateElem(newRoute);
        }
    }

    @Override
    public void setArgs(String arg) {
        try {
            this.id = Long.parseLong(arg);
        } catch (NumberFormatException e) {
            System.out.println(id);
            System.err.println("Invalid input! Try again!");
        }
    }
}
