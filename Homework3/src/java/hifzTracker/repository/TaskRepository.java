package hifzTracker.repository;

import hifzTracker.entity.Task;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class TaskRepository {

    @Inject
    SurahRepository surahRepository;

    private Map<Integer, List<Task>> tasks = new HashMap<>();
    private int lastTaskId = 1;

    public TaskRepository() {
    }

    public int addTask(Integer userId, Task task) {

        List<hifzTracker.entity.Task> userTasks = tasks.get(userId);
        if (userTasks == null) {
            userTasks = new ArrayList();
        }
        task.setId(lastTaskId++);
        userTasks.add(task);
        tasks.put(userId, userTasks);
        return task.getId();
    }

    public void deleteTask(Integer userId, int taskId) {
        List<Task> userTasks = tasks.get(userId);
        userTasks.removeIf(c -> c.getId() == taskId);
    }

    public void completeTask(Integer userId, int taskId, Date completedDate, String level, String comment) {
        List<Task> userTasks = tasks.get(userId);
        //TODO validate task ID
        Task task = userTasks.stream().filter(c -> c.getId() == taskId).findFirst().get();
        task.setCompletedDate(completedDate);
        task.setLevel(level);
        task.setComment(comment);
    }

    public List<Task> getCompletedTasks(Integer userId) {
        List<Task> userTasks = tasks.get(userId);

        List<Task> completedTasks = userTasks.stream().filter(c -> c.isCompleted()).collect(Collectors.toList());
        return completedTasks;
    }

    public List<Task> getPendingTasks(Integer userId) {
        List<Task> userTasks = tasks.get(userId);

        //Insert test data
        if (userTasks == null) {
            insertTestTasks(userId);
        }

        List<Task> pendingTasks = tasks.get(userId).stream().filter(c -> !c.isCompleted()).collect(Collectors.toList());
        return pendingTasks;
    }

    public Task getTask(Integer userId, int taskId) {
        Task task = tasks.get(userId).stream().filter(c -> c.getId() == taskId).findFirst().get();
        return task;
    }

    public void updateTask(Integer userId, Task task) {

        List<Task> userTasks = tasks.get(userId);

        for (int i = 0; i < userTasks.size(); i++) {
            if (userTasks.get(i).getId() == task.getId()) {
                userTasks.set(i, task);
                break;
            }
        }
    }

    private void insertTestTasks(Integer userId) {
        Random random = new Random();
        int lowerBound = -5;
        int upperBound = +5;
        int numberOfAya = 4;
        String[] taskType = {"Memorization", "Revision"};
        
        for (int i = 1; i <= 10; i++) {
            int randomInt = random.nextInt(upperBound - lowerBound) + lowerBound;
            Calendar c = Calendar.getInstance();
            c.setTime(new Date()); // Now use today date.
            c.add(Calendar.DATE, randomInt); // Adding 5 days
            Date dueDate = c.getTime();
            Task task = new Task(surahRepository.getSurah(i), 1, numberOfAya, taskType[i % 2], dueDate);
            numberOfAya += i % 2;
            this.addTask(userId, task);
        }
    }
}
