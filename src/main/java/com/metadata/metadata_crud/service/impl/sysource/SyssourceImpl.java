package com.metadata.metadata_crud.service.impl.sysource;
import com.metadata.metadata_crud.entity.sourcesys.sourcesystem;
import com.metadata.metadata_crud.entity.user.User;
import com.metadata.metadata_crud.repository.sourcesys.Sourcesystemrepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SyssourceImpl implements Syssource {
    private final Sourcesystemrepo sourcesystemrepo;


    public List<sourcesystem> getAllCrossbyusername(String user) {
        // Use the repository to query the data based on the user
        List<sourcesystem> resultList = sourcesystemrepo.findAllByUsername(user);

        return resultList;
    }



    @Override
    public List<sourcesystem> getAllCrossByUserAndCategory(String user, String category) {

        List<sourcesystem> resultList = sourcesystemrepo.findAllByUsernameAndCategory(user, category);

        return resultList;
    }
    @Override
    public sourcesystem updateSource(String source, String categ , sourcesystem sourc) {
        Optional<sourcesystem> existingUser = sourcesystemrepo.findByUsername(source);
        if (existingUser.isPresent()) {
            sourcesystem originalUser = existingUser.get();

            if (sourc.getKeyy() != null && !sourc.getKeyy().isEmpty()) {
                originalUser.setKeyy(sourc.getKeyy());
            }

            if (sourc.getValue() != null && !sourc.getValue().isEmpty()) {
                originalUser.setValue(sourc.getValue());
            }

            if (sourc.getCategory() != null && !sourc.getCategory().isEmpty()) {
                originalUser.setCategory(sourc.getCategory());
            }

            return sourcesystemrepo.save(originalUser);
        }
        return null;

    }

    @Override
    public sourcesystem createKeyyCross(sourcesystem user) {
        // Implement the method to save a sourcesystem
        return sourcesystemrepo.save(user);
    }
    @Override
    @Transactional("productTransactionManager")
    public void deleteSource(String userId) {
        sourcesystemrepo.deleteByUsername(userId);
    }
    @Override
    public sourcesystem findByUsername(String user) {
        Optional<sourcesystem> result = sourcesystemrepo.findByUsername(user);

        if (result.isPresent()) {
            return result.get(); // Return the wrapped sourcesystem object
        } else {
            // Handle the case where no sourcesystem is found
            // You can return a default value, throw an exception, or handle it as needed.
            // For example, return null:
            return null;
        }
}}

