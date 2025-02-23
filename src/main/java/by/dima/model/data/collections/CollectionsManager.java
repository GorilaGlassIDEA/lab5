package by.dima.model.data.collections;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.read.ReadableFile;
import by.dima.model.service.files.parser.string.model.ParserFromJson;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// Взять данные из файла -> поместить их в коллекцию
@Getter
public class CollectionsManager {
    private final Map<Long, Route> routeMap = new HashMap<>();

    public CollectionsManager(Models models) {
        for (Route route : models.getRoutes()) {
            routeMap.put(route.getId(), route);
        }
    }
}
