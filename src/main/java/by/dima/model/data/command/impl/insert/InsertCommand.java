package by.dima.model.data.command.impl.insert;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.AddableInfo;
import by.dima.model.service.files.io.write.WriteableFile;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import by.dima.model.service.generate.id.IdGenerateble;
import lombok.Getter;
import lombok.Setter;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


@Getter
public class InsertCommand implements Command {
    @Setter
    private String key = "insert";
    private Route route;
    private final long id;
    private final AddableInfo addableInfo;
    private final ParserToJson parser;

    public InsertCommand(AddableInfo addableInfo, ParserToJson parser, IdGenerateble idGenerateble) {
        this.id = idGenerateble.generateId();
        this.addableInfo = addableInfo;
        this.parser = parser;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        RouteCreator routeCreator = new RouteCreator(scanner);
        route = routeCreator.createRoute(id);
        addableInfo.addInfo(route);

    }
}
