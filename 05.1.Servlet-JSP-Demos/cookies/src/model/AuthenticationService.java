package model;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class AuthenticationService {

    private List<User> users;

    public AuthenticationService() {
        users = new ArrayList<User>();
        users.add(new User("ali", "password", "Ali Faleh"));
        users.add(new User("mariam", "password", "Mariam Saleh"));
    }

    public User getUser(String userName, String password) {
        User matchedUser = null;
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                matchedUser = user;
                break;
            }
        }
        return matchedUser;
    }

    public User getUser(String authenticationToken) {
        User matchedUser = null;
        for (User user : users) {
            //System.out.println(user.getUserName() + " - Authentication token: " + user.getAuthenticationToken());
            if (user.getAuthenticationToken() != null && user.getAuthenticationToken().equals(authenticationToken)) {
                matchedUser = user;
                break;
            }
        }
        return matchedUser;
    }

    public void setAuthenticationToken(String userName, String authenticationToken) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                user.setAuthenticationToken(authenticationToken);
                break;
            }
        }
    }
}
