package by.dima.model.service.iterator;

import by.dima.model.data.route.model.main.Route;

import java.util.*;

public class RouteSortIterator implements Iterator<Route> {

    private int count;
    private final List<Long> keys;
    private final Map<Long, Route> routeMap;


    public RouteSortIterator(Map<Long, Route> routeMap) {
        this.keys = new ArrayList<>(routeMap.keySet());
        this.routeMap = routeMap;
        keys.sort(Long::compare);
        count = 0;
    }

    @Override
    public boolean hasNext() {
        return count < routeMap.size();
    }

    @Override
    public Route next() {
        return routeMap.get(keys.get(count++));
    }
}
