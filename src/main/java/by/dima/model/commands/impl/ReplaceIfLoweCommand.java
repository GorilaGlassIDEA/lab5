package by.dima.model.commands.impl;

import by.dima.model.commands.model.CommandAbstract;
import by.dima.model.client.parser.RouteParserToJson;
import by.dima.model.common.route.builder.ScannerBuildRoute;

public class ReplaceIfLoweCommand extends CommandAbstract {

    private final ScannerBuildRoute builder;
    private final RouteParserToJson parser;

    public ReplaceIfLoweCommand(RouteParserToJson parser, Long userId) {
        super(userId, "replace_if_lowe");
        builder = new ScannerBuildRoute();
        this.parser = parser;
    }

    @Override
    public void execute() {
        super.execute();

        if (getArg() == null) {
            System.out.println("Некорректно введена команда!");
        } else {
            try {
                getCommandDTO().setJsonRouteObj(parser.getObj(builder.build(Long.parseLong(getArg()))));
                System.out.println("Команда корректно отправила Route");
            } catch (NumberFormatException e) {
                System.out.println("Введите корректный формат аргумента!");
            }
        }
    }
}
