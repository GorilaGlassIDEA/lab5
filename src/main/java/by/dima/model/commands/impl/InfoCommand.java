package by.dima.model.commands.impl;


import by.dima.model.commands.model.CommandAbstract;

/**
 * Команда выводящая информацию по всем элементам коллекции на данный момент
 */
public class InfoCommand extends CommandAbstract {

    public InfoCommand(Long userId) {
        super(userId, "info");
    }

}
