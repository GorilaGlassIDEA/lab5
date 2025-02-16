package by.dima.model.service.files.parser.string.impl;

import by.dima.model.data.abstracts.Model;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import com.google.gson.Gson;

public class ParserToJsonGsonImpl implements ParserToJson {
    @Override
    public String getJson(Model model) {
        Gson gson = new Gson();
        return gson.toJson(model);
    }
}
