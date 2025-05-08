package by.dima.model.client.parser;

import by.dima.model.route.models.main.Route;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class RouteParserToJson {

    private final ObjectMapper mapper;

    public RouteParserToJson(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String getObj(Route route) {
        try {
            return mapper.writeValueAsString(route);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
