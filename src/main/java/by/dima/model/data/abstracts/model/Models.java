package by.dima.model.data.abstracts.model;

import by.dima.model.data.route.model.main.Route;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@NoArgsConstructor
@Getter
public class Models {
    @Setter
    private Map<Long, Route> routesMap;
    private ZonedDateTime zonedDateTime;
    private final String type = Route.class.getName();

    public Models(Map<Long, Route> routesMap) {
        this.routesMap = routesMap;
        this.zonedDateTime = ZonedDateTime.now();
    }

    public long sizeArray() {
        return routesMap.size();
    }

    public void addNewElement(Route route) {
        routesMap.put(route.getId(), route);
    }

}
