package by.dima.model.logging.factory;

import java.io.IOException;
import java.util.logging.*;

public class LoggingFactoryForBeanSpring {
    public Logger logger;
    public FileHandler fileHandler;
    public ConsoleHandler consoleHandler;


    public Logger initLogger() {
        logger = Logger.getLogger("client-log");

        consoleHandler = new ConsoleHandler();
        try {
            fileHandler = new FileHandler("app.log");
        } catch (IOException e) {
            System.err.println("Работа вашей программы будет без отслеживания ошибок!");
        }

        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new SimpleFormatter());

        consoleHandler.setLevel(Level.FINE);
        consoleHandler.setFilter(record -> (record.getLevel() == Level.FINE) || record.getLevel() == Level.INFO);
        consoleHandler.setFormatter(new SimpleFormatter());

        logger.setLevel(Level.FINE);
        return logger;
    }

    public void destroyLogger() {
        System.out.println("Handlers to close: " + logger.getHandlers().length);
        consoleHandler.close();
        fileHandler.close();
    }

}
