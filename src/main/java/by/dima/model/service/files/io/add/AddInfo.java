package by.dima.model.service.files.io.add;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.write.WriteableFile;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import lombok.Getter;

public class AddInfo implements AddableInfo {
    private final Models models;
    @Getter
    private final WriteableFile writeableFile;
    private final ParserToJson parser;


    public AddInfo(Models models, WriteableFile writeableFile, ParserToJson parser) {
        this.models = models;
        this.writeableFile = writeableFile;
        this.parser = parser;
    }
}
