package hifzTracker.entity;

public class Surah {
    private int id;
    private String name;
    private String englishName;
    private int ayaCount;

    public Surah() {}
    public Surah(String name, String englishName, int ayaCount, String email, String username, String password) {
        this.name = name;
        this.englishName = englishName;
        this.ayaCount = ayaCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public int getAyaCount() {
        return ayaCount;
    }

    public void setAyaCount(int ayaCount) {
        this.ayaCount = ayaCount;
    }
}
