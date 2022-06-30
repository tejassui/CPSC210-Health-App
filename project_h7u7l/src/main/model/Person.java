package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

import static java.lang.Math.round;

public class Person implements Writable {
    private String name;
    private static int tdee;
    private int age;
    private int height;
    private int weight;
    private String gender;
    private List<ClassDiet> dietPlan;
    private String choice;

    public Person(String name, int age, int height, int weight, String gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        calcTdee();
        this.dietPlan = DietPlans.getDietPL();
    }

    private static double activityFactor;

    public static double factorCalc(String gender, String choice) {
        if ((gender.equals("M") || gender.equals("F")) && choice.equals("A")) {
            activityFactor = 1.3;
        } else if (gender.equals("M") && choice.equals("B")) {
            activityFactor = 1.6;
        } else if (gender.equals("M") && choice.equals("C")) {
            activityFactor = 1.7;
        } else if (gender.equals("M") && choice.equals("D")) {
            activityFactor = 2.1;
        } else if (gender.equals("M") && choice.equals("E")) {
            activityFactor = 2.4;
        } else if (gender.equals("F") && choice.equals("B")) {
            activityFactor = 1.5;
        } else if (gender.equals("F") && choice.equals("C")) {
            activityFactor = 1.6;
        } else if (gender.equals("F") && choice.equals("D")) {
            activityFactor = 1.9;
        } else if (gender.equals("F") && choice.equals("E")) {
            activityFactor = 2.2;
        } else {
            activityFactor = 0;
        }
        return activityFactor;
    }

    private static double bmr;

    public static double bmrMethod(String gender, double weight, double height, double age) {
        if (gender.equals("M")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else if (gender.equals("F")) {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        } else {
            bmr = 0;
        }
        return round(bmr);
    }


    public double calcTdee() {
        double calculatedTdee = getBmr() * getActivityFactor();
        this.tdee = (int) calculatedTdee;
        return round(tdee);
    }

    public static double getActivityFactor() {
        return activityFactor;
    }

    public static double getBmr() {
        return round(bmr);
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getGender() {
        return this.gender;
    }

    public List<ClassDiet> getDietPlan() {
        return dietPlan;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("height", height);
        json.put("weight", weight);
        json.put("gender", gender);
        json.put("tdee", tdee);
        json.put("diet plan", dietPlan);
        return json;
    }
}

