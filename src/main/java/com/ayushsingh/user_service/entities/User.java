package com.ayushsingh.user_service.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name="micro_users")
@Data
public class User {
    @Id
    @Column(name="userId")
    private String userId;
    @Column(name="userName",nullable = false)
    private String name;
    @Column(name="email",nullable = false, unique = true)
    private String email;
    @Column(name="about")
    private String about;
    @Column(name="phoneNo",nullable = false,unique = true)
    private String phoneNo;

    //we don't have to save the ratings in the database
    @Transient
    private List<Rating> ratings=new ArrayList<>();
}
