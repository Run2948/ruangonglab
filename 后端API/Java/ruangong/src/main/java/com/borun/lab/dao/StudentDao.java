package com.borun.lab.dao;

import com.borun.lab.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface StudentDao extends JpaRepository<Student,Integer>{

    Student findStudentByStunumAndPassword(String stunum,String password);
    Student findByStunum(String stunum);
    @Query("select max(signall) from Student")
    int findMaxSignall();
    Student findFirstBySignallIsGreaterThanEqual(int signall);
}
