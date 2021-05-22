package com.usertasks.tasks.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = userDAO.getUserById(id);
        if(user == null)
            throw new UserNotFoundException("invalid user " + id);

        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(this.getClass())
                        .getAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    //POST /users/{user}
    @PostMapping(path = "/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User savedUser = userDAO.saveUser(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequestUri()
                .path("/users/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //DELETE /user/{id}
    @DeleteMapping(path = "users/{id}")
    public void deleteUserById(@PathVariable int id) {
        User user = userDAO.deleteUserById(id);
        if(user == null)
            throw new UserNotFoundException("invalid user " + id);
        }
}
