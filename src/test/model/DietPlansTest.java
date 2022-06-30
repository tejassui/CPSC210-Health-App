package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.DietPlans.dietPL;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DietPlansTest {
    private DietPlans testPlan;
    private int choice;

    @BeforeEach
    void runBefore() {
        testPlan = new DietPlans();
    }

    @Test
    void testAddDiet() {
        assertEquals(0, testPlan.size());
        testPlan.addDiet(1);

        assertEquals(1, testPlan.size());
        testPlan.addDiet(2);
        assertEquals(2, testPlan.size());
        testPlan.addDiet(3);
        assertEquals(3, testPlan.size());

        for (int i = 0; i < dietPL.size(); i++) {
            System.out.println(dietPL.get(i));
        }

        testPlan.getDietPL();

    }

    @Test
    void testGetters() {
        List testL = new ArrayList();
        testL.add(testPlan.getVegandiet());
        assertEquals(DietPlans.getVegandiet(), testL.get(0));
        testL.add(testPlan.getVdiet());
        assertEquals(DietPlans.getVdiet(), testL.get(1));
        testL.add(testPlan.getNonvdiet());
        assertEquals(DietPlans.getNonvdiet(), testL.get(2));
    }

}
