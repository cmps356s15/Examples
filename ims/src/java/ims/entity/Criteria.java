package ims.entity;

public class Criteria {
    private int id;
    private String title;
    private String description;
    private String category;
    private int grade;

    public Criteria(int id, String title, String description, String category, int grade) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}