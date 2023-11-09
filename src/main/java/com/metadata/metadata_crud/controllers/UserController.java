package com.metadata.metadata_crud.controllers;

import com.metadata.metadata_crud.entity.sourcesys.sourcesystem;
import com.metadata.metadata_crud.entity.user.User;
import com.metadata.metadata_crud.service.impl.sysource.Syssource;
import com.metadata.metadata_crud.service.impl.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final Syssource syssource;

    public UserController(UserService userService, Syssource syssource) {
        this.userService = userService;
        this.syssource = syssource;
    }

    // User-related endpoints


    @PostMapping("/metadata")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createKey(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/metadata/{category}")
    public ResponseEntity<List<User>> getUsersByCategory(@PathVariable("category") String categoryId) {
        List<User> users = userService.getUserByCateg(categoryId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/metadata")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/metadata/{category}")
    public User updateUser(@PathVariable("category") String userId, @RequestBody User user) {
        return userService.updateId(userId, user);
    }

    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

    // Cross-related endpoints
    @PostMapping("/sourcesystem")
    public ResponseEntity<sourcesystem> createSourcesystemCross(@RequestBody sourcesystem sourcesystem) {
        sourcesystem savedSourcesystem = syssource.createKeyyCross(sourcesystem);
        return new ResponseEntity<>(savedSourcesystem, HttpStatus.CREATED);
    }

   

    @GetMapping("/source/{sourceSystemCode}")
    public List<sourcesystem> getAllCrossbyusername(@PathVariable String sourceSystemCode) {
        return syssource.getAllCrossbyusername(sourceSystemCode);
    }
    @GetMapping("/source/{sourceSystemCode}/metadata/{category}")
    public List<sourcesystem> getAllCrossByUserAndCategory(
            @PathVariable String sourceSystemCode,
            @PathVariable String category
    ) {
        return syssource.getAllCrossByUserAndCategory(sourceSystemCode, category);
    }
    @PutMapping("/source/{sourceSystemCode}/metadata/{category}")
    public sourcesystem updateSource(
            @PathVariable String sourceSystemCode,
            @PathVariable String category,
            @RequestBody sourcesystem sourceToUpdate
    ) {
        return syssource.updateSource(sourceSystemCode, category, sourceToUpdate);
    }

    @DeleteMapping("/source/{sourceSystemCode}")
    public void deleteSource(@PathVariable String sourceSystemCode) {
        syssource.deleteSource(sourceSystemCode);
    }
}
