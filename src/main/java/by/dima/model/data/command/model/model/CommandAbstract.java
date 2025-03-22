package by.dima.model.data.command.model.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class CommandAbstract implements Command {

    private String key;
    private String help;

}
