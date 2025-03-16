package by.dima.model.data.command.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.route.model.main.FillOutRouteModelUsingScanner;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;

import by.dima.model.data.route.model.main.RouteBuilder;
import by.dima.model.service.files.io.write.WriteableFile;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import by.dima.model.service.util.GetSecondArgFromArgsUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class UpdateCommand implements Command {
    @Getter
    @Setter
    private String key = "update";
    private Long id;
    private final FillOutRouteModelUsingScanner routeCreator;
    private final CollectionController collectionController;

    public UpdateCommand(FillOutRouteModelUsingScanner routeCreator, CollectionController collectionController) {
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
