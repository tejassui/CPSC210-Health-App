package ui;

import model.Event;
import model.EventLog;
import model.People;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// starts the application
public class HealthApp extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/people.json";
    private static JsonWriter jsonWriter;
    private JsonReader jsonReader;
    protected static People people = new People();
    protected static ImageIcon image = new ImageIcon("./data/health.jpg");
    Scanner in = new Scanner(System.in);

    JRadioButton exists = new JRadioButton();
    JRadioButton newUser = new JRadioButton();
    ButtonGroup bg = new ButtonGroup();
    WindowListener exitListener;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuLoad;
    JMenuItem readMe;
    JMenuItem menuQuit;

    // EFFECTS: runs the health application
    public HealthApp() {
        super("Health app");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null);
        setResizable(true);

        createMenuBar();
        setJMenuBar(createMenuBar());

        newUser.setText("New user");
        newUser.addActionListener(this);
        newUser.setHorizontalAlignment(JLabel.CENTER);

        exists.setText("Existing user");
        exists.addActionListener(this);
        exists.setHorizontalAlignment(JLabel.CENTER);

        bg.add(newUser);
        bg.add(exists);


        add(newUser);
        add(exists);

        setVisible(true);

        jsonWriter = new JsonWriter(HealthApp.JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runHealth();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runHealth() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu();
            command = in.next();
            command = command.toUpperCase();

            if (command.equals("E")) {
                showExercise();
            } else {
                printLog(EventLog.getInstance());
                keepGoing = false;
            }
        }
        System.out.println("\nGoodbye!");
    }

    // menu
    private void displayMenu() {
        System.out.println("*******************************************");
        System.out.println("WELCOME!");
        System.out.println("Press E to view exercises. \nPress Q to Exit");
    }

//    private void processCommand(String command) {
//        if (command == "E") {
//            showExercise();
//        }
//    }

    private void showExercise() {
        System.out.println("Choose a muscle group!");
        System.out.println("1. Abs");
        System.out.println("2. Chest");
        System.out.println("3. Back");
        System.out.println("4. Arms");
        System.out.println("5. Legs");
        int muscle = in.nextInt();
        if (muscle == 1)  {
            System.out.println(abex);
        } else if (muscle == 2) {
            System.out.println(chex);
        } else if (muscle == 3) {
            System.out.println(backex);
        } else if (muscle == 4) {
            System.out.println(armex);
        } else if (muscle == 5) {
            System.out.println(legex);
        } else {
            System.out.println("Please enter a valid option.");
            showExercise();
        }
    }

    // EFFECTS: saves the data to a file
    public static void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(HealthApp.people);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + HealthApp.JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates and returns a menu bar
    private JMenuBar createMenuBar() {

        menuBar = new JMenuBar();
        menuBar.setBackground(Color.YELLOW);

        menu = new JMenu("Menu");

        menu.getAccessibleContext().setAccessibleDescription(
                "This menu has menu items");
        menuBar.add(menu);

        menuLoad = new JMenuItem("Load Data");
        menuLoad.addActionListener(this);
        menu.add(menuLoad);

        readMe = new JMenuItem("Open ReadMe file");
        readMe.addActionListener(this);
        menu.add(readMe);

        menuQuit = new JMenuItem("Quit Application");
        menuQuit.addActionListener(this);
        menu.add(menuQuit);

        makeClosingAction();
        return menuBar;
    }


    // MODIFIES: this
    // EFFECTS: Overrides the closing action when the window is closed to
    // ask the user if they want to save the application or not (pop-up)
    private void makeClosingAction() {

        exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Do you want to save the application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, HealthApp.image, null, null);
                if (confirm == 0) {
                    saveData();
                }
                dispose();
            }
        };
    }

    // MODIFIES: this
    // EFFECTS: opens a new window according to the selected checkbox checked,
    // loads data, quits app, opens readme according to the button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exists) {
            new ExistingUsers(this);
        } else if (e.getSource() == newUser) {
            new NewUser(this);
        }
        if (e.getSource() == menuLoad) {
            readData();
        }
        if (e.getSource() == menuQuit) {
            dispose();
        }
        if (e.getSource() == readMe) {
            openReadMe();
        }
    }

    // EFFECTS: opens the project's readme file
    private void openReadMe() {
        File file = new File("./README.md");
        if (!Desktop.isDesktopSupported()) {
            System.out.println("computer is not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: reads the data from the JSON file containing all the people,
    // throws IOException if cannot read from file
    private void readData() {
        people = new People();
        try {
            people = jsonReader.read();
            System.out.println("Loaded data from people.json");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void printLog(EventLog el) {
        for (Event e : el) {
            System.out.println(e.toString());
        }
    }


    String abex = "> Mountain climbers\n " + "> Ab crunches\n " + "> Oblique crunches";
    String chex = "> Incline pushups\n" + "> Benchpress\n" + "> Decline pushups";
    String backex = "> Deadlifts\n" + "> Bent-over rows\n" + "> Lat pulldowns";
    String armex = "> Tricep extensions\n" + "> Barbell curls\n" + "> Dumbbell curls";
    String legex = "> Legpress\n" + "> Squats\n" + "> Leg extensions";
}
