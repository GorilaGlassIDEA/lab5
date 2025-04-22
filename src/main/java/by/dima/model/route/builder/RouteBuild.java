package by.dima.model.route.builder;


import by.dima.model.route.models.main.Route;
import by.dima.model.route.models.sub.Coordinates;
import by.dima.model.route.models.sub.LocationFrom;
import by.dima.model.route.models.sub.LocationTo;

public class RouteBuild {
    private long id;
    private String name;
    private Coordinates coordinates;
    private LocationFrom from;
    private LocationTo to;
    private double distance;

    public RouteBuild setId(long id) {
        this.id = id;
        return this;
    }

    public RouteBuild setName(String name) {
        this.name = name;
        return this;
    }

    public RouteBuild setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public RouteBuild setFrom(LocationFrom from) {
        this.from = from;
        return this;
    }

    public RouteBuild setTo(LocationTo to) {
        this.to = to;
        return this;
    }

    public RouteBuild setDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Route build() {
        return new Route(id, name, coordinates, from, to, distance);
    }
}
