package by.dima.model.data.command.impl;

import by.dima.model.data.abstracts.model.Models;
import by.dima.model.data.command.model.Command;
import by.dima.model.data.route.model.main.Route;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class InfoCommand implements Command {
    private String key = "info";
    private Models models;

    public InfoCommand(Models models) {
        this.models = models;
    }


    @Override
    public void execute() {
        System.out.println("Type: " + models.getType());
        System.out.println("Date: " + models.getZonedDateTime());
        System.out.println("Size: " + models.sizeArray());
    }

}
