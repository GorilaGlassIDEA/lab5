package by.dima.model.data.command.impl;

import by.dima.model.data.command.impl.creator.RouteCreator;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class UpdateCommand implements Command {
    @Getter
    @Setter
    private String key = "update";
    private Long id;
    private final Map<Long, Route> routeMap;
    private final String[] args;
    private final RouteCreator routeCreator;

    public UpdateCommand(Map<Long, Route> routeMap, String[] args, RouteCreator routeCreator) {
        this.routeMap = routeMap;
        this.args = args;
        this.routeCreator = routeCreator;
    }

    @Override
    public void execute() {
        initId();
        if (!routeMap.containsKey(id)) {
            System.err.println("This id does not exist in your collection!");
        } else {
            Route newRoute = routeCreator.createRoute(id);
            routeMap.replace(newRoute.getId(), newRoute);
        }

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
