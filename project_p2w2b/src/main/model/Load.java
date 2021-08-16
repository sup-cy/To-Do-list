package model;

import exception.InvalidDataException;
import exception.ItemLoadException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observer;


public class Load implements Loadable {
    private ToDoList todo;
    private Location location;

    @Override
    public ToDoList load(Observer observe) throws IOException, InvalidDataException, ItemLoadException {
        todo = new ToDoList(observe);
        List<String> lines = Files.readAllLines(Paths.get("inputfile.txt"));
        loadHelp(lines);
        return todo;
    }

    //MODIFIES:this
    //EFFECTS:load the mapSet from input.file
    public void loadHelp(List<String> lines) throws InvalidDataException, ItemLoadException {
        for (String list : lines) {
            ArrayList<String> partOfLine = splitOnSpace(list);
            if (partOfLine.size() < 3) {
                location = new Location(partOfLine.get(0));
                todo.addLocation(location);
                todo.getTodoList().put(location,new ArrayList<Item>());
            } else {
                String name = partOfLine.get(0);
                int day = Integer.parseInt(partOfLine.get(1));
                int month = Integer.parseInt(partOfLine.get(2));
                int year = Integer.parseInt(partOfLine.get(3));
                if (partOfLine.get(4).equals("URGENT!!!")) {
                    todo.getTodoList().get(location).add(new UrgentItem(name,day,month,year));
                } else {
                    todo.getTodoList().get(location).add(new RegularItem(name,day,month,year));
                }
            }
        }
    }

    //EFFECTS: split String line to String list
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" / ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
