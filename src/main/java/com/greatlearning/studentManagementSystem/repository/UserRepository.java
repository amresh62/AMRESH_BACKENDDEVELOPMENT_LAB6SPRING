package com.greatlearning.studentManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.studentManagementSystem.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    public Users getUserByUserName(String username);
}
