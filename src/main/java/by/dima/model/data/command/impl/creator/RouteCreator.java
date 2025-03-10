package by.dima.model.data.command.impl.creator;

import by.dima.model.data.route.model.main.Route;
import by.dima.model.data.route.model.sub.Coordinates;
import by.dima.model.data.route.model.sub.LocationFrom;
import by.dima.model.data.route.model.sub.LocationTo;

import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;


// TODO: переписать без костылей
public class RouteCreator {
    private final Scanner scanner;
    private Route route;

    public RouteCreator(Scanner scanner) {
        this.scanner = scanner;
    }


    public Route createRoute(long id) {
        route = new Route();
        readName();
        readCoordinates();
        readLocationFrom();
        readLocationTo();
        readDistance();
        return new Route(id, route.getName(), route.getCoordinates(), route.getFrom(), route.getTo(), route.getDistance());
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
                    System.out.print("Coordinate X (int): ");
                    if (scanner.hasNextInt()) {
                        int x = scanner.nextInt();
                        coordinates.setX(x);
                    } else {
                        System.out.println("Incorrect type! Coordinate X must be an integer. Try again.");
                        scanner.next();
                        continue;
                    }
                }

                System.out.print("Coordinate Y (double, greater than -749): ");
                if (scanner.hasNextDouble()) {
                    double y = scanner.nextDouble();
                    if (y <= -749) {
                        System.out.println("Coordinate Y must be greater than -749. Try again.");
                        continue;
                    }
                    coordinates.setY(y);
                } else {
                    System.out.println("Incorrect type! Coordinate Y must be a double. Try again.");
                    scanner.next();
                    continue;
                }

                route.setCoordinates(coordinates);
                break;

            } catch (InputMismatchException e) {
                System.out.println("Incorrect input! Try again.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }
    }

    private void readLocationFrom() {
        LocationFrom locationFrom = new LocationFrom();
        while (route.getFrom() == null) {
            try {
                System.out.print("LocationFrom X (double): ");
                if (scanner.hasNextDouble()) {
                    double x = scanner.nextDouble();
                    locationFrom.setX(x);
                } else {
                    System.out.println("Incorrect type! LocationFrom X must be a double. Try again.");
                    scanner.next(); // Пропустить некорректный ввод
                    continue;
                }

                System.out.print("LocationFrom Y (float): ");
                if (scanner.hasNextFloat()) {
                    float y = scanner.nextFloat();
                    locationFrom.setY(y);
                } else {
                    System.out.println("Incorrect type! LocationFrom Y must be a float. Try again.");
                    scanner.next(); // Пропустить некорректный ввод
                    continue;
                }

                System.out.print("LocationFrom Name (can be empty, length <= 690): ");
                scanner.nextLine(); // Очистить буфер перед чтением строки
                String name = scanner.nextLine();
                if (name.length() > 690) {
                    System.out.println("Length of name must be less than or equal to 690. Try again.");
                    continue;
                }
                locationFrom.setName(name);

                route.setFrom(locationFrom);
                break;

            } catch (InputMismatchException e) {
                System.out.println("Incorrect input! Try again.");
                scanner.next(); // Пропустить некорректный ввод
            } finally {
                scanner.nextLine(); // Очистить буфер сканера
            }
        }
    }

    private void readLocationTo() {
        LocationTo locationTo = new LocationTo();
        while (route.getTo() == null) {
            try {
                System.out.print("LocationTo X (double): ");
                if (scanner.hasNextDouble()) {
                    double x = scanner.nextDouble();
                    locationTo.setX(x);
                } else {
                    System.out.println("Incorrect type! LocationTo X must be a double. Try again.");
                    scanner.next(); // Пропустить некорректный ввод
                    continue;
                }

                System.out.print("LocationTo Y (double): ");
                if (scanner.hasNextDouble()) {
                    double y = scanner.nextDouble();
                    locationTo.setY(y);
                } else {
                    System.out.println("Incorrect type! LocationTo Y must be a double. Try again.");
                    scanner.next(); // Пропустить некорректный ввод
                    continue;
                }

                System.out.print("LocationTo Name (can be empty, length <= 330): ");
                scanner.nextLine(); // Очистить буфер перед чтением строки
                String name = scanner.nextLine();
                if (name.length() > 330) {
                    System.out.println("Length of name must be less than or equal to 330. Try again.");
                    continue;
                }
                locationTo.setName(name);

                route.setTo(locationTo);
                break;

            } catch (InputMismatchException e) {
                System.out.println("Incorrect input! Try again.");
                scanner.next(); // Пропустить некорректный ввод
            } finally {
                scanner.nextLine(); // Очистить буфер сканера
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
