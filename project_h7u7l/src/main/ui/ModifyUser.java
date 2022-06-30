package ui;

import model.DietPlans;
import model.Person;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

// Represents a window asking the user if they want to add a new record or view previous records
public class ModifyUser extends JFrame implements ActionListener {

    Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
    JTextArea area = new JTextArea();
    Person person;
    JRadioButton addNewDP = new JRadioButton();
    JRadioButton viewDP = new JRadioButton();
    ButtonGroup bg = new ButtonGroup();
    WindowListener exitListener;
    JPanel p1;
    JPanel p5;
    JScrollPane scrollPanel;

    // MODIFIES: this
    // EFFECTS: creates a GUI Window
    public ModifyUser(Person p) {
        super("Modify Records Window");
        person = p;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        p1 = new JPanel(new GridLayout(2, 1));
        p1.setPreferredSize(new Dimension(100, 100));

        p5 = new JPanel();

        initialiseFields();

        makeClosingAction();
    }

    // MODIFIES: this
    // EFFECTS: initialises all the fields for the window
    private void initialiseFields() {
        p5.setBackground(Color.PINK);

        add(p1, BorderLayout.NORTH);
        add(p5);

        addNewDP.setText("Add New Diet Plan");
        addNewDP.addActionListener(this);
        addNewDP.setHorizontalAlignment(SwingConstants.CENTER);
        bg.add(addNewDP);
        p1.add(addNewDP);

        viewDP.setText("View Diet Plans");
        viewDP.addActionListener(this);
        viewDP.setHorizontalAlignment(SwingConstants.CENTER);
        bg.add(viewDP);
        p1.add(viewDP);

        area.setBorder(border);
        area.setEditable(false);
        area.setBackground(Color.white);

        scrollPanel = new JScrollPane(area);
        scrollPanel.setPreferredSize(new Dimension(600, 600));

        p5.add(scrollPanel);
        p5.setVisible(false);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewDP) {
            area.setText(getDP());
            JOptionPane.showMessageDialog(null, "Diet of user " + person.getName() + "\n"
                            + person.getDietPlan(), "DietPlan", JOptionPane.INFORMATION_MESSAGE);
            p5.setVisible(true);
        } else if (e.getSource() == addNewDP) {
            new AddNewPlan(person);
            dispose();
        }


//            if (person.getDietPlan() != null) {
//                for (ClassDiet classDiet : person.getDietPlan()) {
//                    area.append(classDiet.getTitle() + " : " + classDiet.getDiet() + "\n");
//                }
//                p5.setVisible(true);
//            } else {
//                area.setText(getDP());
//                p5.setVisible(true);
//            }

//        if (DietPlans.getDietPL().size() != 0 && viewDP.isSelected()) {
//            area.setText(String.valueOf(DietPlans.getDietPL()));
//            p5.setVisible(true);
//        }
//
//        if (viewDP.isSelected() && DietPlans.getDietPL().size() == 0) {
//            area.setText(getDP());
//            p5.setVisible(true);
//        }
//
//        if (addNewDP.isSelected()) {
//            new AddNewPlan(person);
//            dispose();
//        }
    }

    private String getDP() {
//        String output;
//        for (ClassDiet classDiet : person.getDietPlan()) {
//            output = "Name of user: " + person.getName() + "\n\n";
//            area.setText(classDiet.getDiet() + " : Bkl dhank se toh bana deta" + "\n");
//        }

        String a = "Non-vegetarian Diet: " + DietPlans.getNonvdiet() + "\n\n\n";
        String b = "Vegetarian Diet: " + DietPlans.getVdiet() + "\n\n\n";
        String c = "Vegan Diet: " + DietPlans.getVegandiet() + "\n\n SELECT ANY ONE OF THESE FROM THE ABOVE OPTION!";


        return a + b + c;
    }
}
