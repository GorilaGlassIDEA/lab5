package by.dima.model.data.command.impl.insert;

import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;
import lombok.Getter;
import lombok.Setter;

import java.util.InputMismatchException;
import java.util.Scanner;


@Getter
public class InsertCommand implements Command {
    @Setter
    private String key = "insert";
    private Route route;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        RouteCreator routeCreator = new RouteCreator(scanner);

        route = routeCreator.createRoute();
        System.out.println(route);
    }
}
