package persistence;

import model.People;
import model.Person;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public People read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePeople(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private People parsePeople(JSONObject jsonObject) {
        People people = new People();
        addPeople(people, jsonObject);
        return people;
    }

    // MODIFIES: people
    // EFFECTS: parses person from JSON object and adds them to people
    private void addPeople(People people, JSONObject jsonObject) {
        Set<String> keySet = jsonObject.keySet();
        for (String name : keySet) {
            JSONObject attributes = jsonObject.getJSONObject(name);
            String gender = attributes.getString("gender");
            int weight = attributes.getInt("weight");
            int age = attributes.getInt("age");
            int height = attributes.getInt("height");
            Person person = new Person(name, age, height, weight, gender);
            people.addPerson(person);
        }
    }
}

