package hifzTracker.repository;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import hifzTracker.entity.User;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public List<User> getUsers() {
        Query query = em.createQuery("select u from User as u");
        return query.getResultList();
    }

    public User addUser(User user) {
        em.persist(user);
        return user;
    }

    public void updateUser(User user) {
        em.merge(user);
    }

    public void deleteUser(int userId) {
        User user = em.getReference(User.class, userId);
        em.remove(user);
    }

    public User getUser(int id) {
        return em.find(User.class, id);
    }

    public User getUser(String username, String password) {
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        return (User) query.getSingleResult();
    }
    
    public void update(User user) {
        em.merge(user);
    }

    //@PostConstruct = This method will be auto-executed after the object is instantiated 
    @PostConstruct
    private void loadUsers() {
        //Only load users to the database and the Users table is empty
        int usersCount = ((Long) em.createQuery("select count(u.id) from User as u").getSingleResult()).intValue();
        if (usersCount > 0)
            return;

        String usersUrl = "http://erradi.github.io/users.js";
        Gson gson = new Gson();
        String usersStr = Utils.readUrl(usersUrl);
        System.out.println(usersStr);

        User[] userArray = gson.fromJson(usersStr, User[].class);
        List<User> users = new ArrayList<>(Arrays.asList(userArray));
        users.forEach(
                user -> addUser(user)
        );
    }
}
