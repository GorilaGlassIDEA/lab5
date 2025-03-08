package by.dima.model.data.command.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.command.impl.creator.RouteCreator;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.add.AddableInfo;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import by.dima.model.service.generate.id.IdGenerateble;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;


@Getter
public class InsertCommand implements Command {
    @Setter
    private String key = "insert";
    private Route route;
    private long id;
    private final CollectionController collectionController;
    private final ParserToJson parser;
    private final RouteCreator routeCreator;
    private String[] args;
    private final IdGenerateble idGenerateble;

    public InsertCommand(CollectionController collectionController, ParserToJson parser, IdGenerateble idGenerateble, RouteCreator routeCreator) {
        this.collectionController = collectionController;
        this.parser = parser;
        this.routeCreator = routeCreator;
        this.idGenerateble = idGenerateble;
    }


    @Override
    public void execute() {
        route = routeCreator.createRoute(id);
        collectionController.addElem(route);
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
        try {
            id = idGenerateble.generateId(Long.parseLong(args[1]));
        } catch (NumberFormatException e) {
            id = idGenerateble.generateId();
        }
    }

}
