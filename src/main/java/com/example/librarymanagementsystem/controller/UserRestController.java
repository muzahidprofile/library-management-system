package com.example.librarymanagementsystem.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagementsystem.dto.UserDto;
import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.service.UserService;

@RestController
@RequestMapping("/api/v1/")
public class UserRestController {
	@Autowired
	private UserService userService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@GetMapping("users/{id}")
	public String home(Model model) {
		return "redirect:/employee/";
	}
	
	
	@GetMapping("users")
	public List<UserDto> user(Model model, Principal user) {
		List<User> users = userService.getUsers();
		
		return users.stream()
		          .map(this::convertToDto)
		          .collect(Collectors.toList());
	}

	private UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
	    return userDto;
	}
	
	private User convertToEntity(UserDto userDto) {
	    User user = modelMapper.map(userDto, User.class);
	    
	    return user;
	}
	
	
}
