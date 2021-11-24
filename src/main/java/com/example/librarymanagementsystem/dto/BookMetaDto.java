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
public class BookMetaDto {
	private Long id;

	private String title;
	private BookDto book;
	private String email;
	private String username;

}
