package ui;

import exception.InvalidDataException;
import exception.ItemLoadException;
import model.*;
import observe.Printer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UserInput extends JFrame implements ActionListener {
    private JButton btn;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private ToDoList todo;
    private Printer printer;
    private JList location;
    private JList item;
    private JList urgentItem;
    private DefaultListModel locationModel;
    private DefaultListModel regularItemModel1;
    private DefaultListModel urgentItemModel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel status;


    public UserInput() throws InvalidDataException, IOException, ItemLoadException {
        printer = new Printer();
        Load load = new Load();
        todo = load.load(printer);
        JFrame frame = new JFrame("To Do List");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(30,90,40));
        locationModel = new DefaultListModel();
        regularItemModel1 = new DefaultListModel();
        urgentItemModel = new DefaultListModel();
        updateLocation();
        location = new JList(locationModel);
        if (locationModel.size() != 0) {
            location.setSelectedIndex(0);
        }
        location.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        updateItem(new Location((String)location.getSelectedValue()));
        item = new JList(regularItemModel1);
        item.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        urgentItem = new JList(urgentItemModel);
        urgentItem.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        status = new JLabel(" ");
        btn = new JButton("Add Item");
        btn.setActionCommand("Add");
        btn1 = new JButton("Remove Item");
        btn1.setActionCommand("Remove");
        btn2 = new JButton("Show Item");
        btn2.setActionCommand("Show");
        btn3 = new JButton("Save");
        btn3.setActionCommand("Save");
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel1.setSize(100,100);
        BoxLayout boxlayout = new BoxLayout(panel1,BoxLayout.Y_AXIS);
        panel1.setLayout(boxlayout);
        panel1.setBackground(Color.yellow);
        BoxLayout boxlayout1 = new BoxLayout(panel2,BoxLayout.Y_AXIS);
        panel2.setLayout(boxlayout1);
        panel2.setBackground(Color.yellow);
        panel2.setSize(600,500);
        panel2.add(new Label("Regular Item"));
        BoxLayout boxLayout2 = new BoxLayout(panel3,BoxLayout.Y_AXIS);
        panel3.add(new Label("Urgent Item"));
        panel3.setBackground(Color.red);
        panel3.setLayout(boxLayout2);
        panel3.setPreferredSize(new Dimension(300,200));
        frame.getContentPane().add(btn);
        frame.getContentPane().add(btn1);
        frame.getContentPane().add(btn2);
        JScrollPane pane = new JScrollPane(location);
        JScrollPane pane1 = new JScrollPane(item);
        JScrollPane pane3 = new JScrollPane(urgentItem);
        panel1.setPreferredSize(new Dimension(100,200));
        panel2.setPreferredSize(new Dimension(300,200));
        location.setFixedCellHeight(30);
        location.setFixedCellWidth(100);
        panel1.add(new JLabel("Location"));
        panel1.add(pane);
        panel2.add(pane1);
        panel3.add(pane3);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(btn3);
        frame.add(status);
        panel2.add(panel3);
        // panel3.add(urgentItem);
        btn.addActionListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        //location.addListSelectionListener((ListSelectionListener) this);
        frame.setSize(700,500);
        frame.setVisible(true);
    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            new AddPage(todo);
            playSound("Click.wav");
        } else if (e.getActionCommand().equals("Remove")) {
            removeItem(new Location((String)location.getSelectedValue()));
            playSound("Delete.wav");
        } else if (e.getActionCommand().equals("Show")) {
            updateLocation();
            if (locationModel.size() != 0 && !location.isSelectionEmpty()) {
                Location l = new Location((String) location.getSelectedValue());
                updateItem(l);
            }
            playSound("Click.wav");
        } else {
            saveToFile();
            playSound("Click.wav");
        }
    }


    //EFFECTS: update the location information to JList
    public void updateLocation() {
        for (Location l:todo.getLocationList()) {
            if (!locationModel.contains(l.getLocaion())) {
                locationModel.addElement(l.getLocaion());
            }
        }
    }

    //EFFECTS: update the specific location item information to JList
    public void updateItem(Location l) {
        regularItemModel1.clear();
        urgentItemModel.clear();
        ArrayList<Item> item = todo.getTodoList().get(l);
        for (Item i:item) {
            String s = i.getName() + " - " + i.getDueDate();
            if (i.getUrgent() == true) {
                urgentItemModel.addElement(s);
            } else {
                regularItemModel1.addElement(s);
            }
        }
    }

    //MODIFIES:this
    //EFFECTS:remove choose item
    public void removeItem(Location l) {
        Location lo = new Location((String)location.getSelectedValue());
        ArrayList<Item> i = todo.getTodoList().get(lo);
        int index = getIndex(i);
        todo.removeItem(lo,index);
        updateItem(lo);
        if (todo.getTodoList().get(lo).size() == 0) {
            locationModel.removeElement(lo.getLocaion());
            todo.getLocationList().remove(lo);
            todo.getTodoList().remove(lo);
        } else {
            updateItem(lo);
        }
    }

    //EFFECTS:get remove index
    public int getIndex(ArrayList<Item> i) {
        int index = 0;
        ArrayList<String> s = new ArrayList<>();
        if (!item.isSelectionEmpty()) {
            s = splitOnSpace(item.getSelectedValue().toString());
        } else if (!urgentItem.isSelectionEmpty()) {
            s = splitOnSpace(urgentItem.getSelectedValue().toString());
        }
        for (int a = 0; a < i.size(); a++) {
            if (s.get(0).equals(i.get(a).getName())) {
                index = a;
            }
        }
        return index;
    }

    //MODIFIES: this
    //EFFECTS: save todo map set to file
    public void saveToFile() {
        Save save = new Save();
        try {
            save.save(todo.getTodoList());
            printer.trackNumber(todo);
            printer.getStatic();
            status.setText(printer.getStatus());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //EFFECTS: play sound from sound file
    //copy from web
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    //EFFECTS: split String line to String list
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" - ");
        return new ArrayList<>(Arrays.asList(splits));
    }



    public static void main(String[] args) throws InvalidDataException, IOException, ItemLoadException {
        new UserInput();
    }
}