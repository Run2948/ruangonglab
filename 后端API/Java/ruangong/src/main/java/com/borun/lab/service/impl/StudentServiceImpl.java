package com.borun.lab.service.impl;

import com.borun.lab.bean.Student;
import com.borun.lab.dao.StudentDao;
import com.borun.lab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService{

    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student findByStuNum(String stunum) {
        return studentDao.findByStunum(stunum);
    }

    @Override
    public Student doLogin(String stunum, String password) {
        return studentDao.findStudentByStunumAndPassword(stunum,password);
    }

    @Override
    public Student saveOrUpdate(Student student) {
        return studentDao.saveAndFlush(student);
    }

    @Override
    public List<Student> queryByExample(Student student){
        if(student == null)
            return studentDao.findAll();
        return studentDao.findAll(Example.of(student));
    }

    @Override
    public long getCount(Student student) {
        return studentDao.count(Example.of(student));
    }

    @Override
    public long getMaxSignall() {
        int signall = studentDao.findMaxSignall();
        Student model = studentDao.findFirstBySignallIsGreaterThanEqual(signall);
        if(model == null)
            return 0;
        else
            return model.getSignall() + model.getLeaveall();
    }


}
