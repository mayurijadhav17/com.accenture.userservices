package com.accenture.userservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


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
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private List<Comment> commentsList;
}