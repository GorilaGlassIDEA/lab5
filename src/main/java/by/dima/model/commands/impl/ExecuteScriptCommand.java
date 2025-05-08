package by.dima.model.commands.impl;

import by.dima.model.commands.model.CommandAbstract;
import by.dima.model.service.io.ReadableFile;
import by.dima.model.service.util.iteration.TextIterable;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * Данная команда позволяет запускать другие команды с помощью текстовых файлов, содержащих последовательность
 * команд. Для правильного чтения данных из файла используется собственный класс-итератор {@link TextIterable}
 */
@Setter
@Getter
public class ExecuteScriptCommand extends CommandAbstract {
    private String content;
    private TextIterable textIterable;
    private final ReadableFile readableFile;
    private final String filePath;
    private StringBuilder builder;


    public ExecuteScriptCommand(Long userId, String filePath, ReadableFile readableFile) {
        super(userId, "execute_script");
        this.readableFile = readableFile;
        this.filePath = filePath;
        builder = new StringBuilder();
    }


    @Override
    public void execute() {
        super.execute();
    }

    /**
     * Вспомогательный метод для чтения файла
     *
     * @param filePath путь к файлу
     * @return содержимое файла или null если не удалось прочитать
     */
    private String tryReadFile(String filePath) {
        try {
            return readableFile.getContent(filePath);
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

}
