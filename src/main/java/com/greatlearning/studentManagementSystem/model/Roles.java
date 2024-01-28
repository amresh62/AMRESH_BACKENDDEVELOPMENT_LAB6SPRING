package com.greatlearning.studentManagementSystem.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@javax.persistence.Entity
@Data
public class Roles {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String roleName;
}
