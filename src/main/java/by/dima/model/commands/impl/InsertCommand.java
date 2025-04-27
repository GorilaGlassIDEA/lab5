package by.dima.model.commands.impl;

import by.dima.model.commands.model.Command;
import by.dima.model.common.CommandDTO;
import by.dima.model.parser.RouteParserToJson;
import by.dima.model.route.builder.ScannerBuildRoute;
import by.dima.model.route.models.main.Route;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

import java.io.IOException;

/**
 * Команда позволяющая добавлять новые элементы в коллекцию
 */
@Getter
@Component
public class InsertCommand implements Command {
    private final ScannerBuildRoute builder;
    private final RouteParserToJson routeParserToJson;
    private CommandDTO commandDTO;
    @Setter
    private String arg;
    private final RouteParserToJson parser;
    @Setter
    private String key = "insert";

    @Autowired
    public InsertCommand( ScannerBuildRoute builder, RouteParserToJson parser, RouteParserToJson routeParserToJson) {
        this.builder = builder;
        this.parser = parser;
        this.routeParserToJson = routeParserToJson;
    }

    @Override
    public void execute() {
        Route route = builder.getRoute();
        if (route != null) {
            commandDTO = new CommandDTO(key,
                    arg == null ? "" : arg, parser.getObj(route), route.getId());
        }
    }

    @Override
    public CommandDTO getCommandDTO() {
        return commandDTO;
    }
}
