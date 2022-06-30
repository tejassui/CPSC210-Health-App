package persistence;

import model.People;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    private People p;
    private Person personOne;

    @BeforeEach
    public void setUp() {
        p = new People();
        personOne = new Person("testOne", 18, 188, 65,"M");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");

        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyPeople() {
        try {
            JsonWriter writer = new JsonWriter(("./data/testEmptyPerson.json"));
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyPerson.json");
            p = reader.read();
            assertEquals(0, p.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralWorkroom() {
        try {
            p.addPerson(personOne);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            p = reader.read();
            assertEquals(1, p.size());
            Person person = p.getPerson(0);
            assertPerson(person);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}

