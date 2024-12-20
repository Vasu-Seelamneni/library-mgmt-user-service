package com.msp.library.mgmt.controller;

import com.msp.library.mgmt.model.User;
import com.msp.library.mgmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/save")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/find/{name}")
    public Iterable<User> findByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteById(id);
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @GetMapping("/exists/{id}")
    public boolean isUserExists(@PathVariable long id) {
        return userService.isUserExists(id);
    }

}
