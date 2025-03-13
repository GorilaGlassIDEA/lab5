package by.dima.model.data.command.impl;

import by.dima.model.data.CollectionController;
import by.dima.model.data.route.model.main.FillOutRouteModelUsingScanner;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.data.route.model.main.RouteBuilder;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import by.dima.model.service.generate.id.IdGenerateble;
import lombok.Getter;
import lombok.Setter;


@Getter
public class InsertCommand implements Command {
    @Setter
    private String key = "insert";
    private Route route;
    private long id;
    private final CollectionController collectionController;
    private final ParserToJson parser;
    private final FillOutRouteModelUsingScanner fillOutRouteModelUsingScanner;
    private final IdGenerateble idGenerateble;

    public InsertCommand(CollectionController collectionController, ParserToJson parser, IdGenerateble idGenerateble, FillOutRouteModelUsingScanner fillOutRouteModelUsingScanner) {
        this.collectionController = collectionController;
        this.parser = parser;
        this.fillOutRouteModelUsingScanner = fillOutRouteModelUsingScanner;
        this.idGenerateble = idGenerateble;
    }


    @Override
    public void execute() {
        route = fillOutRouteModelUsingScanner.createRoute(new RouteBuilder(), id);
        collectionController.addElem(route);
        System.out.println("New element added in collection! You can save changes using save command!");
    }

    @Override
    public void setArgs(String[] args) {
        try {
            id = idGenerateble.generateId(Long.parseLong(args[1]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            id = idGenerateble.generateId();
        }
    }
}
