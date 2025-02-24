package by.dima.model.data.command.impl.insert;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.write.WriteableFile;
import by.dima.model.service.files.parser.string.model.ParserToJson;
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
    private final WriteableFile writeableFile;
    private final ParserToJson parser;

    public InsertCommand(long id, WriteableFile writeableFile, ParserToJson parser) {
        this.id = id;
        this.writeableFile = writeableFile;
        this.parser = parser;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        RouteCreator routeCreator = new RouteCreator(scanner);
        route = routeCreator.createRoute(id);
        writeableFile.write(
                parser.getJson(new Models(List.of(route)))
        );

    }
}
