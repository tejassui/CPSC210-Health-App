package model;

public class ClassDiet {
    private String title;
    private String diet;

    public ClassDiet(String title, String diet) {
        this.title = title;
        this.diet = diet;
    }

    public String getTitle() {
        return title;
    }

    public String getDiet() {
        return diet;
    }

    @Override
    public String toString() {
        return ("Type: " + this.getTitle() + "\nPlan: " + this.getDiet());
    }


}
