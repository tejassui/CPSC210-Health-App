package ui;

import model.ClassDiet;
import model.DietPlans;
import model.Event;
import model.EventLog;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Represents a frame to add a new diet
public class AddNewPlan extends JFrame implements ActionListener {

    Person person;
    JButton submit = new JButton();
    JLabel type = new JLabel();
    JLabel diet = new JLabel();
    JComboBox typeComboBox;
    JPanel typePanel;
    JPanel dietPanel;
    WindowListener exitListener;

    // MODIFIES: this
    // EFFECTS: creates a GUI window
    public AddNewPlan(Person p) {
        super("Add New Plan");
        setLayout(new GridLayout(4, 1));
        person = p;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        typePanel = new JPanel();
        dietPanel = new JPanel();
        String[] availableTypes = {"Vegetarian", "Non-vegetarian", "Vegan"};
        typeComboBox = new JComboBox(availableTypes);
        add(typePanel);
        add(dietPanel);
        typePanel.add(type);
        typePanel.add(typeComboBox);
        initialiseLayout();

        makeClosingAction();
    }

    // MODIFIES: this
    // EFFECTS: initialises the whole window layout
    private void initialiseLayout() {
        type.setText("Type of diet plan");
        type.setLabelFor(typeComboBox);
       // duration.setLabelFor(durationField);
       // durationField.setColumns(20);
        typePanel.add(type);
        dietPanel.add(diet);
       // durationPanel.add(durationField);

        submit.addActionListener(this);
        submit.setText("Submit");
        add(submit);

    }

    private ClassDiet makeNewDP() {
        String type = setType(typeComboBox.getSelectedIndex());
        String diet = setDiet(String.valueOf(typeComboBox.getSelectedIndex()));

        return new ClassDiet(type, diet);
    }

    // REQUIRES: g is 0, 1 or 2
    // EFFECTS: returns "Vegetarian" g=0, returns "Non-vegetarian" g=1, returns "Vegan" otherwise
    private String setType(int g) {
        if (g == 0) {
            return "Vegetarian";
        } else if (g == 1) {
            return "Non-vegetarian";
        } else {
            return "Vegan";
        }
    }

    private String setDiet(String s) {
        if (s == "Vegetarian") {
            return DietPlans.getVdiet();
        } else if (s == "Non-vegetarian") {
            return DietPlans.getNonvdiet();
        } else {
            return DietPlans.getVegandiet();
        }
    }

    // MODIFIES: this
    // EFFECTS: Overrides the closing action when the window is closed to
    // ask the user if they want to save the application or not
    private void makeClosingAction() {
        exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Do you want to save the application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, HealthApp.image, null, null);
                if (confirm == 0) {
                    HealthApp.saveData();
                }
                dispose();
            }
        };
        addWindowListener(exitListener);
    }


    // MODIFIES: this
    // EFFECTS: saves the application and closes it
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            person.getDietPlan().add(makeNewDP());
            HealthApp.saveData();
            EventLog.getInstance().logEvent(new Event("Added diet to diet plan"));
            dispose();
            new ModifyUser(person);
        }
    }

}
