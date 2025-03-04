package by.dima.model.service.files.io.add;

import by.dima.model.data.route.model.main.Route;
import by.dima.model.service.files.io.write.WriteableFile;

public interface AddableInfo {
    void addInfo(Route route);
    WriteableFile getWriteableFile();
}
