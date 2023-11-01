package com.metadata.metadata_crud.repository;

import com.metadata.metadata_crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
   List<User> findAllByCategoryContaining(String userId);
   List<User> findByUsernameNotNull();

   List<User> findByKeyy(String keyy);

   void deleteByKeyy(String userId);

   List<User> findAllByKeyy(String userId);


//    Optional<User> findAllByCategory(String Category);
}
