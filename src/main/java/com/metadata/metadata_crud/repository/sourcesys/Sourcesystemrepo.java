package com.metadata.metadata_crud.repository.sourcesys;

import com.metadata.metadata_crud.entity.sourcesys.sourcesystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Sourcesystemrepo extends JpaRepository<sourcesystem, Long> {
    List<sourcesystem> findByCategory(String id);



    List<sourcesystem> findAllByUsernameAndCategory(String user, String category);

    List<sourcesystem> findAllByUsername(String user);

    Optional<sourcesystem> findByUsername(String source);

    void deleteByUsername(String userId);


    // Define custom query methods if needed
}
