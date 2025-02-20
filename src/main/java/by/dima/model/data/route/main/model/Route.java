package by.dima.model.data.route.main.model;

import by.dima.model.data.route.sub.model.Coordinates;
import by.dima.model.data.route.sub.model.LocationFrom;
import by.dima.model.data.route.sub.model.LocationTo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Map;
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
