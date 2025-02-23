package by.dima.model.data.collections;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.route.model.main.Route;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CollectionsDataModel {
    Map<Long, Route> modelMap = new HashMap<>();

    public CollectionsDataModel(Models models) {
        for (Route route : models.getRoutes()) {
            modelMap.put(route.getId(), route);
        }
    }

}
