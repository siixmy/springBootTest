package com.example.demo.service.impl;

import com.example.demo.dao.IStudentDao;
import com.example.demo.pojo.Student;
import com.example.demo.service.IStudentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentInterface {
    @Autowired
    public IStudentDao studentDao;
    @Override
    public List<Student> queryAllStudents() {
        return studentDao.getAllItemsByQueryObject(new Student());
    }
}
