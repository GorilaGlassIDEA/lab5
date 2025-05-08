package by.dima.model.commands.impl;

import by.dima.model.Client;
import by.dima.model.commands.CommandManager;
import by.dima.model.commands.model.CommandAbstract;
import by.dima.model.io.ReadableFile;
import by.dima.model.request.Clientable;
import by.dima.model.util.iteration.TextIterable;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
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
        builder = new StringBuilder();
        super.execute();
        String content = tryReadFile(filePath);
        this.content = content;
        if (content != null) {
            this.textIterable = new TextIterable(content);
            for (String command : textIterable) {
                if (!(command.isBlank() || command.split(" ").length > 1 || command.equals("insert") || command.equals("update") || command.equals("remove_key") || command.equals("remove_lower_key") || command.equals("exit"))) {
                    command = command.strip();
                    System.out.println("Команда из файла: " + command);
                    builder.append(command).append("\n");
                }
            }
            getCommandDTO().setArgCommand(new String(builder).strip());
        } else {
            System.out.println("Не получилось открыть файл по заданному пути!");
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
