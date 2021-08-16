package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



import exception.InvalidDayException;
import exception.InvalidMonthException;
import exception.InvalidYearException;
import exception.InvalidDataException;


public abstract class Item {
    protected String name;
    protected  boolean status;
    protected Calendar duedate;
    protected boolean isUrgent;
    protected ArrayList<Location> location;



    //MODIFIES:this
    //EFFECTS:Create the constructor of class Item
    public  Item(String things) {
        location = new ArrayList<>();
        this.name = things;
        status = true;
    }

    public Item(String things,int d,int m,int y) throws InvalidDataException {
        location = new ArrayList<>();
        this.name = things;
        status = true;
        setDueDate(d,m,y);

    }

    //MODIFIES:this
    //EFFECTS:add location to item
    public void addLocation(Location l) {
        if (!location.contains(l)) {
            location.add(l);
            l.addItem(this);
        }
    }

    //EFFECTS:get item's location
    public ArrayList<Location> getLocation() {
        return location;
    }

    //MODIFIES:this
    //EFFECTS:change the value of isUrgebt
    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }

    //EFFECTS:get the value of isUrgent
    public boolean getUrgent() {
        return isUrgent;
    }

    //MODIFIES:this
    //EFFECTS:change name's value
    public void setName(String name) {
        this.name = name;
    }

    //EFFECTS:return name's value
    public String getName() {
        return this.name;
    }

    //MODIFIES:this
    //EFFECTS:Change the field status's value
    public void setStatus(boolean status) {
        this.status = status;
    }

    //EFFECTS:return status
    public boolean getStatus() {
        return status;
    }

    //MODIFIES:this
    //EFFECTS:set the date
    public void setDueDate(int day, int month, int year) throws InvalidDataException {
        duedate = Calendar.getInstance();
        duedate.set(Calendar.SECOND,59);
        duedate.set(Calendar.MINUTE,59);
        duedate.set(Calendar.HOUR_OF_DAY,23);
        if (day  < 0 || day > 31) {
            throw new InvalidDayException();
        }
        duedate.set(Calendar.DAY_OF_MONTH,day);
        if (month < 0 || month > 12) {
            throw new InvalidMonthException();
        }
        duedate.set(Calendar.MONTH,(month - 1));
        if (year < 2018) {
            throw new InvalidYearException();
        }
        duedate.set(Calendar.YEAR,year);
    }

    //EFFECT:get the date
    public String getDueDate() {
        Date date = duedate.getTime();
        String time = new SimpleDateFormat("dd / MM / yyyy").format(date);
        return time;
    }

    //EFFECT:return result of object
    public String getStatusResult() {
        if (this.status == true) {
            return "ToDo";
        } else {
            return "UnDo";
        }
    }

    //EFFECTS:return the result of isUrgent
    public abstract String getIsUrgentResult();

    //EFFECTS: return Item basic information
    public String getResult() {
        String result = " Item: " + name + " Status: " + getStatusResult() + " Due Data: " + getDueDate();
        return getIsUrgentResult() + " " + result;
    }

}

