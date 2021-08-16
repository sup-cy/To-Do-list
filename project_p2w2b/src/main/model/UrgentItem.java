package model;

import exception.InvalidDataException;

public class UrgentItem extends Item {

    public UrgentItem(String item) {
        super(item);
        setUrgent(true);
    }

    public UrgentItem(String item,int d,int m,int y) throws InvalidDataException {
        super(item, d, m, y);
        setUrgent(true);
    }

    //EFFECTS: return urgent
    @Override
    public String getIsUrgentResult() {
        return "URGENT!!!";
    }

//    public String getResult() {
//        String result = " Item: " + name + " Status: " + getStatusResult() + " Due Data: " + getDuedate();
//        return getIsUrgentResult() + " " + result;
//    }

    //EFFECTS: Override the toString method
    @Override
    public String toString() {
        return "UrgentItem : name = " + name;
    }
}
