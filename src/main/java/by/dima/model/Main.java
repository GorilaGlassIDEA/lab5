package by.dima.model;

import by.dima.model.common.AnswerDTO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Client client = (Client) context.getBean("client");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            command = command.strip();
            System.out.println("Команда которая отправлена: " + command);
            AnswerDTO answerDTO = client.sendCommandReceiveAnswer(command);
            System.out.println(answerDTO.getAnswer());
        }

    }
}