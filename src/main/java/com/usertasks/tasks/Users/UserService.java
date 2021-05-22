package com.usertasks.tasks.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        User user = userDAO.getUserById(id);
        if(user == null)
            throw new UserNotFoundException("invalid user " + id);
        return user;
    }

    //POST /users/{user}
    @PostMapping(path = "/users")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        User savedUser = userDAO.saveUser(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequestUri()
                .path("/users/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
