package model;

import java.util.ArrayList;
import java.util.List;

public class DietPlans {

    public DietPlans() {
        dietPL = new ArrayList<>();
    }

    protected static List<ClassDiet> dietPL = new ArrayList<ClassDiet>();

    public static void addDiet(int choice) {
        if (choice == 1) {
            dietPL.add(new ClassDiet("Veg", vdiet));
        } else if (choice == 2) {
            dietPL.add(new ClassDiet("Non veg", nonvdiet));
        } else if (choice == 3) {
            dietPL.add(new ClassDiet("Vegan", vdiet));
        }
    }

    public static List<ClassDiet> getDietPL() {
        return dietPL;
    }


    private static String vdiet = "Breakfast: Scrambled eggs with tomatoes, garlic and mushrooms\n"
            +
            "\tLunch: Zucchini boats stuffed with veggies and feta with tomato soup\n"
            +
            "\tDinner: Chickpea curry with basmati rice";

    public static String getVdiet() {
        return vdiet;
    }

    private static String nonvdiet = "Breakfast: scrambled eggs, stir-fried veggies, and oatmeal\n"
            +
            "\tSnack: whey protein shake\n"
            +
            "\tLunch: grilled chicken breast, mixed greens, and baked sweet potato\n"
            +
            "\tSnack: hard-boiled egg(s) and carrot sticks\n"
            +
            "\tDinner: broiled fish, green beans with brown rice";

    public static String getNonvdiet() {
        return nonvdiet;
    }

    private static String vegandiet = "\tBreakfast: protein oatmeal made with oats, vegan protein powder, "
            +
            "soy milk, banana, and nut "
            + "butter\n"
            +
            "\tLunch: tofu stir-fry made with extra firm tofu, vegan pasta, beans, red lentils, celery, onion, and "
            + "spinach\n"
            +
            "\tDinner: teriyaki tempeh with broccoli and quinoa\n"
            +
            "\tSnack: strawberry-banana protein shake";

    public static String getVegandiet() {
        return vegandiet;
    }

    public int size() {
        return dietPL.size();
    }
}


