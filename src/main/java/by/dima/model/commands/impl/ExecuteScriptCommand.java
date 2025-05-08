package by.dima.model.commands.impl;

import by.dima.model.commands.CommandManager;
import by.dima.model.commands.model.Command;
import by.dima.model.commands.model.CommandAbstract;
import by.dima.model.route.builder.RouteBuilder;
import by.dima.model.service.io.ReadableFile;
import by.dima.model.service.util.iteration.TextIterable;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    private RouteBuilder routeBuilder;
    private final Map<String, Command> commandMap;


    public ExecuteScriptCommand(Long userId, String filePath, ReadableFile readableFile, Map<String, Command> commandMap) {
        super(userId, "execute_script");
        this.readableFile = readableFile;
        this.filePath = filePath;
        builder = new StringBuilder();
        this.commandMap = commandMap;
        routeBuilder = new RouteBuilder();
    }


    @Override
    public void execute() {
        super.execute();
        String content = tryReadFile(filePath);
        if (content != null) {
            TextIterable iterable = new TextIterable(content);

        } else {
            System.out.println("Некорректный ввод!");
        }
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
