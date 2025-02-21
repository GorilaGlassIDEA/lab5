package by.dima.model.data.route.model.main;

import by.dima.model.data.route.model.sub.Coordinates;
import by.dima.model.data.route.model.sub.LocationFrom;
import by.dima.model.data.route.model.sub.LocationTo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
public class Route {
    //Решить проблему генерации id для ключа Map<id, Route>
    //проблема в том, когда и как получать id элемента из json, чтобы
    //id элемента не начинался каждый раз с единицы
    private static long id = 1;
    private String name;
    private Coordinates coordinates;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime creationDate;
    private LocationFrom from;
    private LocationTo to;
    private double distance;

}
