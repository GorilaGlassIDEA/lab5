package by.dima.model.parser;

import by.dima.model.route.models.main.Route;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class RouteParserToJson {

    private final ObjectMapper mapper;

    @Autowired
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
