package ui;

import exception.InvalidDataException;
import exception.ItemLoadException;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPage extends JFrame implements ActionListener {
    private JLabel setItem;
    private JLabel setDay;
    private JLabel setMonth;
    private JLabel setYear;
    private JLabel setLocation;
    private JLabel status;
    private JCheckBox urgent;
    private JCheckBox regular;
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;
    private JTextField field5;
    private JButton add;
    private ToDoList list;
    private String item;
    private int day;
    private int month;
    private int year;
    private Location location;

    public AddPage(ToDoList todo) {
        list = todo;
        JFrame frame = new JFrame("Add Page");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setLayout(new FlowLayout(30,90,40));
        field1 = new JTextField(20);
        field2 = new JTextField(10);
        field3 = new JTextField(10);
        field4 = new JTextField(10);
        field5 = new JTextField(10);
        setItem = new JLabel("Item Name");
        setDay = new JLabel("Due Day:");
        setMonth = new JLabel("Due Month:");
        setYear = new JLabel("Due Year:");
        setLocation = new JLabel("Location Name:");
        add = new JButton("ADD");
        urgent = new JCheckBox("Urgent");
        regular = new JCheckBox("Regular");
        status = new JLabel(" ");
        frame.getContentPane().add(setLocation);
        frame.getContentPane().add(field5);
        frame.getContentPane().add(setItem);
        frame.getContentPane().add(field1);
        frame.getContentPane().add(setDay);
        frame.getContentPane().add(field2);
        frame.getContentPane().add(setMonth);
        frame.getContentPane().add(field3);
        frame.getContentPane().add(setYear);
        frame.getContentPane().add(field4);
        frame.getContentPane().add(urgent);
        urgent.setActionCommand("urgent");
        frame.getContentPane().add(regular);
        regular.setActionCommand("regular");
        frame.getContentPane().add(add);
        add.setActionCommand("add");
        frame.add(status);
        add.addActionListener(this);
        urgent.addActionListener(this);
        regular.addActionListener(this);
        frame.setSize(500,500);
        frame.setVisible(true);
    }


    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            item = field1.getText();
            day = Integer.parseInt(field2.getText());
            month = Integer.parseInt(field3.getText());
            year = Integer.parseInt(field4.getText());
            String locations = field5.getText();
            location = new Location(locations);
            if (urgent.isSelected()) {
                addUrgentItem();
            } else if (regular.isSelected()) {
                addRegularItem();
            }
        } else if (e.getActionCommand().equals("urgent")) {
            regular.setSelected(false);
        } else if (e.getActionCommand().equals("regular")) {
            urgent.setSelected(false);
        }
    }

    //MODIFIES:this
    //EFFECTS: add regular item to map set
    public void addRegularItem() {
        try {
            Item i = new RegularItem(item, day, month, year);
            list.addLocation(location);
            list.addItem(location, i);
            status.setText("Add successful");
        } catch (InvalidDataException ex) {
            status.setText(ex.getMessage());
        } catch (ItemLoadException e) {
            status.setText("Too much Item");
        }
    }

    //MODIFIES:this
    //EFFECTS: add urgent item to map set
    public void addUrgentItem() {
        try {
            Item i = new UrgentItem(item, day, month, year);
            list.addLocation(location);
            list.addItem(location, i);
            status.setText("Add successful");
        } catch (InvalidDataException ex) {
            add.setText(ex.getMessage());
        } catch (ItemLoadException e) {
            add.setText("Too much Item");
        }
    }
}
