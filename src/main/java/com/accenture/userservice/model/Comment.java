package com.accenture.userservice.model;


import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
}