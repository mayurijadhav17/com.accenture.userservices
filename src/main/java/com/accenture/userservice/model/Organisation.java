package com.accenture.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "organisation")
public class Organisation {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
private Set<User> user;
}