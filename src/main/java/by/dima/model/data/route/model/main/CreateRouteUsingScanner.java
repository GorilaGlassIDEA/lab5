package by.dima.model.data.route.model.main;

import by.dima.model.data.route.model.sub.Coordinates;
import by.dima.model.data.route.model.sub.LocationFrom;
import by.dima.model.data.route.model.sub.LocationTo;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Реализация pattern Builder для ввода данных с консоли
 * @see RouteBuildable
 */
public class CreateRouteUsingScanner {
    private Scanner scanner;

    public Route createRoute(RouteBuildable routeBuildable, long id) {
        scanner = new Scanner(System.in);
        routeBuildable.setId(id);
        routeBuildable.setName(readName());
        routeBuildable.setCoordinates(readCoordinates());
        routeBuildable.setLocationFrom(readLocationFrom());
        routeBuildable.setLocationTo(readLocationTo());
        routeBuildable.setDistance(readDistance());
        return routeBuildable.build();
    }

    private String readName() {
        while (true) {
            try {
                System.out.println("Route name: ");
                String name = scanner.nextLine();
                if (name == null || name.isEmpty()) {
                    System.out.println("Name shouldn't be empty");
                } else {
                    return name;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect type! Try again");
                scanner.next();
            }
        }
    }

    private Coordinates readCoordinates() {
        Coordinates coordinates = new Coordinates();
        while (true) {
            try {
                System.out.println("Coordinate X (int): ");
                if (scanner.hasNextInt()) {
                    int x = scanner.nextInt();
                    coordinates.setX(x);
                    scanner.nextLine(); // Очистка буфера
                } else {
                    System.out.println("Incorrect type! Coordinate X must be an integer. Try again.");
                    scanner.next();
                    continue;
                }

                System.out.println("Coordinate Y (double, greater than -749): ");
                if (scanner.hasNextDouble()) {
                    double y = scanner.nextDouble();
                    if (y <= -749) {
                        System.out.println("Coordinate Y must be greater than -749. Try again.");
                        scanner.nextLine(); // Очистка буфера
                        continue;
                    }
                    coordinates.setY(y);
                    scanner.nextLine(); // Очистка буфера
                } else {
                    System.out.println("Incorrect type! Coordinate Y must be a double. Try again.");
                    scanner.next();
                    continue;
                }

                return coordinates;

            } catch (InputMismatchException e) {
                System.out.println("Incorrect input! Try again.");
                scanner.next();
            }
        }
    }

    private LocationFrom readLocationFrom() {
        LocationFrom locationFrom = new LocationFrom();
        while (true) {
            try {
                System.out.println("LocationFrom X (double): ");
                if (scanner.hasNextDouble()) {
                    double x = scanner.nextDouble();
                    locationFrom.setX(x);
                    scanner.nextLine(); // Очистка буфера
                } else {
                    System.out.println("Incorrect type! LocationFrom X must be a double. Try again.");
                    scanner.next();
                    continue;
                }

                System.out.println("LocationFrom Y (float): ");
                if (scanner.hasNextFloat()) {
                    float y = scanner.nextFloat();
                    locationFrom.setY(y);
                    scanner.nextLine(); // Очистка буфера
                } else {
                    System.out.println("Incorrect type! LocationFrom Y must be a float. Try again.");
                    scanner.next();
                    continue;
                }

                System.out.println("LocationFrom Name (can be empty, length <= 690): ");
                String name = scanner.nextLine();
                if (name.length() > 690) {
                    System.out.println("Length of name must be less than or equal to 690. Try again.");
                    continue;
                }
                locationFrom.setName(name);

                return locationFrom;

            } catch (InputMismatchException e) {
                System.out.println("Incorrect input! Try again.");
                scanner.next();
            }
        }
    }

    private LocationTo readLocationTo() {
        LocationTo locationTo = new LocationTo();
        while (true) {
            try {
                System.out.println("LocationTo X (double): ");
                if (scanner.hasNextDouble()) {
                    double x = scanner.nextDouble();
                    locationTo.setX(x);
                    scanner.nextLine(); // Очистка буфера
                } else {
                    System.out.println("Incorrect type! LocationTo X must be a double. Try again.");
                    scanner.next();
                    continue;
                }

                System.out.println("LocationTo Y (double): ");
                if (scanner.hasNextDouble()) {
                    double y = scanner.nextDouble();
                    locationTo.setY(y);
                    scanner.nextLine(); // Очистка буфера
                } else {
                    System.out.println("Incorrect type! LocationTo Y must be a double. Try again.");
                    scanner.next();
                    continue;
                }

                System.out.println("LocationTo Name (can be empty, length <= 330): ");
                String name = scanner.nextLine();
                if (name.length() > 330) {
                    System.out.println("Length of name must be less than or equal to 330. Try again.");
                    continue;
                }
                locationTo.setName(name);

                return locationTo;

            } catch (InputMismatchException e) {
                System.out.println("Incorrect input! Try again.");
                scanner.next();
            }
        }
    }

    private double readDistance() {
        while (true) {
            try {
                System.out.println("Route Distance: ");
                double distance = scanner.nextDouble();
                if (distance <= 0) {
                    System.out.println("Distance should be positive");
                    scanner.nextLine(); // Очистка буфера
                } else {
                    scanner.nextLine(); // Очистка буфера
                    return distance;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect type! Try again");
                scanner.next();
            }
        }
    }
}