package by.dima.model.commands.impl;

import by.dima.model.commands.model.Command;
import by.dima.model.common.CommandDTO;
import by.dima.model.parser.RouteParserToJson;
import by.dima.model.route.builder.ScannerBuildRoute;
import by.dima.model.route.models.main.Route;
import lombok.Getter;
import lombok.Setter;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Команда позволяющая добавлять новые элементы в коллекцию
 */
@Getter
public class InsertCommand implements Command {
    private ScannerBuildRoute builder;
    private final Logger logger;
    @Setter
    private CommandDTO commandDTO;
    private String arg;
    private final RouteParserToJson parser;
    @Setter
    private String key = "insert";
    private final Long userId;

    public InsertCommand(RouteParserToJson parser, Long userId, Logger logger) {
        this.parser = parser;
        this.logger = logger;
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
                logger.log(Level.CONFIG, "Id при создании невозможно преобразовать в long");
            }
        }

        Route route = builder.build(routeId);
        if (route != null) {
            commandDTO = new CommandDTO(key,
                    arg == null ? "" : arg, parser.getObj(route), userId);
        }
        logger.log(Level.INFO, "Построенная модель: " + route);
        builder = new ScannerBuildRoute();
    }

    @Override
    public void setArgs(String arg) {
        this.arg = arg;
    }

}
