package by.dima.model.data.command.impl;

import by.dima.model.data.command.impl.creator.RouteCreator;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.AddableInfo;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import by.dima.model.service.generate.id.IdGenerateble;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;


@Getter
public class InsertCommand implements Command {
    @Setter
    private String key = "insert";
    private Route route;
    private final long id;
    private final AddableInfo addableInfo;
    private final ParserToJson parser;
    private final RouteCreator routeCreator;

    public InsertCommand(AddableInfo addableInfo, ParserToJson parser, IdGenerateble idGenerateble, RouteCreator routeCreator) {
        this.id = idGenerateble.generateId();
        this.addableInfo = addableInfo;
        this.parser = parser;
        this.routeCreator = routeCreator;
    }


    @Override
    public void execute() {
        route = routeCreator.createRoute(id);
        addableInfo.addInfo(route);

    }
}
