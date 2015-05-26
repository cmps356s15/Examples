package hifzTracker.repository;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import hifzTracker.entity.User;
import javax.ejb.Singleton;
import java.util.Optional;

@Singleton
public class UserRepository {

    private List<User> users;
    private int lastUserId = 0;
    private final String usersUrl = "http://erradi.github.io/users.js";

    public List<User> getUsers() {
        loadUsers();
        return users;
    }

    public User addUser(User user) {
        if (users == null) {
            users = new ArrayList();
        }
        users.add(user);
        user.setId(++lastUserId);
        return user;
    }

    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                break;
            }
        }
    }

    public void deleteUser(int userId) {
        users.removeIf(c -> c.getId() == userId);
    }

    public User getUser(int id) {
        return users.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    public int getUsersCount() {
        return users == null ? 0 : users.size();
    }

    public User getUser(String username, String password) {
        System.out.println("username : " + username);
        System.out.println("password : " + password);
        //System.out.println(users);
        if (users == null) {
            loadUsers();
        }
        Optional<User> user = users.stream().filter(c -> c.getUsername().equals(username) && c.getPassword().equals(password)).findFirst();
        return user.isPresent() ? user.get() : null;
    }

    private void loadUsers() {
        if (users != null && users.size() > 0) {
            return;
        }

        Gson gson = new Gson();
        String usersStr = Utils.readUrl(usersUrl);
        System.out.println(usersStr);

        User[] userArray = gson.fromJson(usersStr, User[].class);
        users = new ArrayList<>(Arrays.asList(userArray));
        lastUserId = users.size() + 1;

        System.out.println("lastUserId : " + lastUserId);
    }
}
