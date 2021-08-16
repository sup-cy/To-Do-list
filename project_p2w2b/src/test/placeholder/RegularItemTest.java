package placeholder;
import exception.InvalidDataException;
import exception.InvalidDayException;
import exception.InvalidMonthException;
import exception.InvalidYearException;
import model.Item;
import model.Location;
import model.RegularItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RegularItemTest {
    private Item item1;
    private ArrayList<Location> location;

    @BeforeEach
    public void setup(){
        item1 = new RegularItem("one");
        try {
            item1.setDueDate(5,10,2019);
        } catch (InvalidDataException e) {
            fail();
        }
        location = new ArrayList<>();
    }

    @Test
    public void getNameTest() {
        assertEquals("one",item1.getName());
    }

    @Test
    public void setNameTest() {
        item1.setName("three");
        assertEquals("three",item1.getName());
    }

    @Test
    public void getStatusTest() {
        assertEquals(true,item1.getStatus());
        item1.setStatus(false);
        assertEquals(false,item1.getStatus());
    }

    @Test
    public void setStatusTest() {
        item1.setStatus(false);
        assertEquals(false,item1.getStatus());
    }
    @Test
    public void gerStatusResultTest() {
        assertEquals("ToDo",item1.getStatusResult());
        item1.setStatus(false);
        assertEquals("UnDo",item1.getStatusResult());
    }

    @Test
    public void getDueDateTest() {
        assertEquals("05 / 10 / 2019",item1.getDueDate());
    }

    @Test
    public void setDueDateTest() {
        try {
            item1.setDueDate(15,1,2020);
        } catch (InvalidDataException e) {
            fail();
        }
        assertEquals("15 / 01 / 2020",item1.getDueDate());
    }
    @Test
    public void setUrgentTest() {
        assertEquals(false,item1.getUrgent());
        item1.setUrgent(true);
        assertEquals(true,item1.getUrgent());
    }


    @Test
    public void getIsUrgentResultTest() {
        assertEquals("Regular",item1.getIsUrgentResult());
    }

    @Test
    public void getUrgentTest() {
        assertEquals(false,item1.getUrgent());
        item1.setUrgent(true);
        assertEquals(true,item1.getUrgent());
    }
    @Test
    public void getResultTest() {
        assertEquals("Regular  Item: one Status: ToDo Due Data: 05 / 10 / 2019",item1.getResult());
    }

    @Test
    public void invalidDayTest1() throws InvalidDataException {
        try {
            item1.setDueDate(-1,15,2019);
            fail();
        } catch(InvalidDayException e) {
            e.showMessage();
        }
    }

    @Test
    public void invalidDayTest2() throws InvalidDataException {
        try {
            item1.setDueDate(33,15,2019);
            fail();
        } catch(InvalidDayException e) {
            e.showMessage();
        }
    }

    @Test
    public void invalidMonthTest1() throws InvalidDataException {
        try {
            item1.setDueDate(1,150,2019);
            fail();
        } catch(InvalidMonthException e) {
            e.showMessage();
        }
    }

    @Test
    public void invalidMonthTest2() throws InvalidDataException {
        try {
            item1.setDueDate(1,-1,2019);
            fail();
        } catch(InvalidMonthException e) {
            e.showMessage();
        }
    }


    @Test
    public void invalidYearTest() throws InvalidDataException {
        try {
            item1.setDueDate(1,11,2000);
            fail();
        } catch(InvalidYearException e) {
            e.showMessage();
        }
    }
    @Test
    public void invalidDateTest() {
        try{
            item1.setDueDate(-1,150,199);
            fail();
        } catch (InvalidDataException e) {
            e.showMessage();
        }
    }

    @Test
    public void testAddLocation() {
        Location l = new Location("math");
        item1.addLocation(l);
        location = item1.getLocation();
        assertEquals(1,location.size());
    }

    @Test
    public void testgetLocation() {
        Location l1 = new Location("math");
        Location l2 = new Location("Cpsc");
        Location l3 = new Location("Eosc");
        item1.addLocation(l1);
        item1.addLocation(l2);
        item1.addLocation(l3);
        location = item1.getLocation();
        assertEquals(3,location.size());
    }

    @Test
    public void toStringTest() {
        String r = item1.toString();
        assertEquals("RegularItem : name = one",r);
    }
}
