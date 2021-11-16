package com.example.IndividualProject.controller;

import com.example.IndividualProject.model.*;

import com.example.IndividualProject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/hello")
    @ResponseBody
    public String SayHello() {
        return "Hello, your resources work!";
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        if (users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable(value = "id") String id) {
        User user = userService.getUserById(id);

        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return new ResponseEntity("User not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.addUser(user);

        if (created != null) {
            String url = "user" + "/" + created.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }

        String entity = "User with email " + user.getEmail() + " already exists.";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") String id, @RequestBody User user) {
        User old = userService.getUserById(id);

        if (old == null) {
            return new ResponseEntity("User not found.", HttpStatus.NOT_FOUND);
        }

        old.setName(user.getName());
        old.setCountry(user.getCountry());
        old.setJob(user.getJob());
        old.setEmail(user.getEmail());
        old.setAge(user.getAge());
        old.setUserType(user.getUserType());

        User updated = userService.updateUser(old);
        return ResponseEntity.ok().body(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        if (userService.getUserById(id) == null) {
            return new ResponseEntity("User not found.", HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
