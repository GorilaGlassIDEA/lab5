package by.dima.model.data.collections;

import by.dima.model.data.abstracts.model.Model;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CollectionsDataModel {
    Map<Long, Model> modelMap = new HashMap<>();

    public CollectionsDataModel(Model... models) {
        for (Model model : models) {
            modelMap.put(model.getId(), model);
        }
    }

}
