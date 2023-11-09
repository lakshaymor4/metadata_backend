package com.metadata.metadata_crud.service.impl.user;
import com.metadata.metadata_crud.entity.user.User;
import java.util.List;
public interface UserService {
    User createKey(User user);

   List <User> getUserByCateg(String Id);
    List <User>getAllUsers();
    User updateId(String id, User userr);
    void deleteUser(String userId);




}
