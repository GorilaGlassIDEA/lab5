package by.dima.model.service.files.parser.string.impl;

import by.dima.model.data.abstracts.Model;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ParserToJsonJacksonImpl implements ParserToJson {
    @Override
    public String getJson(Model model) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(model);

        } catch (JsonGenerationException | JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
