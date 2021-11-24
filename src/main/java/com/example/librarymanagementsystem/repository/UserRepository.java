package com.example.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagementsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
//	User sfindByfirstName1AND(String firstName);

}
