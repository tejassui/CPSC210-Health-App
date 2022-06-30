package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassDietTest {
    private ClassDiet testDiet;
    private String vDiet;

        @BeforeEach
        void runBefore() {
            vDiet = DietPlans.getVdiet();
            testDiet = new ClassDiet("Veg", vDiet);
        }

        @Test
        void testConstructor() {
            assertEquals("\tBreakfast: Scrambled eggs with tomatoes, garlic and mushrooms\n"
                    + "\tLunch: Zucchini boats stuffed with veggies and feta with tomato soup\n"
                    + "\tDinner: Chickpea curry with basmati rice", testDiet.getDiet());
            assertEquals("Veg", testDiet.getTitle());
        }
}
