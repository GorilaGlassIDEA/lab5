package by.dima.model.data.command.impl.insert;

import by.dima.model.data.route.model.CheckValidateInfoUtilMyImpl;
import by.dima.model.data.route.model.exceptions.IncorrectDataModel;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.data.route.model.sub.Coordinates;
import by.dima.model.data.route.model.sub.LocationFrom;
import by.dima.model.data.route.model.sub.LocationTo;

import java.util.InputMismatchException;
import java.util.Scanner;


// TODO: переписать без костылей
public class RouteCreator {
    private final Scanner scanner;
    private final Route route;

    public RouteCreator(Scanner scanner) {
        route = new Route();
        this.scanner = scanner;
    }

    public Route createRoute() {
        readId();
        readName();
        readCoordinates();
        readLocationFrom();
        readLocationTo();
        readDistance();
        return new Route(route.getId(), route.getName(), route.getCoordinates(), route.getFrom(), route.getTo(), route.getDistance());
    }

    private void readId() {
        while (route.getId() == 0) {
            try {
                System.out.print("Route id: ");
                long id = scanner.nextLong();
                if (id == 0) {
                    System.out.println("Id shouldn't be empty");
                    scanner.next();
                } else {
                    route.setId(id);
                    scanner.nextLine(); // Очистка буфера
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect type! Try again");
                scanner.next();
                scanner.nextLine(); // Очистка буфера
            } catch (IncorrectDataModel e) {
                System.err.println(e);
                scanner.next();
                scanner.nextLine(); // Очистка буфера
            }
        }
    }

    private void readName() {
        while (route.getName() == null || route.getName().isEmpty()) {
            try {
                System.out.print("Route name: ");
                String name = scanner.nextLine();
                if (name == null || name.isEmpty()) {
                    System.out.println("Name shouldn't be empty");
                } else {
                    route.setName(name);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect type! Try again");
                scanner.next();
            }
        }
    }

    private void readCoordinates() {
        Coordinates coordinates = new Coordinates();
        while (route.getCoordinates() == null) {
            try {
                if (coordinates.getX() == 0) {
                    System.out.print("Coordinate X: ");
                    int x = scanner.nextInt();
                    coordinates.setX(x);
                    scanner.nextLine();
                }

                System.out.print("Coordinate Y: ");
                Double y = scanner.nextDouble();
                scanner.nextLine();
                if (y == null || y <= -749) {
                    System.out.println("Coordinate Y must be greater than -749 and not null. Try again.");
                    continue;
                }

                coordinates.setY(y);
                route.setCoordinates(coordinates);
                break;

            } catch (InputMismatchException e) {
                System.out.println("Incorrect type! Try again");
                scanner.next();
                scanner.nextLine();

                coordinates.setX(0);
            }
        }
    }

    private void readLocationFrom() {
        LocationFrom locationFrom = new LocationFrom();
        Double x = null;
        Float y = null;
        while (route.getFrom() == null) {
            try {
                if (x == null) {
                    System.out.print("LocationFrom X: ");
                    x = scanner.nextDouble();
                    locationFrom.setX(x);
                }
                if (y == null) {
                    System.out.print("LocationFrom Y: ");
                    y = scanner.nextFloat();
                    locationFrom.setY(y);
                }
                System.out.print("LocationFrom Name (can be empty): ");
                String name = scanner.nextLine();
                scanner.nextLine();
                if (name.length() > 690 || name.isEmpty()) {
                    System.out.println("Length name must be less than 690 and greater than 0. Try again.");
                    continue;
                }
                locationFrom.setName(name);
                route.setFrom(locationFrom);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect type! Try again");
                scanner.next();
            }
        }
    }

    private void readLocationTo() {
        LocationTo locationTo = new LocationTo();
        Double x = null;
        Double y = null;
        while (route.getTo() == null) {
            try {
                if (x == null) {
                    System.out.print("LocationTo X: ");
                    x = scanner.nextDouble();
                    locationTo.setX(x);
                }
                if (y == null) {
                    System.out.print("LocationTo Y: ");
                    y = scanner.nextDouble();
                    locationTo.setY(y);
                }
                System.out.print("LocationTo Name (can be empty): ");
                String name = scanner.nextLine();
                scanner.nextLine();
                if (name.length() > 330 || name.isEmpty()) {
                    System.out.println("Length name must be less than 330 and greater than 0. Try again.");
                    continue;
                }
                locationTo.setName(name);
                route.setTo(locationTo);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect type! Try again");
                scanner.next();
            }
        }
    }

    private void readDistance() {
        while (route.getDistance() == 0) {
            try {
                System.out.print("Route Distance: ");
                double distance = scanner.nextDouble();
                if (distance <= 0) {
                    System.out.println("Distance should be positive");
                    scanner.next();
                } else {
                    route.setDistance(distance);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect type! Try again");
                scanner.next();
            }
        }
    }

}
