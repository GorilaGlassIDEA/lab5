package by.dima.model.data;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.write.WriteableFile;
import by.dima.model.service.files.parser.string.model.ParserToJson;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class CollectionController {
    @Setter
    private Map<Long, Route> collectionForControl;
    private final Models models;
    private final WriteableFile writeableFile;
    private final ParserToJson parser;

    public CollectionController(Models models, WriteableFile writeableFile, ParserToJson parser) {
        this.models = models;
        this.writeableFile = writeableFile;
        this.parser = parser;
        this.collectionForControl = models.getRoutesMap();
    }

    public void syncCollections() {
        collectionForControl = models.getRoutesMap();
    }

    public void addElem(Route route) {
        models.addNewElement(route);
        syncCollections();
        writeableFile.write(parser.getJson(models));
    }

}
