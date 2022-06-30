package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {
    private Person personTest;

    @BeforeEach
    void runBefore() {
        personTest = new Person("test", 18, 188, 65, "M");
    }

    @Test
    void testConstructor() {
        assertEquals("test", personTest.getName());
        assertEquals(18, personTest.getAge());
        assertEquals(188, personTest.getHeight());
        assertEquals(65, personTest.getWeight());
        assertEquals("M", personTest.getGender());
        List<String> EmptyList = Collections.<String>emptyList();
     //   assertEquals(EmptyList, personTest.getDietPlan());
    }

    @Test
    void testBmrMethod() {
        personTest.bmrMethod("F", 80, 180, 18);
        assertEquals(1667, personTest.getBmr());
        assertEquals(101 , personTest.bmrMethod("M", 1, 1, 1));
        assertEquals(456, personTest.bmrMethod("F", 1, 1, 1));
        personTest.bmrMethod("D", 1,1,1);
        assertEquals(0, personTest.getBmr());
    }

    @Test
    void testTdee() {
        personTest.factorCalc("M", "C");
        personTest.bmrMethod("M", 65, 188, 18);
        assertEquals(2990 , personTest.calcTdee());
    }

    @Test
    void testActivityFactor() {
        personTest.factorCalc("F", "D");
        assertEquals(1.9, personTest.getActivityFactor());
        assertEquals(1.6 , personTest.factorCalc("M", "B"));
        assertEquals(2.1 , personTest.factorCalc("M", "D"));
        assertEquals(2.4 , personTest.factorCalc("M", "E"));
        assertEquals(1.6 , personTest.factorCalc("F", "C"));
        assertEquals(2.2 , personTest.factorCalc("F", "E"));
        assertEquals(1.5 , personTest.factorCalc("F", "B"));
        assertEquals(0, personTest.factorCalc("C", "E"));
    }

    @Test
    void testFactorCalc() {
        assertEquals(1.3, personTest.factorCalc("F", "A"));
        assertEquals(1.3, personTest.factorCalc("M", "A"));
    }
}


