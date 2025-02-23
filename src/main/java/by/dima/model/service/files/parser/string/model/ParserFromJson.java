package by.dima.model.service.files.parser.string.model;

import by.dima.model.data.abstracts.model.Model;

public interface ParserFromJson {
    Model getModel(String jsonContent);
}
