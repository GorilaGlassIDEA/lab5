package by.dima.model.commands.impl;

import by.dima.model.commands.model.CommandAbstract;
import by.dima.model.client.parser.RouteParserToJson;
import by.dima.model.common.route.builder.ScannerBuildRoute;
import by.dima.model.common.route.main.Route;


public class UpdateCommand extends CommandAbstract {

    private final ScannerBuildRoute scannerBuildRoute;
    private final RouteParserToJson parser;

    public UpdateCommand(RouteParserToJson parser, Long userId) {
        super(userId, "update");
        this.parser = parser;
        scannerBuildRoute = new ScannerBuildRoute();
    }

    @Override
    public void execute() {
        super.execute();
        try {
            Route route = scannerBuildRoute.build(Long.parseLong(getArg()));
            getCommandDTO().setArgCommand(getArg());
            getCommandDTO().setJsonRouteObj(parser.getObj(route));
        } catch (NumberFormatException e) {
            System.err.println("Некорректный ввод!");
        }
    }
}
