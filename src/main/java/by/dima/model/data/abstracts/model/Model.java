package by.dima.model.data.abstracts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Model<T> {
    private T t;
}
