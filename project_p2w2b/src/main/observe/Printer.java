package observe;

import model.Item;
import model.Location;
import model.ToDoList;
import java.util.Observable;
import java.util.Observer;

public class Printer implements Observer {
    private int regularNumber;
    private int urgentNumber;

    public Printer() {
        regularNumber = 0;
        urgentNumber = 0;
    }

    //EFFECTS:Override the update method
    @Override
    public void update(Observable o, Object arg) {
        ToDoList i = (ToDoList) o;
        trackNumber(i);
        System.out.println(arg);
    }

    //MODIFIES:this
    //EFFECTS:count the regular item number and urgent item number from file
    public void trackNumber(ToDoList todo) {
        regularNumber = 0;
        urgentNumber = 0;
        for (Location l:todo.getLocationList()) {
            for (Item item : todo.getTodoList().get(l)) {
                if (item.getUrgent() == true) {
                    urgentNumber++;
                } else {
                    regularNumber++;
                }
            }
        }
    }

    //EFFECTS: print the regular item number and urgent item number
    public void getStatic() {
        System.out.println("---Total Item in File---");
        System.out.println("RegularItem: " + regularNumber);
        System.out.println("UrgentItem: " + urgentNumber);
    }

    //EFFECTS:return regular item number and urgent item number information
    public String getStatus() {
        String s = "RegularItem: " + regularNumber + "  " + "UrgentItem: " + urgentNumber;
        return s;
    }
}
