package hifzTracker.repository;

import hifzTracker.entity.Task;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class TaskRepository {

    @Inject
    SurahRepository surahRepository;

    private Map<Integer, List<Task>> tasks = new HashMap<>();
    private int lastTaskId = 0;

    public TaskRepository() {
    }

    public void addTask(Integer userId, Task task) {

        List<Task> userTasks = tasks.get(userId);
        if (userTasks == null) {
            userTasks = new ArrayList();
        }
        task.setId(lastTaskId++);
        userTasks.add(task);
        tasks.put(userId, userTasks);
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
            for (int i = 1; i < 25; i++) {
                Task task = new Task(surahRepository.getSurah(i), 1, 5, "Memorization", new Date());
                this.addTask(userId, task);
            }
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

}
