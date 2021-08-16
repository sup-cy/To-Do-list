package model;

import java.util.*;

import exception.ItemLoadException;


public class ToDoList extends Observable {
    private Map<Location,ArrayList<Item>> todoList;
    private Map<Location,ArrayList<Item>> undoList;
    private ArrayList<Location> loc;
    private final int maxItem = 5;

    //MODEFIC:this
    //EFFECTS:Create two hashmap
    public ToDoList(Observer observer) {
        todoList = new HashMap<>();
        undoList = new HashMap<>();
        loc = new ArrayList<>();
        addObserver(observer);
    }

    //MODEFIC:this
    //EFFECTS:create the location
    public void addLocation(Location l) {
        if (!todoList.containsKey(l)) {
            todoList.put(l, new ArrayList<Item>());
            loc.add(l);
        }
    }

    //MODEFIC:this
    //EFFECTS:get location arraylist
    public ArrayList<Location> getLocationList() {
        return loc;
    }

    //MODEFIC:this
    //EFFECTS:create location for undolist
    public void addUndoLocation(Location l) {
        if (!undoList.containsKey(l)) {
            undoList.put(l, new ArrayList<Item>());
        }
    }

    //MODEFIC:this
    //EFFECTS:add item to specific location
    public void addItem(Location l,Item i) throws ItemLoadException {
        ArrayList<Item> item = todoList.get(l);
        if (item.size() >= maxItem) {
            throw new ItemLoadException();
        }
        item.add(i);
        i.addLocation(l);
        setChanged();
        notifyObservers(i);
    }

//    //MODEFIC:this
//    //EFFECTS:add UrgentItem
//    public void addUrgent(String i, int d, int m, int y,Location l) throws InvalidDataException, ItemLoadException {
//        UrgentItem name = new UrgentItem(i);
//        name.setDuedate(d,m,y);
//        addItem(l,name);
//    }
//
//    //MODEFIC:this
//    //EFFECTS:add Item to regualr list
//    public void addRegualr(String i, int d, int m, int y,Location l) throws InvalidDataException, ItemLoadException {
//        RegularItem name = new RegularItem(i);
//        name.setDuedate(d,m,y);
//        addItem(l,name);
//    }

    //MODEFIC:this
    //EFFECTS:remove item
    public void removeItem(Location l,int position) {
        ArrayList<Item> item = todoList.get(l);
        addUndoLocation(l);
        ArrayList<Item> undoItem = undoList.get(l);
        item.get(position).setStatus(false);
        undoItem.add(item.get(position));
        item.get(position).location.clear();
        item.remove(position);
        int x = item.size();
        String n = l.getLocaion();
        System.out.println("by crossing off this item,you have " + x + " number of items left on your " + n + " list!");
    }


    //EFFECTS:get todolist
    public Map<Location,ArrayList<Item>> getTodoList() {
        return todoList;
    }

    //EFFECTS;get undolist
    public Map<Location,ArrayList<Item>> getUnDoList() {
        return undoList;
    }

    //EFFECTS;get print the ToDoList or UndoList
    public void showList(Location l, Map<Location,ArrayList<Item>> map) {
        ArrayList<Item> item = map.get(l);
        for (int i = 0; i < item.size();i++) {
            System.out.println(item.get(i).getResult());
        }
    }



//    //EFFECTS;get print the undolist
//    public void showUndo(Location l) {
//        ArrayList<Item> item = undoList.get(l);
//        for (int i = 0; i < item.size(); i++) {
//            System.out.println(item.get(i).getResult());
//        }
//    }

}

