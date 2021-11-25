package com.example.librarymanagementsystem.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagementsystem.dto.UserDto;
import com.example.librarymanagementsystem.dto.UserPostDto;
import com.example.librarymanagementsystem.entity.Role;
import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.service.UserService;

@RestController
@RequestMapping("/api/v1/")
public class UserRestController {
	@Autowired
	private UserService userService;
	
	@Autowired
    private ModelMapper modelMapper;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@GetMapping("users/{id}")
	public String home(Model model) {
		return "redirect:/employee/";
	}
	
	
	@GetMapping("users")
	public List<UserDto> getUser(Model model, Principal user) {
		List<User> users = userService.getUsers();
		
		return users.stream()
		          .map(this::convertToDto)
		          .collect(Collectors.toList());
	}

	@PostMapping(value="users", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> postUser(@RequestBody UserPostDto user) {
		User userEntity =  convertToEntity(user);

		userService.saveUser(userEntity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	private UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
	    return userDto;
	}
	
	private User convertToEntity(UserDto userDto) {
	    User user = modelMapper.map(userDto, User.class);
	    
	    return user;
	}
	private User convertToEntity(UserPostDto userPostDto) {
	    User user = modelMapper.map(userPostDto, User.class);
	    if (user.getPassword() != null) {
	    	user.setPassword(passwordEncoder.encode(user.getPassword()));
	    }
	    
	    return user;
	}
	
}
