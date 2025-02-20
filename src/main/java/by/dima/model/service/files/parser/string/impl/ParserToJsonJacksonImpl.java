package by.dima.model.service.files.parser.string.impl;

import by.dima.model.data.abstracts.Model;
import by.dima.model.service.files.parser.string.exceptions.JsonSerializationException;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

//Dependency injection
public class ParserToJsonJacksonImpl<T> implements ParserToJson<T> {
    ObjectMapper objectMapper;

    public ParserToJsonJacksonImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

    }

    @Override
    public String getJson(Model<T> model) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(model.getT());
        } catch (JsonProcessingException e){
            throw new JsonSerializationException("Ошибка сериализации объекта",e);
        }
    }
}
