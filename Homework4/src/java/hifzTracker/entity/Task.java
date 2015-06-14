package hifzTracker.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Task.getTasksByUserId",
            query = "select t FROM Task t WHERE t.user.id = :userId")})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SurahId")
    private Surah surah;
    private int fromAya;
    private int toAya;
    private String type;
    private String dueDate;
    private String completedDate;
    private String level;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
