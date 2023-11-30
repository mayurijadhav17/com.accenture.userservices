package com.accenture.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "organisation")
public class Organisation {

@Id
@GeneratedValue(generator = "UUID")
@Column(name = "id", updatable = false, nullable = false)
private UUID id;
private String name;

}