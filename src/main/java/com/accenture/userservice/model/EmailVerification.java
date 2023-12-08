package com.accenture.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "email_verification")
public class EmailVerification {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Integer totalAttempts;
private String token;
private Date expiryDate;
private Long userId;

}