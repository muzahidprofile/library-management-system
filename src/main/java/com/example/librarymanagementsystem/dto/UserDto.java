package com.example.librarymanagementsystem.dto;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import lombok.Data;

@Data
public class UserDto {
	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private boolean enabled;

}
