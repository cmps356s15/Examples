package hifzTracker.repository;

import hifzTracker.entity.Task;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TaskRepository {

    @PersistenceContext
    private EntityManager em;

    @Inject
    SurahRepository surahRepository;

    @Inject
    UserRepository userRepository;

    public int addTask(Task task) {
        em.persist(task);
        em.flush(); //Force saving to the database to generate a taskId
        return task.getId();
    }

    public void deleteTask(int taskId) {
        Task task = em.getReference(Task.class, taskId);
        em.remove(task);
    }

    public void completeTask(int taskId, String completedDate, String level, String comment) {
        Task task = getTask(taskId);
        task.setCompletedDate(completedDate);
        task.setLevel(level);
        task.setComment(comment);
        updateTask(task);
    }

    public Task getTask(int taskId) {
        return em.find(Task.class, taskId);
    }

    public void updateTask(Task task) {
        em.merge(task);
    }

    public List<Task> gettasksByuserId(int userId) {
        Query query = em.createNamedQuery("Task.getTasksByUserId");
        query.setParameter("userId", userId);
        List<Task> tasks = query.getResultList();
        if (tasks == null || tasks.isEmpty()) {
            insertTestTasks(userId);
            tasks = query.getResultList();
        }
        return tasks;
    }

    public List<Task> getCompletedTasks(int userId) {
        Query query = em.createQuery("select t FROM Task t WHERE t.user.id = :userId and t.completedDate is not null");
        query.setParameter("userId", userId);
        List<Task> tasks = query.getResultList();
        if (tasks == null || tasks.isEmpty()) {
            insertTestTasks(userId);
            tasks = query.getResultList();
        }
        return tasks;
    }

    public List<Task> getPendingTasks(int userId) {
        Query query = em.createQuery("select t FROM Task t WHERE t.user.id = :userId and t.completedDate is null");
        query.setParameter("userId", userId);
        List<Task> tasks = query.getResultList();
        if (tasks == null || tasks.isEmpty()) {
            insertTestTasks(userId);
            tasks = query.getResultList();
        }
        return tasks;
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

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
            Task task = new Task(surahRepository.getSurah(i), 1, numberOfAya, taskType[i % 2], df.format(dueDate));
            task.setUser(userRepository.getUser(userId));
            numberOfAya += i % 2;
            this.addTask(task);
        }
    }

}
