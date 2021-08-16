package model;

import exception.InvalidDataException;
import exception.ItemLoadException;

import java.io.IOException;
import java.util.Observer;

public interface Loadable {
    public ToDoList load(Observer observe) throws IOException, InvalidDataException, ItemLoadException;
}
