package model;

import java.util.ArrayList;
import java.util.Objects;

public class Location {
    private String location;
    private ArrayList<Item> item;

    public Location(String locaion) {
        this.location = locaion;
        item = new ArrayList<>();
    }

    //EFFECTS: get location
    public String getLocaion() {
        return location;
    }

    //MODEFIC:this
    //EFFECTS:set location value
    public void setLocaion(String locaion) {
        this.location = locaion;
    }

    //MODEFIC:this
    //EFFECTS:add item to location
    public void addItem(Item m) {
        if (!item.contains(m)) {
            item.add(m);
            m.addLocation(this);
        }
    }

    //EFFECTS:get Location's Item
    public ArrayList<Item> getItem() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Location location = (Location) o;

        return this.location.equals(location.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
}
