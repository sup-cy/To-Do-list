package placeholder;

import model.Item;
import model.Location;
import model.RegularItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {
    private Location location;
    private ArrayList<Item> item;

    @BeforeEach
    public void setup() {
        location = new Location("cpsc");
    }

    @Test
    public void getLocationTest() {
        assertEquals("cpsc",location.getLocaion());
    }

    @Test
    public void setLocationTest() {
        location.setLocaion("math");
        assertEquals("math",location.getLocaion());
    }

    @Test
    public void addItemTest() {
        RegularItem regular = new RegularItem("lab");
        location.addItem(regular);
        item = location.getItem();
        assertEquals(1,item.size());
    }

    @Test
    public void getItemTest() {
        RegularItem regular1 = new RegularItem("lab1");
        RegularItem regular2 = new RegularItem("lab2");
        RegularItem regular3 = new RegularItem("lab3");
        location.addItem(regular1);
        location.addItem(regular2);
        location.addItem(regular3);
        item = location.getItem();
        assertEquals(3,item.size());
    }

    @Test
    public void equalTest() {
        Location l = new Location("cpsc");
        Location s = null;
        assertEquals(true,l.equals(location));
        RegularItem regularItem = new RegularItem("item");
        assertEquals(false,l.equals(regularItem));
        assertEquals(false,l.equals(s));
    }
}
