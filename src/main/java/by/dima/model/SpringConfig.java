package by.dima.model;

import by.dima.model.logging.factory.LoggerWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;
import java.util.logging.Logger;

@Configuration
@ComponentScan("by.dima.model")
public class SpringConfig {

    @Bean
    public Scanner scannerBean() {
        return new Scanner(System.in);
    }

    @Bean
    public ObjectMapper mapperBean() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    @Autowired
    public Logger loggerBean(LoggerWrapper loggerWrapper) {
        return loggerWrapper.logger;
    }
}
