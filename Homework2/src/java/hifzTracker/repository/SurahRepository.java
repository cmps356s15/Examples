package hifzTracker.repository;

import com.google.gson.Gson;
import hifzTracker.entity.Surah;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class SurahRepository {
    private List<Surah> surahs;
    private final String surahsUrl = "http://erradi.github.io/surahs.js";

    public List<Surah> getSurahs() {
        if (surahs != null) {
            return surahs;
        } else {
            loadSurahs();
            return surahs;
        }
    }
    
    public Surah getSurah(int id) {
        if (surahs != null) {
             return surahs.stream().filter(c -> c.getId() == id).findFirst().get();
        } else {
            loadSurahs();
            return surahs.stream().filter(c -> c.getId() == id).findFirst().get();
        }
    }

    private void loadSurahs() {
        if (surahs != null && surahs.size() > 0) {
            return;
        }
        Gson gson = new Gson();
        String surahsStr = Utils.readUrl(surahsUrl);
        System.out.println(surahsStr);

        Surah[] surahArray = gson.fromJson(surahsStr, Surah[].class);
        surahs = new ArrayList<>(Arrays.asList(surahArray));
    }
}
