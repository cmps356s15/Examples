package hifzTracker.entity;

public class Surah {

    private int id;
    private String name;
    private String englishName;
    private int ayaCount;

    public int getSurahID() {
        return id;
    }

    public void setSurahID(int id) {
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
