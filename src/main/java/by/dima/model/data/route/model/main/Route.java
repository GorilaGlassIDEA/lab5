package by.dima.model.data.route.model.main;


import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.route.model.CheckValidateInfoUtilMyImpl;
import by.dima.model.data.route.model.CheckableValidateInfoUtil;
import by.dima.model.data.route.model.exceptions.IncorrectDataModel;
import by.dima.model.data.route.model.sub.Coordinates;
import by.dima.model.data.route.model.sub.LocationFrom;
import by.dima.model.data.route.model.sub.LocationTo;
import by.dima.model.service.generate.id.IdGenerateble;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class Route implements Comparable<Route> {
    private long id;
    private String name;
    private Coordinates coordinates;
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime creationDate;
    private LocationFrom from;
    private LocationTo to;
    private double distance;
    private CheckableValidateInfoUtil checkableValidateInfoUtil;

    public Route(long id, String name, Coordinates coordinates, LocationFrom from, LocationTo to, double distance) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
        // generate id
        this.id = id;
        checkableValidateInfoUtil = new CheckValidateInfoUtilMyImpl(this);
        checkableValidateInfoUtil.checkable();
    }

    @Override
    public int compareTo(Route o) {
        return Long.compare(o.getId(), this.id);
    }
}
