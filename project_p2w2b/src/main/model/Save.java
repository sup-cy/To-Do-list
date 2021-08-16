package model;


import observe.Printer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;


public class Save {
    //MODIFIES:this
    //EFFECTS:save new map set to file
    public void save(Map<Location,ArrayList<Item>> item) throws IOException {
        PrintWriter writer = new PrintWriter("inputfile.txt","UTF-8");
        for (Location l:item.keySet()) {
            ArrayList<Item> items = item.get(l);
            writer.println(l.getLocaion());
            for (Item i:items) {
                writer.println(i.getName() + " / " + i.getDueDate() + " / " + i.getIsUrgentResult());
            }
        }
        writer.close();
    }
}
