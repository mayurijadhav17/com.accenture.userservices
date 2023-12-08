package com.accenture.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_details")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String email;
@ManyToOne
private Organisation organisation;
}