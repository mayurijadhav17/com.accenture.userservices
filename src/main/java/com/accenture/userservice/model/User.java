package com.accenture.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String address;
	private String email;
	private String phonenumber;
@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "fk_org_id", referencedColumnName = "id")
private Organisation organisation;
}