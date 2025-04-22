package by.dima.model.commands.model;

/** Данный интерфейс нужен для хранения имени аргумента, для удобного сравнения и замены при желании
 * Например если у нас есть аргумент help и мы хотим поменять его имя на helping то достаточно вызвать метод
 * setName() у соответствующего класса-команды реализующего этот интерфейс*/
public interface Nameable {
    void setKey(String name);
    String getKey();
}
