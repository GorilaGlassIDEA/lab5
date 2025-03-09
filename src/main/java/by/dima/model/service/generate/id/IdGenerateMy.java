package by.dima.model.service.generate.id;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.route.model.main.Route;
import lombok.Data;

import java.util.*;

@Data
public class IdGenerateMy implements IdGenerateble {
    private Models models;

    public IdGenerateMy(Models models) {
        this.models = models;
    }

    @Override
    public long generateId(Long... ids) {
        if (ids == null || ids.length == 0) {
            Long newId = 1L;
            try {
                Map<Long, Route> routeMap = models.getRoutesMap();
                List<Long> allId = new ArrayList<>(routeMap.keySet());
                for (Long id : allId) {
                    if (id > newId) {
                        newId = id;
                    }
                }
                return ++newId;
            } catch (NoSuchElementException e) {
                return newId;
            }
        } else {
            return ids[0];
        }
    }
}