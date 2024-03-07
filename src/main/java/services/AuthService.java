package services;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private static List<User> users = new ArrayList<>();

    // Initialize hardcoded users
    static {
        users.add(new User("ela", "123"));
    }

    public static boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
