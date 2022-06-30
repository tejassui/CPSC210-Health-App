package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class People implements Writable {

    private static List<Person> people;

    public People() {
        this.people = new ArrayList<>();
    }

    public Person getPerson(int index) {
        return this.people.get(index);
    }

    // REQUIRES: p != null
    // MODIFIES: this
    // EFFECTS: adds a Person p at the end of the list
    public static void addPerson(Person p) {
        people.add(p);
        EventLog.getInstance().logEvent(new Event("Added person to list of people"));
    }

    // EFFECTS: returns the size of People
    public int size() {
        return this.people.size();
    }

    public List<Person> getPeep() {
        return this.people;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject job = new JSONObject();

        for (Person p : people) {
            job.put(p.getName(), p.toJson());
        }

        return job;
    }

}
