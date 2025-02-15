package by.dima.model.data.route.main.model;

import by.dima.model.data.route.sub.model.Coordinates;
import by.dima.model.data.route.sub.model.LocationFrom;
import by.dima.model.data.route.sub.model.LocationTo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
public class Route implements Comparable<Route> {
    private long id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private LocationFrom from;
    private LocationTo to;
    private double distance;

    public Route(long id) {
        this.id = id;
    }


    @Override
    public int compareTo(Route o) {
        return id - o.getId() < 0 ? -1 : 1;
    }
}
