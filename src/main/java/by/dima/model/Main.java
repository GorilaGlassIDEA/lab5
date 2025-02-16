package by.dima.model;

import by.dima.model.data.abstracts.Model;
import by.dima.model.data.command.CommandManager;
import by.dima.model.data.route.main.model.Route;
import by.dima.model.data.route.sub.model.Coordinates;
import by.dima.model.data.route.sub.model.LocationFrom;
import by.dima.model.data.route.sub.model.LocationTo;
import by.dima.model.service.files.parser.string.impl.ParserToJsonJacksonImpl;
import by.dima.model.service.files.parser.string.model.ParserToJson;

import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {

        CommandManager commandManager = new CommandManager();
        commandManager.executeCommand(args);

        Route route = new Route(
                10,
                "name",
                new Coordinates(10, 10.3),
                ZonedDateTime.now(),
                new LocationFrom(10, 10f, "this Name"),
                new LocationTo(10d, 10d, "name"),
                10.0
        );

        ParserToJson parser = new ParserToJsonJacksonImpl();
        System.out.println(parser.getJson(new Model(route)));


    }
}