package com.greatlearning.studentManagementSystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.studentManagementSystem.model.Student;
import com.greatlearning.studentManagementSystem.repository.StudentRepository;
import com.greatlearning.studentManagementSystem.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student) {
        if (student != null)
            studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        if (student != null)
            studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).get();
    }

}
