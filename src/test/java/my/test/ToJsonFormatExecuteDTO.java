package my.test;

import by.dima.model.client.parser.ExecuteDTOParserFromJson;
import by.dima.model.common.ExecuteDTO;
import by.dima.model.common.route.main.Route;
import by.dima.model.common.route.sub.Coordinates;
import by.dima.model.common.route.sub.LocationFrom;
import by.dima.model.common.route.sub.LocationTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ToJsonFormatExecuteDTO {
    @Test
    void test() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Route route1 = new Route(
                1L,
                "Маршрут из Москвы в Санкт-Петербург",
                new Coordinates(55, 37.6176),
                new LocationFrom(55.7558, 37.6176F, "Москва"),
                new LocationTo(59.9343, 30.3351, "Санкт-Петербург"),
                634.0
        );

        Route route2 = new Route(
                2L,
                "Маршрут из Минска в Киев",
                new Coordinates(53, 27.5615),
                new LocationFrom(53.9045, 27.5615F, "Минск"),
                new LocationTo(50.4501, 30.5234, "Киев"),
                467.0
        );


        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ExecuteDTO executeDTO = new ExecuteDTO(List.of(
                new HashMap<>(Map.of(
                        "insert", route1
                )),
                new HashMap<>(Map.of(
                        "update", route2
                ))
        ));
        ExecuteDTOParserFromJson parserFromJson = new ExecuteDTOParserFromJson(mapper);


    }
}
