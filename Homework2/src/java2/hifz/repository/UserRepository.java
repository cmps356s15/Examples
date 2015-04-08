/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hifzTracker.repository;

import com.google.gson.Gson;
import hifzTracker.entity.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import javax.ejb.Singleton;

/**
 *
 * @author Taofik
 */
@Singleton
public class UserRepository {

    private List<User> users;
    
    private final String usersUrl = "http://erradi.github.io/users.js";

    public List<User> getUsers() {
        if (users != null) {
            return users;
        } else {
            insertTestData();
            return users;
        }
    }

    public void addUser(User user) {
        if (users == null) {
            users = new ArrayList();
        }
        users.add(user);
        //user.setId(++lastUserId);
    }

    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                break;
            }
        }
    }

    public void deleteUser(int userID) {
        users.removeIf(c -> c.getId() == userID);
    }

    public User getUser(int id) {
        return users.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    public User getUser(String username, String password) {
        if (users == null) {
            insertTestData();
        }
        try {
            User user = users.stream().filter(c -> c.getUsername().equals(username)).findFirst().get();

            if (user.getPassword().equals(password)) {
                return user;
            } else {
                return null;
            }

        } catch (NullPointerException | NoSuchElementException e) {
            return null;
        }

    }

    private void insertTestData() {
        if (users != null && users.size() > 0) {
            return;
        }

        Gson gson = new Gson();
        String userStr = Utils.readUrl(usersUrl);
        System.out.println(userStr);

        User[] usersArray = gson.fromJson(userStr, User[].class);
        //Convert the array to a editable list 
        users = new ArrayList<>(Arrays.asList(usersArray));
       

        
    }

}
