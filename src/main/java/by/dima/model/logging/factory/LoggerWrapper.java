package by.dima.model.logging.factory;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.logging.*;

public class LoggerWrapper {
    private static FileHandler fileHandler;
    private static Logger logger;
    private static ConsoleHandler consoleHandler;

    public static Logger getLogger() {
        logger = Logger.getLogger("client-log");
        logger.setUseParentHandlers(false);

        consoleHandler = new ConsoleHandler();
        try {
            fileHandler = new FileHandler("app.log");
        } catch (IOException e) {
            System.err.println("Работа вашей программы будет без отслеживания ошибок!");
        }

        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(new SimpleFormatter());

        consoleHandler.setLevel(Level.FINE);
        consoleHandler.setFilter(record -> (record.getLevel() == Level.FINE) || record.getLevel() == Level.INFO);
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
        return logger;
    }

}
