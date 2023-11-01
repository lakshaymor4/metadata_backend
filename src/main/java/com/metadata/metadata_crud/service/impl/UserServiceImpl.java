package com.metadata.metadata_crud.service.impl;
import java.util.ArrayList;
import com.metadata.metadata_crud.repository.UserRepository;
import lombok.AllArgsConstructor;
import com.metadata.metadata_crud.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User createKey(User user) {
        List<User> users = userRepository.findByKeyy(user.getKeyy());

        for (User existingUser : users) {
            if (existingUser.getUsername() == null) {
                // Handle the case where a user with a null username is found.
                // You can throw a custom exception or handle it as needed.
                throw new RuntimeException("User with a null username found.");
            }
        }

        // Continue processing and save the user if no null usernames are found.
        return userRepository.save(user);
    }




    @Override
    public List<User> getUserByCateg(String userId) {
        List<User> allUsers = userRepository.findAllByCategoryContaining(userId);
        List<User> filteredUsers = new ArrayList<>();

        for (User user : allUsers) {
            String username = user.getUsername();
            if (username == null || username.isEmpty()) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }



    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        List<User> filteredUsers = new ArrayList<>();

        for (User user : allUsers) {
            String username = user.getUsername();
            if (username == null || username.isEmpty()) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }


    @Override
    public List<User> updateId(User userr) {
        List<User> users = userRepository.findAllByKeyy(userr.getKeyy());
        List<User> filteredUsers = new ArrayList<>();

        for (User user : users) {
            String username = user.getUsername();
            if (username == null) {
                // Update the attributes of the user
                user.setUsername(userr.getUsername());
                user.setCategory(userr.getCategory());
                user.setValue(userr.getValue());
                filteredUsers.add(user);
            }
        }

        // Save the updated users in a batch
        userRepository.saveAll(filteredUsers);

        return filteredUsers;
    }



    @Override
    public List<User> deleteKeyy(String userId) {
        List<User> allUsers = userRepository.findAllByKeyy(userId);
        List<User> filteredUsers = new ArrayList<>();

        for (User user : allUsers) {
            String username = user.getUsername();
            if (username == null) {
                filteredUsers.add(user);
                userRepository.delete(user); // Delete the user with null username
            }
        }

        return filteredUsers;
    }


    @Override
    public List<User> getAllCross(){
        return userRepository.findByUsernameNotNull();
    }
    @Override
    public  User createKeyCross(User user) {
      return  userRepository.save(user);
    }
    @Override
    public List<User> getUserByCategCross(String Id){
        List<User> allUsers = userRepository.findAllByCategoryContaining(Id);
        List<User> filteredUsers = new ArrayList<>();

        for (User user : allUsers) {
            String username = user.getUsername();
            if (username != null ) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
}}
