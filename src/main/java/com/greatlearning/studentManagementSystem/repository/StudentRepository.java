package com.greatlearning.studentManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.studentManagementSystem.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
