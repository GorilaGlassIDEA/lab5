package by.dima.model.logging.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.logging.*;

public class LoggingFactoryForBeanSpring {
    private FileHandler fileHandler;
    public Logger logger;
    private ConsoleHandler consoleHandler;


    public Logger initLogger() {
        logger = Logger.getLogger("client-log");
        logger.setUseParentHandlers(false);

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
        logger.addHandler(fileHandler);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.FINE);
        return logger;
    }

    public void destroyLogger() {
        consoleHandler.close();
        fileHandler.close();
    }

}
