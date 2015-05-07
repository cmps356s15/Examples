package ims.entity;

public class Rating {
    private int id;
    private String title;
    private float percentage;

    public Rating(int id, String title, float percentage) {
        this.id = id;
        this.title = title;
        this.percentage = percentage;
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

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
