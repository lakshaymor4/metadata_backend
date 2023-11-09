package com.metadata.metadata_crud.service.impl.sysource;

import com.metadata.metadata_crud.entity.sourcesys.sourcesystem;
import java.util.List;

public interface Syssource {


    sourcesystem createKeyyCross(sourcesystem user);
    List<sourcesystem> getAllCrossbyusername(String userr);
    List<sourcesystem> getAllCrossByUserAndCategory(String user , String categ);

    sourcesystem updateSource(String user , String categ , sourcesystem source);

    void deleteSource(String userId);

    sourcesystem findByUsername(String user1);
}
