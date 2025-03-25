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
                System.out.print("Route name: ");
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

                return coordinates;


            } catch (InputMismatchException e) {
                System.out.println("Incorrect input! Try again.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }
    }

    private LocationFrom readLocationFrom() {
        LocationFrom locationFrom = new LocationFrom();
        while (true) {
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

                return locationFrom;

            } catch (InputMismatchException e) {
                System.out.println("Incorrect input! Try again.");
                scanner.next(); // Пропустить некорректный ввод
            } finally {
                scanner.nextLine(); // Очистить буфер сканера
            }
        }
    }

    private LocationTo readLocationTo() {
        LocationTo locationTo = new LocationTo();
        while (true) {
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

                return locationTo;


            } catch (InputMismatchException e) {
                System.out.println("Incorrect input! Try again.");
                scanner.next(); // Пропустить некорректный ввод
            } finally {
                scanner.nextLine(); // Очистить буфер сканера
            }
        }
    }

    private double readDistance() {
        while (true) {
            try {
                System.out.print("Route Distance: ");
                double distance = scanner.nextDouble();
                if (distance <= 0) {
                    System.out.println("Distance should be positive");
                    scanner.next();
                } else {
                    return distance;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect type! Try again");
                scanner.next();
            }
        }
    }

}
