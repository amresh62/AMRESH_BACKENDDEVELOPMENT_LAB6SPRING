package com.greatlearning.studentManagementSystem.service;

import java.util.List;

import javax.transaction.TransactionScoped;

import org.springframework.stereotype.Service;

import com.greatlearning.studentManagementSystem.model.Student;

@Service
@TransactionScoped
public interface StudentService {

    List<Student> getAllStudent();

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(int id);

    Student getStudentById(int id);
}
