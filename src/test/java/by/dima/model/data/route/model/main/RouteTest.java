package by.dima.model.data.route.model.main;

import by.dima.model.data.route.model.exceptions.IncorrectDataModel;
import by.dima.model.data.route.model.sub.Coordinates;
import by.dima.model.data.route.model.sub.LocationFrom;
import by.dima.model.data.route.model.sub.LocationTo;

import org.junit.jupiter.api.Test; // Import для аннотации Test
import org.junit.jupiter.api.function.Executable; // Import для Executable
import org.junit.jupiter.api.Assertions; // Import для Assertions (вместо TestCase)

import static org.junit.jupiter.api.Assertions.assertThrows; // Import статического метода assertThrows


public class RouteTest {
    @Test
    void testCheckValidateInfo() {
        Coordinates coordinates1 = new Coordinates(10, 20.5);
        LocationFrom from1 = new LocationFrom(1.1, 2.2f, "Start Point A");
        LocationTo to1 = new LocationTo(3.3, 4.4, "End Point B");
        assertThrows(IncorrectDataModel.class, () -> {
            Route route1 = new Route(1, "", coordinates1, from1, to1, 100.0);
        });

    }
}