package com.metadata.metadata_crud.service.impl.user;

import com.metadata.metadata_crud.entity.user.User;
import com.metadata.metadata_crud.repository.user.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("customerTransactionManager") // Specify the desired transaction manager
    private PlatformTransactionManager customerTransactionManager;

    private final UserRepository userRepository;

    @Override
    public User createKey(User user) {


            return userRepository.save(user);



    }


    @Override
    public List<User> getUserByCateg(String category) {
        return userRepository.findAllByCategoryContaining(category);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateId(String id, User newUser) {
        Optional<User> existingUser = userRepository.findByKeyy(id);
        if (existingUser.isPresent()) {
            User originalUser = existingUser.get();

            if (newUser.getKeyy() != null && !newUser.getKeyy().isEmpty()) {
                originalUser.setKeyy(newUser.getKeyy());
            }

            if (newUser.getValue() != null && !newUser.getValue().isEmpty()) {
                originalUser.setValue(newUser.getValue());
            }

            if (newUser.getCategory() != null && !newUser.getCategory().isEmpty()) {
                originalUser.setCategory(newUser.getCategory());
            }

            return userRepository.save(originalUser);
        }
        return null;
    }


    @Override
    @Transactional("customerTransactionManager")
    public void deleteUser(String userId) {
        userRepository.deleteByKeyy(userId);
    }

}
