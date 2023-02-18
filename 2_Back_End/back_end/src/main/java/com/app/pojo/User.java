package com.app.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
