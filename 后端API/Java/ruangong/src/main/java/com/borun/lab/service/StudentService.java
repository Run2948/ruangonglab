package com.borun.lab.service;

import com.borun.lab.bean.Student;

import java.util.List;

public interface StudentService {
    Student findByStuNum(String stunum);
    Student doLogin(String stunum,String password);
    Student saveOrUpdate(Student student);
    List<Student> queryByExample(Student student);
    long getCount(Student student);
    long getMaxSignall();
}
