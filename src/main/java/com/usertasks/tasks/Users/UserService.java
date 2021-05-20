package com.usertasks.tasks.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserService {
    @Autowired
    private UserDAO userDAO;

    //GET /users
    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    //GET /user/{id}
    @GetMapping(path = "users/{id}")
    public User getUser(@PathVariable int id) {
        return userDAO.getUserById(id);
    }

    //POST /users/{user}
    @PostMapping(path = "/users")
    public User saveUser(@RequestBody User user) {
        return userDAO.saveUser(user);
    }
}
