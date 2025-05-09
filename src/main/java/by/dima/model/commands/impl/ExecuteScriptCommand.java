package by.dima.model.commands.impl;

import by.dima.model.client.parser.ExecuteDTOParserFromJson;
import by.dima.model.commands.model.CommandAbstract;
import by.dima.model.common.ExecuteDTO;
import by.dima.model.service.io.ReadableFile;
import by.dima.model.service.util.iteration.TextIterable;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper mapper;
    private final ExecuteDTOParserFromJson parserFromJson;

    public ExecuteScriptCommand(Long userId, String filePath, ReadableFile readableFile, ObjectMapper mapper) {
        super(userId, "execute_script");
        this.readableFile = readableFile;
        this.filePath = filePath;
        this.mapper = mapper;
        builder = new StringBuilder();
        parserFromJson = new ExecuteDTOParserFromJson(mapper);
    }


    @Override
    public void execute() {
        super.execute();

        String content = tryReadFile(filePath);
        if (content != null || !content.isBlank()) {
            //TODO: сделать проверку на корректность данных);
            try {
                getCommandDTO().setExecuteDTO(parserFromJson.getObj(content));
            } catch (IOException e) {
                System.out.println("Не удалось преобразовать файл в команды, проверьте ввод!");
                e.printStackTrace();
            }
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
