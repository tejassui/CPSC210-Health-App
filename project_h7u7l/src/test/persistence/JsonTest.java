package persistence;

import model.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void assertPerson(Person p) {
        assertEquals("testOne", p.getName());
        assertEquals(18, p.getAge());
        assertEquals(188, p.getHeight());
        assertEquals(65, p.getWeight());
        assertEquals("M", p.getGender());
    }

    protected void assertPersonTwo(Person p) {
        assertEquals("testTwo", p.getName());
        assertEquals(19, p.getAge());
        assertEquals(190, p.getHeight());
        assertEquals(150, p.getWeight());
        assertEquals("M", p.getGender());

    }
}
