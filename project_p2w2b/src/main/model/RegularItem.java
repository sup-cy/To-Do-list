package model;

import exception.InvalidDataException;

public class RegularItem extends Item {

    public RegularItem(String item) {
        super(item);
        setUrgent(false);
    }

    public RegularItem(String item,int d,int m, int y) throws InvalidDataException {
        super(item,d,m,y);
        setUrgent(false);
    }

    //EFFECTS: return regular
    @Override
    public String getIsUrgentResult() {
        return "Regular";
    }

//    public String getResult() {
//        String result = " Item: " + name + " Status: " + getStatusResult() + " Due Data: " + getDuedate();
//        return getIsUrgentResult() + " " + result;
//    }

    //EFFECTS:Override the toString
    @Override
    public String toString() {
        return "RegularItem : name = " + name;
    }
}
