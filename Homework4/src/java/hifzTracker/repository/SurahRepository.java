package hifzTracker.repository;

import com.google.gson.Gson;
import hifzTracker.entity.Surah;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.ejb.Singleton;

@Singleton
public class SurahRepository {
    private List<Surah> surahs;
    private final String surahsUrl = "http://erradi.github.io/surahs.js";

    public List<Surah> getSurahs() {
        loadSurahs();
        return surahs;
    }
    
    public Surah getSurah(int id) {
        loadSurahs();
        return surahs.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    public void loadSurahs() {
        if (surahs != null && surahs.size() > 0) {
            return;
        }
        Gson gson = new Gson();
        String surahsStr = Utils.readUrl(surahsUrl);
        System.out.println(surahsStr);

        Surah[] surahArray = gson.fromJson(surahsStr, Surah[].class);
        surahs = new ArrayList<>(Arrays.asList(surahArray));

        surahs.addAll(surahs);
    }
}
