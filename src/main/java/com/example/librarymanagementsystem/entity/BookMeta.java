package com.example.librarymanagementsystem.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import lombok.Data;

@Data
@Entity
public class BookMeta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;
	@OneToOne
	private Book book;
	private String email;
	private String username;
	private String password;
	private boolean enabled;
	private boolean tokenExpired;
}
