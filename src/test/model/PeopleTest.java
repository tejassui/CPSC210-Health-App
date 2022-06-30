package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeopleTest {
    private People people;
    private Person testPerson;
    private Person testPersonTwo;

    @BeforeEach
    public void setUp() {
        people = new People();
        testPerson = new Person("test", 14, 160, 60,"M");
        testPersonTwo = new Person("test2", 15, 150, 50, "F");
        people.addPerson(testPersonTwo);
    }

    @Test
    public void testAddPerson() {
        assertEquals(1, people.size());
        checkTestPersonTwoIsAtIndexZero();
    }

    @Test
    public void testAddPersonTwo() {
        people.addPerson(testPerson);
        assertEquals(2, people.size());
        checkTestPersonTwoIsAtIndexZero();
        checkTestPersonIsAtIndexOne();
    }


    private void checkTestPersonTwoIsAtIndexZero() {
        assertEquals("test2", people.getPerson(0).getName());
        assertEquals(15, people.getPerson(0).getAge());
        assertEquals(150, people.getPerson(0).getHeight());
        assertEquals(50, people.getPerson(0).getWeight());
        assertEquals("F", people.getPerson(0).getGender());
    }

    private void checkTestPersonIsAtIndexOne() {
        assertEquals("test", people.getPerson(1).getName());
        assertEquals(14, people.getPerson(1).getAge());
        assertEquals(160, people.getPerson(1).getHeight());
        assertEquals(60, people.getPerson(1).getWeight());
        assertEquals("M", people.getPerson(1).getGender());
    }

    @Test
    public void testGetPeep() {
        List<Person> peep = people.getPeep();
        assertEquals(1, people.size());
        assertEquals("test2", peep.get(0).getName());
        assertEquals(15, peep.get(0).getAge());
        assertEquals(150, peep.get(0).getHeight());
        assertEquals(50, peep.get(0).getWeight());
        assertEquals("F", peep.get(0).getGender());
    }

}