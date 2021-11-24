package com.example.librarymanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.librarymanagementsystem.entity.User;


public interface UserService {
	public User getUserById(Long id);
	public List<User> getUsers();
	public List<User> getUsers(Integer page, Integer pageSize, String sortBy);
	public void saveUser(User employee);
	public void deleteUserById(Long id);
	public Page<User> getUsersPage(Integer page, Integer pageSize, String sortBy);
	public void deleteAllUsers();

}
