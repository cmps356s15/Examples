/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hifzTracker.repository;

import com.google.gson.Gson;
import hifzTracker.entity.Surah;
import hifzTracker.entity.Task;
import hifzTracker.entity.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author Taofik
 */
@Singleton
public class TaskRepository {

    @Inject
    UserRepository userRepository;

    private Map<String, List<Task>> tasks;
    private int lastTaskId = 0;
    private List<Surah> surahs;
    private final String surahsUrl = "http://erradi.github.io/surahs.js";

    public List<Task> getTasks(String username) {
        if (tasks != null && tasks.get(username) != null) {
            return (List<Task>) tasks.get(username);
        } else {
            insertTestData();
            return (List<Task>) tasks.get(username);

        }
    }

    private void initHashMap() {
        if (tasks != null) {
            return;
        } else {
            tasks = new HashMap<String, List<Task>>();
            List<User> users = userRepository.getUsers();
            users.stream().forEach((user) -> {
                tasks.put(user.getUsername(), new ArrayList<Task>());
            });
        }
    }

    public List<Task> getPendingTasks(User user) {
        try {
            if (tasks == null) {
                insertTestData();
            }
            if (tasks.get(user.getUsername()) != null) {
                List<Task> lp = tasks.get(user.getUsername());
                List<Task> pendingTemp = new ArrayList<Task>();
                lp.stream().filter((lp1) -> (lp1 instanceof Task)).forEach((lp1) -> {
                    pendingTemp.add((Task) lp1);
                });
                return pendingTemp;
            } else {
                // insertTestData();
                return null;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Task> getCompletedTasks(User user) {

        try {
            if (tasks == null) {
                insertTestData();
            }
            if (tasks.get(user.getUsername()) != null) {
                List<Task> lp = tasks.get(user.getUsername());
                List<Task> completedTemp = new ArrayList<Task>();
                lp.stream().filter((lp1) -> (lp1 instanceof Task)).forEach((lp1) -> {
                    completedTemp.add((Task) lp1);
                });
                return completedTemp;
            } else {
                // insertTestData();
                return null;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addCompletedTask(Task task, User user) {
        if (tasks == null) {
            initHashMap();
        }
        tasks.get(user.getUsername()).add(task);
    }

    public void addTask(Task task, User user) {
        if (tasks == null) {
            initHashMap();
        }
        tasks.get(user.getUsername()).add(task);
        task.setTaskId(++lastTaskId);
    }

    public void updatePendingTask(Task task, User user) {

        List<Task> lp = tasks.get(user.getUsername());

        for (int i = 0; i < lp.size(); i++) {
            if (lp.get(i).getTaskId() == task.getTaskId()) {
                lp.set(i, task);
                break;
            }
        }
    }

    public void deleteTask(int id, User user) {
        tasks.get(user.getUsername()).removeIf(c -> c.getTaskId() == id);
    }

    public Task getTask(int id, User user) {
        return (Task) tasks.get(user.getUsername()).stream().filter(c -> c.getTaskId() == id).findFirst().get();
    }

   
    public List<Surah> getSurahs() {
        if (surahs == null) {
            Gson gson = new Gson();
            String surahStr = Utils.readUrl(surahsUrl);
            System.out.println(surahStr);

            Surah[] surahArray = gson.fromJson(surahStr, Surah[].class);
            //Convert the array to a editable list 
            surahs = new ArrayList<>(Arrays.asList(surahArray));
            return surahs;
        } else {
            return surahs;
        }
    }

    public Surah getSurah(int id) {
        if (surahs == null) {
            getSurahs();
            return surahs.stream().filter(c -> c.getSurahID() == id).findFirst().get();
        } else {
            return surahs.stream().filter(c -> c.getSurahID() == id).findFirst().get();
        }
    }

    private void insertTestData() {
        if (tasks != null && tasks.size() > 0) {
            return;
        }
        initHashMap();

        addTask(new Task(getSurah(4), 5, 46, "Memorization", "2015-04-15"), userRepository.getUser("juha", "password"));
        addCompletedTask(new Task(getSurah(56), 3, 10, "Memorization", "2015-05-15", "OK", "more practice!"), userRepository.getUser("juha", "password"));
        addTask(new Task(getSurah(6), 40, 130, "Revision", "2015-05-15"), userRepository.getUser("juha", "password"));
        addCompletedTask(new Task(getSurah(80), 1, 3, "Revision", "2015-05-15", "Excelent", "i got this!"), userRepository.getUser("juha", "password"));
    }
}
