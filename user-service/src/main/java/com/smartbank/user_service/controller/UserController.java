package com.smartbank.user_service.controller;

import com.smartbank.user_service.dto.UserDto;
import com.smartbank.user_service.entity.Users;
import com.smartbank.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    // register user
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UserDto userDto) {
        Users createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // get all users
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> usersList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(usersList);
    }

    // find user by id
    @GetMapping(path = "/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id) {
        Users user = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // update the user
    @PutMapping(path = "/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        Users updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    // delete the user b id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        String response = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
