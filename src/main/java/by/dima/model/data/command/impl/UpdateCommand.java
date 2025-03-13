package by.dima.model.data.command.impl;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.route.model.main.FillOutRouteModelUsingScanner;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;

import by.dima.model.data.route.model.main.RouteBuilder;
import by.dima.model.service.files.io.write.WriteableFile;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class UpdateCommand implements Command {
    @Getter
    @Setter
    private String key = "update";
    private Long id;
    private final Map<Long, Route> routeMap;
    @Setter
    private String[] args;
    private final FillOutRouteModelUsingScanner routeCreator;
    private final WriteableFile writeableFile;
    private final ParserToJson parser;

    public UpdateCommand(Map<Long, Route> routeMap, FillOutRouteModelUsingScanner routeCreator, WriteableFile writeableFile, ParserToJson parser) {
        this.routeMap = routeMap;
        this.routeCreator = routeCreator;
        this.writeableFile = writeableFile;
        this.parser = parser;
    }

    @Override
    public void execute() {
        initId();
        if (!routeMap.containsKey(id)) {
            System.err.println("This id does not exist in your collection!");
        } else {
            Route newRoute = routeCreator.createRoute(new RouteBuilder(), id);
            routeMap.replace(newRoute.getId(), newRoute);
        }
        writeableFile.write(parser.getJson(new Models(routeMap)));
    }

    private void initId() {
        Long id = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(key)) {
                if ((i + 1) < (args.length - 1)) {
                    System.err.println("Write to id!");
                } else
                    try {
                        id = Long.parseLong(args[i + 1]);
                    } catch (NumberFormatException e) {
                        System.err.println("Incorrect arg! A wet {" + args[i + 1] + "}");
                    }
            }
            if (id != null) {
                this.id = id;
            }
        }
    }
}
