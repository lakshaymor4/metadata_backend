package com.metadata.metadata_crud.repository.user;

import com.metadata.metadata_crud.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
   List<User> findAllByCategoryContaining(String userId);




   void deleteByKeyy(String userId);



   Optional<User> findByKeyy(String id);


   List<User> findAllByKeyy(String userId);
}
