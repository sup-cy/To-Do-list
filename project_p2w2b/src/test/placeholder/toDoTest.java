package placeholder;
import exception.*;
import model.*;
import observe.Printer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class toDoTest {
    private ToDoList todo;
    private Location l;
    private Location l1;
    private ArrayList<Location> location;
    private Printer printer;

    @BeforeEach
    public void setup() {
        printer = new Printer();
        todo = new ToDoList(printer);
        l = new Location("math");
        l1 = new Location("cpsc");
        todo.addUndoLocation(l);
        todo.addLocation(l);
        location = new ArrayList<>();
    }

    @Test
    public void addLocationTest() {
        todo.addLocation(l1);
        assertEquals(2,todo.getTodoList().size());
        todo.addLocation(l);
        assertEquals(2,todo.getTodoList().size());
    }

    @Test
    public void addUndoLocationTest() {
        todo.addUndoLocation(l1);
        assertEquals(2,todo.getUnDoList().size());
    }

    @Test
    public void addItemTest() {
        Item i = new RegularItem("lab");
        try {
            todo.addItem(l, i);
        } catch (ItemLoadException e) {
            fail();
        }
        printer.getStatic();
        assertEquals(1,todo.getTodoList().get(l).size());
    }

    @Test
    public void testAddItem() {
        try {
            RegularItem regularItem = new RegularItem("one",15,10,2019);
            UrgentItem urgentItem = new UrgentItem("two",15,10,2019);
            todo.addLocation(l1);
            todo.addItem(l,regularItem);
            todo.addItem(l1,urgentItem);
        } catch (InvalidDataException e) {
            fail();
        } catch (ItemLoadException e) {
            fail();
        }
        assertEquals(1, todo.getTodoList().get(l).size());
        assertEquals(1, todo.getTodoList().get(l1).size());
    }

    @Test
    public void testRemove() {
        try {
            RegularItem regular = new RegularItem("one",10,10,2020);
            todo.addItem(l,regular);
        } catch (InvalidDataException e) {
            fail();
        } catch (ItemLoadException e) {
            fail();
        }
        todo.removeItem(l,0);
        assertEquals(0, todo.getTodoList().get(l).size());
        assertEquals(1, todo.getUnDoList().get(l).size());
    }


    @Test
    public void testShowList() {
        try {
            RegularItem regular = new RegularItem("one",15,10,2019);
            RegularItem regular1 = new RegularItem("two",10,10,2020);
            UrgentItem urgent = new UrgentItem("three",1,8,2020);
            todo.addItem(l,regular);
            todo.addItem(l,regular1);
            todo.addItem(l,urgent);
        } catch (InvalidDataException e) {
            fail();
        } catch (ItemLoadException e) {
            fail();
        }
        todo.showList(l,todo.getTodoList());
        todo.removeItem(l,0);
        todo.removeItem(l,0);
        todo.showList(l,todo.getUnDoList());
    }


    @Test
    public void getTodoListTest() {
        try {
            RegularItem regular = new RegularItem("two",10,10,2020);
            UrgentItem urgent = new UrgentItem("three",1,8,2020);
            todo.addItem(l,regular);
            todo.addItem(l,urgent);
        } catch (InvalidDataException e) {
            fail();
        } catch (ItemLoadException e) {
            fail();
        }
        assertEquals(1, todo.getTodoList().size());
    }
    @Test
    public void getUndoListTest() {
        try {
            RegularItem regular1 = new RegularItem("two",10,10,2020);
            UrgentItem urgent = new UrgentItem("three",1,8,2020);
            todo.addLocation(l1);
            todo.addItem(l,regular1);
            todo.addItem(l1,urgent);
            todo.removeItem(l,0);
            todo.removeItem(l1,0);
        } catch (InvalidDataException e) {
            fail();
        } catch (ItemLoadException e) {
            fail();
        }
        assertEquals(2, todo.getUnDoList().size());
    }

    @Test
    public void showTodoTest() {
        try {
            RegularItem regular1 = new RegularItem("two",10,10,2020);
            UrgentItem urgent = new UrgentItem("three",1,8,2020);
            todo.addItem(l,regular1);
            todo.addItem(l,urgent);
        } catch (InvalidDataException e) {
            fail();
        } catch (ItemLoadException e) {
            fail();
        }
        todo.showList(l,todo.getTodoList());
    }

    @Test
    public void showUnDoTest() {
        try {
            RegularItem regular1 = new RegularItem("two",10,10,2020);
            UrgentItem urgent = new UrgentItem("three",1,8,2020);
            todo.addItem(l,regular1);
            todo.addItem(l,urgent);
            todo.removeItem(l,0);
            todo.removeItem(l,0);
        } catch (InvalidDataException e) {
            fail();
        } catch (ItemLoadException e) {
            fail();
        }
        todo.showList(l,todo.getUnDoList());
    }

    @Test
    public void regularItemLoadExceptionTest() {
        try {
            RegularItem regular1 = new RegularItem("1",10,10,2020);
            RegularItem regular2 = new RegularItem("2",10,10,2020);
            RegularItem regular3 = new RegularItem("3",10,10,2020);
            RegularItem regular4 = new RegularItem("4",10,10,2020);
            RegularItem regular5 = new RegularItem("5",10,10,2020);
            RegularItem regular6 = new RegularItem("6",10,10,2020);

            todo.addItem(l,regular1);
            todo.addItem(l,regular2);
            todo.addItem(l,regular3);
            todo.addItem(l,regular4);
            todo.addItem(l,regular5);
            todo.addItem(l,regular6);
            fail();
        } catch (ItemLoadException e) {

        } catch (InvalidDataException e) {
            fail();
        }

    }

    @Test
    public void urgentItemLoadExceptionTest() {
        try {
            UrgentItem urgent1 = new UrgentItem("1",1,8,2020);
            UrgentItem urgent2 = new UrgentItem("2",1,8,2020);
            UrgentItem urgent3 = new UrgentItem("3",1,8,2020);
            UrgentItem urgent4 = new UrgentItem("4",1,8,2020);
            UrgentItem urgent5 = new UrgentItem("5",1,8,2020);
            UrgentItem urgent6 = new UrgentItem("6",1,8,2020);
            todo.addItem(l,urgent1);
            todo.addItem(l,urgent2);
            todo.addItem(l,urgent3);
            todo.addItem(l,urgent4);
            todo.addItem(l,urgent5);
            todo.addItem(l,urgent6);
            fail();
        } catch (ItemLoadException e) {

        } catch (InvalidDataException e) {
            fail();
        }
    }

    @Test
    public void testInvalidDayTest1() {
        try {
            UrgentItem urgent1 = new UrgentItem("1",33,8,2020);
            todo.addItem(l,urgent1);
            fail();
        } catch (ItemLoadException e) {
            fail();
        } catch (InvalidDataException e) {
            e.showMessage();
        }
    }

    @Test
    public void testInvalidDayTest2() {
        try {
            UrgentItem urgent1 = new UrgentItem("1",-10,8,2020);
            todo.addItem(l,urgent1);
            fail();
        } catch (ItemLoadException e) {
            fail();
        } catch (InvalidDataException e) {
            e.showMessage();
        }
    }

    @Test
    public void testInvalidMonthTest1(){
        try {
            UrgentItem urgent1 = new UrgentItem("1",3,80,2020);
            todo.addItem(l,urgent1);
            fail();
        } catch (ItemLoadException e) {
            fail();
        } catch (InvalidDataException e) {
            e.showMessage();
        }
    }

    @Test
    public void testInvalidMonthTest2(){
        try {
            UrgentItem urgent1 = new UrgentItem("1",33,-8,2020);
            todo.addItem(l,urgent1);
            fail();
        } catch (ItemLoadException e) {
            fail();
        } catch (InvalidDataException e) {
            e.showMessage();
        }
    }

    @Test
    public void testInvalidYearTest(){
        try {
            UrgentItem urgent1 = new UrgentItem("1",33,8,2000);
            todo.addItem(l,urgent1);
            fail();
        } catch (ItemLoadException e) {
            fail();
        } catch (InvalidDataException e) {
           e.showMessage();
        }
    }
    @Test
    public void testGetLocationList() {
        todo.addLocation(l1);
        location = todo.getLocationList();
        assertEquals(2,location.size());
    }
}
