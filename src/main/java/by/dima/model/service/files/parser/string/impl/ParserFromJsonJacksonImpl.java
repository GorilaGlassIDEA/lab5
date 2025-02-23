package by.dima.model.service.files.parser.string.impl;

import by.dima.model.data.abstracts.model.Model;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.parser.string.exceptions.JsonException;
import by.dima.model.service.files.parser.string.model.ParserFromJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParserFromJsonJacksonImpl implements ParserFromJson<Route> {

    ObjectMapper mapper;

    public ParserFromJsonJacksonImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Route getModel(String jsonContent) {
        try {
            return mapper.readValue(jsonContent, Route.class);
        } catch (JsonProcessingException e) {
            throw new JsonException("Ошибка десериализации!", e);
        }
    }
}
