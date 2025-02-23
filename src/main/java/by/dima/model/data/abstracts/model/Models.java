package by.dima.model.data.abstracts.model;

import by.dima.model.data.route.model.main.Route;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
public class Models {
    @Setter
    private List<Route> routes;
    private ZonedDateTime zonedDateTime;
    private final String type = Route.class.getName();

    public Models(List<Route> routes) {
        this.routes = routes;
        this.zonedDateTime = ZonedDateTime.now();
    }

    public long sizeArray() {
        return routes.size();
    }
}
