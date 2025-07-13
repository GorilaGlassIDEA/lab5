package by.dima.model.commands.impl;

import by.dima.model.commands.model.Command;
import by.dima.model.common.CommandDTO;
import by.dima.model.client.parser.RouteParserToJson;
import by.dima.model.common.route.builder.ScannerBuildRoute;
import by.dima.model.common.route.main.Route;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Команда позволяющая добавлять новые элементы в коллекцию
 */
@Getter
@Slf4j
public class InsertCommand implements Command {
    private ScannerBuildRoute builder;
    @Setter
    private CommandDTO commandDTO;
    private String arg;
    private final RouteParserToJson parser;
    @Setter
    private String key = "insert";
    private final Long userId;

    public InsertCommand(RouteParserToJson parser, Long userId) {
        this.parser = parser;
        this.builder = new ScannerBuildRoute();
        this.userId = userId;
    }

    @Override
    public void execute() {
        Long routeId = -1L;
        if (arg != null) {
            try {
                routeId = Long.parseLong(arg);
            } catch (NumberFormatException e) {
                log.info("Id при создании невозможно преобразовать в long");
            }
        }

        Route route = builder.build(routeId);
        if (route != null) {
            commandDTO = new CommandDTO(key, routeId.toString(), parser.getObj(route), userId);
        }
        log.info("Построенная модель: " + route);
        builder = new ScannerBuildRoute();
    }

    @Override
    public void setArgs(String arg) {
        this.arg = arg;
    }

}
