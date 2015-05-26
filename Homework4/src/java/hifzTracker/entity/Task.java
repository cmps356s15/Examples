package hifzTracker.entity;

public class Task {
    private int id;
    private Surah surah;
    private int fromAya;
    private int toAya;
    private String type;
    private String dueDate;
    private String completedDate;
    private String level;
    private String comment;

    public Task() {
    }

    public Task(Surah surah, int fromAya, int toAya, String taskType, String dueDate) {
        this.surah = surah;
        this.fromAya = fromAya;
        this.toAya = toAya;
        this.type = taskType;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setType(String taskType) {
        this.type = taskType;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isCompleted() {
        return completedDate != null;
    }
}
