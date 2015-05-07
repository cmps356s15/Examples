package ims.entity;

public class Presentation {
    String date, location, time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Presentation(String date, String location, String time) {
        this.date = date;
        this.location = location;
        this.time = time;
    }
}
