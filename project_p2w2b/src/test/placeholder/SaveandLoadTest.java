package placeholder;

import exception.InvalidDataException;
import exception.ItemLoadException;
import model.*;
import observe.Printer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SaveandLoadTest {
    private Load load;
    private ToDoList todo;
    private Save save;
    private Location l;
    private ArrayList<Item> items;
    private Map<Location,ArrayList<Item>> map;
    private Printer printer;

    @BeforeEach
    public void setup() throws IOException {
        printer = new Printer();
        save = new Save();
        load = new Load();
        items = new ArrayList<>();
    }

    @Test
    public void loadTest() throws IOException {
        try {
            todo = load.load(printer);
        } catch (InvalidDataException e) {
            fail();
        } catch (ItemLoadException e) {
            fail();
        }
        l = todo.getLocationList().get(0);
        assertEquals("cpsc",l.getLocaion());
        assertEquals("lab",todo.getTodoList().get(l).get(0).getName());
        assertEquals("10 / 09 / 2020",todo.getTodoList().get(l).get(0).getDueDate());
        assertEquals("Regular",todo.getTodoList().get(l).get(0).getIsUrgentResult());
    }

    @Test
    public void splitOnSpaceTest() {
        ArrayList<String> strings = load.splitOnSpace("1 / 2 / 3");
        assertEquals("1",strings.get(0));
        assertEquals("2",strings.get(1));
        assertEquals("3",strings.get(2));
    }

    @Test
    public void saveTest() throws IOException{
        try {
            todo = load.load(printer);
            l = new Location("math");
            todo.addLocation(l);
            RegularItem regularItem= new RegularItem("assignment",1,9,2020);
            todo.addItem(l,regularItem);
            save.save(todo.getTodoList());
        } catch (InvalidDataException e) {
            fail();
        } catch (ItemLoadException e) {
            fail();
        }
        l = todo.getLocationList().get(1);
        assertEquals("math",l.getLocaion());
        assertEquals("assignment",todo.getTodoList().get(l).get(0).getName());
        assertEquals("01 / 09 / 2020",todo.getTodoList().get(l).get(0).getDueDate());
        assertEquals("Regular",todo.getTodoList().get(l).get(0).getIsUrgentResult());
    }


}
