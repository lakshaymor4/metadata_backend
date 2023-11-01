package com.metadata.metadata_crud.controllers;
import com.metadata.metadata_crud.service.impl.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import com.metadata.metadata_crud.entity.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class UserController {

    private UserService userService;
    @GetMapping("/api/users")
    public String sayHelloWorld() {
        System.out.println("hi");
        return "Hello World!";
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.createKey(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @GetMapping("/api/getusers/{id}")
    public ResponseEntity<List<User>> getUsersByCategory(@PathVariable("id") String categoryId) {
        List<User> users = userService.getUserByCateg(categoryId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/api/getall")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PutMapping("/api/update/{id}")

    public ResponseEntity<List <User> >updateUser(@PathVariable("id") String userId,
                                           @RequestBody User user){
        user.setKeyy(userId);
        List <User> updatedUser = userService.updateId(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/api/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId){
        userService.deleteKeyy(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
@PostMapping("/cross/create")
public ResponseEntity<User> createUserCross(@RequestBody User user){
    User savedUser = userService.createKeyCross(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
}

        @GetMapping("/cross/getall")
    public ResponseEntity<List<User>> getAllCross(){
        List<User> users = userService.getAllCross();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/cross/getusers/{id}")
    public ResponseEntity<List<User>> getUserByCategCru(@PathVariable("id") String categoryId) {
        List<User> users = userService.getUserByCategCross(categoryId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

