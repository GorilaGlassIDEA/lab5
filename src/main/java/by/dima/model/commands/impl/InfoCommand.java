package by.dima.model.commands.impl;


/**
 * Команда выводящая информацию по всем элементам коллекции на данный момент
 */
public class InfoCommand extends CommandAbstract {

    public InfoCommand(Long userId) {
        super(userId, "info");
    }

}
