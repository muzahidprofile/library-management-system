package com.example.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagementsystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String string);

}
