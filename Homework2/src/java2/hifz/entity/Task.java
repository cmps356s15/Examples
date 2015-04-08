package hifzTracker.entity;

import java.util.Date;

public class Task {

    private int taskId;
    private Surah surah;
    private int fromAya;
    private int toAya;
    private String type;
    private Date dueDate;
    private String level;
    private Date completedDate;
    private String comment;

    public Task(int taskId, Surah surah, int fromAya, int toAya, String type, Date dueDate) {
        this.taskId = taskId;
        this.surah = surah;
        this.fromAya = fromAya;
        this.toAya = toAya;
        this.type = type;
        this.dueDate = dueDate;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Surah getSurah() {
        return surah;
    }

    public void setSurah(Surah surah) {
        this.surah = surah;
    }

    public int getFromAya() {
        return fromAya;
    }

    public void setFromAya(int fromAya) {
        this.fromAya = fromAya;
    }

    public int getToAya() {
        return toAya;
    }

    public void setToAya(int toAya) {
        this.toAya = toAya;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getHifzLevel() {
        return level;
    }

    public void setHifzLevel(String level) {
        this.level = level;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}