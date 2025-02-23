package by.dima.model.data.abstracts.model;

import by.dima.model.data.route.model.main.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Models {
    ArrayList<Route> routes;

}
