package by.dima.model.data.route.model.main;

import by.dima.model.data.route.model.sub.Coordinates;
import by.dima.model.data.route.model.sub.LocationFrom;
import by.dima.model.data.route.model.sub.LocationTo;
import by.dima.model.service.generate.id.IdGenerateble;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.ZonedDateTime;

@Data
public class Route implements Comparable<Route> {
    private long id;
    private String name;
    private Coordinates coordinates;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime creationDate;
    private LocationFrom from;
    private LocationTo to;
    private double distance;


    public Route(IdGenerateble idGenerateble, String name, Coordinates coordinates, ZonedDateTime creationDate, LocationFrom from, LocationTo to, double distance) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
        // generate id
        this.id = idGenerateble.generateId();
    }

    @Override
    public int compareTo(Route o) {
        return Long.compare(o.getId(), this.id);
    }
}
