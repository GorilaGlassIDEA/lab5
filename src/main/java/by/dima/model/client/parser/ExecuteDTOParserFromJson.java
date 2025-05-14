package by.dima.model.client.parser;

import by.dima.model.common.ExecuteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ExecuteDTOParserFromJson {
    private final ObjectMapper mapper;

    public ExecuteDTOParserFromJson(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ExecuteDTO getObj(String executeDTO) throws IOException {
        return mapper.readValue(executeDTO, ExecuteDTO.class);

    }
}
