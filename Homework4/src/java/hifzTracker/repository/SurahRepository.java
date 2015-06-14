package hifzTracker.repository;

import com.google.gson.Gson;
import hifzTracker.entity.Surah;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SurahRepository {
    
    @PersistenceContext
    private EntityManager em;

    public List<Surah> getSurahs() {
        Query query = em.createQuery("select s from Surah as s");
        return query.getResultList();
    }
    
    public Surah getSurah(int id) {
        return em.find(Surah.class, id);
    }
    
    public Surah addSurah(Surah sura) {
        em.persist(sura);
        em.flush();
        return sura;
    }

    //@PostConstruct = This method will be auto-executed after the object is instantiated 
    @PostConstruct
    public void loadSurahs() {
        //Only load Surahs to the database and the Surah table is empty
        int surahCount = ((Long) em.createQuery("select count(s.id) from Surah as s").getSingleResult()).intValue();
        if (surahCount > 0)
            return;
    
        String surahsUrl = "http://erradi.github.io/surahs.js";
        Gson gson = new Gson();
        String surahsStr = Utils.readUrl(surahsUrl);
        System.out.println(surahsStr);

        Surah[] surahArray = gson.fromJson(surahsStr, Surah[].class);
        List<Surah> surahs = new ArrayList<>(Arrays.asList(surahArray));
        
        surahs.forEach(sura -> addSurah(sura));
    }
}
