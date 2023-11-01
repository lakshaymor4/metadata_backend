package com.metadata.metadata_crud.service.impl;
import com.metadata.metadata_crud.entity.User;
import java.util.List;
public interface UserService {
    User createKey(User user);
    User createKeyCross(User user);
   List <User> getUserByCateg(String Id);
    List <User>getAllUsers();
    List <User> updateId(User user);
    List <User> deleteKeyy(String Id);
    List <User> getAllCross();
    List <User> getUserByCategCross(String Id);


}
