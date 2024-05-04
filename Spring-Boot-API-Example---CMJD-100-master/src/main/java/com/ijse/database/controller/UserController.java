package com.ijse.database.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.database.entity.User;
import com.ijse.database.service.UserService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/users") //Servers entire controller under /users URI
public class UserController {
    private UserService userService; 

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //GET url/users - Get All Users
    //GET url/users/:id - Get User by the Path id
    //POST url/users - Create new user
    //PUT url/users/:id - Find user by ID and Update User
    //DELETE url/users/:id - Delete user

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try 
        {
            //Trying to find a user by the given ID
            User user = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } 
        catch (NoSuchElementException e) 
        {
            //If there is no user by the ID, throw NoSuchElementExeption from
            //UserServicImpl
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch (Exception e)
        {
            //If there is any other generic error like MySQL is not working
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        try {
            User userCreated = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

        try {
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
