package ui;

import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.round;

// Represents a window to create a new user
public class NewUser extends JFrame implements ActionListener {
    JLabel name = new JLabel("Name");
    JLabel gender = new JLabel("Gender");
    JLabel height = new JLabel("Height (in centimeters)");
    JLabel weight = new JLabel("Weight (in kilograms)");
    JLabel age = new JLabel("Age");
    JLabel activityLevelLabel = new JLabel("Choose your activity level");
    JTextField nameField = new JTextField();
    JLabel bmr = new JLabel("BMR");
    JLabel activityFactor = new JLabel("Activity Factor");

    JComboBox activityLevel;
    JComboBox genderComboBox;
    JButton submit = new JButton("Submit");
    JTextField heightField = new JTextField();
    JTextField weightField = new JTextField();
    JTextField ageField = new JTextField();
    JPanel intensityPanel;
    WindowListener exitListener;
    JPanel namePanel;
    JPanel heightPanel;
    JPanel weightPanel;
    JPanel genderPanel;
    JPanel agePanel;
    JFrame previousFrame;
    private static int tde;

    // MODIFIES: this
    // EFFECTS: creates the GUI window
    public NewUser(JFrame frame) {
        super("Create New User");
        previousFrame = frame;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
        setLayout(new GridLayout(7, 1));
        setLocationRelativeTo(null);

        String[] availableGenders = {"Male", "Female"};
        String[] intensity = {"A. Sedentary",
                "B. Light Exercise",
                "C. Moderate Exercise",
                "D. Extreme Exercise",
                "E. Athlete"
        };

        genderComboBox = new JComboBox(availableGenders);
        activityLevel = new JComboBox(intensity);

        setLabels();
        initialisePanels();
        addFields();

        add(submit);
        submit.addActionListener(this);
        makeClosingAction();
    }

    // MODIFIES: this
    // EFFECTS: sets the labels for different text fields
    private void setLabels() {
        name.setLabelFor(nameField);
        gender.setLabelFor(genderComboBox);
        height.setLabelFor(heightField);
        weight.setLabelFor(weightField);
        age.setLabelFor(ageField);
        activityLevelLabel.setLabelFor(activityLevel);
        bmr.setLabelFor(bmr);
        activityFactor.setLabelFor(activityFactor);
    }

    // MODIFIES: this
    // EFFECTS: initialises all the panels
    private void initialisePanels() {
        namePanel = new JPanel();
        heightPanel = new JPanel();
        weightPanel = new JPanel();
        genderPanel = new JPanel();
        agePanel = new JPanel();
        intensityPanel = new JPanel();
    }

    // MODIFIES: this
    // EFFECTS: adds the fields to the panels and the panels to the frame
    private void addFields() {
        add(namePanel);
        nameField.setColumns(30);
        namePanel.add(name);
        namePanel.add(nameField);

        add(heightPanel);
        heightField.setColumns(10);
        heightPanel.add(height);
        heightPanel.add(heightField);

        add(weightPanel);
        weightField.setColumns(10);
        weightPanel.add(weight);
        weightPanel.add(weightField);

        add(genderPanel);
        genderPanel.add(gender);
        genderPanel.add(genderComboBox);

        add(agePanel);
        ageField.setColumns(10);
        agePanel.add(age);
        agePanel.add(ageField);

        add(intensityPanel);
        intensityPanel.add(activityLevelLabel);
        intensityPanel.add(activityLevel);
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
    // EFFECTS: creates a new person and adds that person to people and then opens new Window
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit && checkNotNull()) {
            String name = nameField.getText();
            if (alreadyExists(name)) {
                JOptionPane.showMessageDialog(null, "Someone with that name already exists in records!",
                        "Existing name chosen", JOptionPane.PLAIN_MESSAGE, HealthApp.image);
                return;
            }

            Person newPerson = makeNewPerson(name);
            HealthApp.people.addPerson(newPerson);
            tde = (int) round(newPerson.factorCalc(setGender(genderComboBox.getSelectedIndex()),
                    setActivityLevelLabel(activityLevel.getSelectedIndex()))
                    *
                    newPerson.bmrMethod(setGender(genderComboBox.getSelectedIndex()),
                            Integer.parseInt(weightField.getText()), Integer.parseInt(heightField.getText()),
                            Integer.parseInt(ageField.getText())));

            JOptionPane.showMessageDialog(null,
                    "Your Total Daily Energy Expenditure is " + tde + " calories"
                            + "\n Eat more calories to gain weight, eat less calories to lose weight!", "TDEE",
                    JOptionPane.PLAIN_MESSAGE, HealthApp.image);

            new ModifyUser(newPerson);
            previousFrame.dispose();
            dispose();
        }
        if (e.getSource() == submit && !checkNotNull()) {
            JOptionPane.showMessageDialog(null, "Please enter all the specified fields.",
                    "Enter every input",
                    JOptionPane.PLAIN_MESSAGE, HealthApp.image);
        }
    }





    // EFFECTS: makes a new person with the inputs specified by the user
    private Person makeNewPerson(String name) {
        int age = Integer.parseInt(ageField.getText());
        int height = Integer.parseInt(heightField.getText());
        int weight = Integer.parseInt(weightField.getText());
        String gender = setGender(genderComboBox.getSelectedIndex());
        String activityLevelLabel = setActivityLevelLabel(activityLevel.getSelectedIndex());

        return new Person(name, age, height, weight, gender);
    }

    public static int getTde() {
        return tde;
    }

    // REQUIRES: g is 0,1,2,3,4
    // EFFECTS: returns "Male" is g is 0, returns "Female" otherwise
    private String setActivityLevelLabel(int g) {
        if (g == 0) {
            return "A";
        } else if (g == 1) {
            return "B";
        } else if (g == 2) {
            return "C";
        } else if (g == 3) {
            return "D";
        } else {
            return "E";
        }
    }

    // EFFECTS: If a user with name already exists in people then returns true, else returns false
    private boolean alreadyExists(String name) {
        for (Person p : HealthApp.people.getPeep()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    // REQUIRES: g is 0 or 1
    // EFFECTS: returns "Male" is g is 0, returns "Female" otherwise
    private String setGender(int g) {
        if (g == 0) {
            return "M";
        } else {
            return "F";
        }
    }

    // EFFECTS: if all the input fields are not null then returns true, otherwise false
    private boolean checkNotNull() {
        return !nameField.getText().isEmpty()
                && !ageField.getText().isEmpty()
                && !weightField.getText().isEmpty()
                && !heightField.getText().isEmpty();
    }

}