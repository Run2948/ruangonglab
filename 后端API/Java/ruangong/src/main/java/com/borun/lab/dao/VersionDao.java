package com.borun.lab.dao;

import com.borun.lab.bean.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VersionDao extends JpaRepository<Version,Integer> {

    Version findVersionById(Integer id);
    @Query("select max(id) from Version")
    int findMaxId();

}
