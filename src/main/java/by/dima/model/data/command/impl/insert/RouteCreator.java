package by.dima.model.data.command.impl.insert;

import by.dima.model.data.route.model.exceptions.IncorrectDataModel;
import by.dima.model.data.route.model.main.Route;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RouteCreator {
    private final Scanner scanner;
    private final Route route;

    public RouteCreator(Scanner scanner) {
        route = new Route();
        this.scanner = scanner;
    }

    public Route createRoute() {
        readId();
        return route;
    }

    private void readId() {
        while (route.getId() == 0) {
            try {
                System.out.print("Route id: ");
                long id = scanner.nextLong();
                if (id == 0) {
                    System.out.println("id cant be 0!");
                    scanner.next();
                } else {
                    route.setId(id);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect type id! Id is long!");
                scanner.next();
            } catch (IncorrectDataModel e) {
                System.err.println(e);
                scanner.next();
            }
        }
    }
}
