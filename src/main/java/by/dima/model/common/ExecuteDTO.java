package by.dima.model.common;

import by.dima.model.common.route.main.Route;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Map<String, List<Route>> commandsForExecuteScript;
}