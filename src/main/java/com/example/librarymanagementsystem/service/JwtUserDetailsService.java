package com.example.librarymanagementsystem.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.librarymanagementsystem.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.librarymanagementsystem.entity.User user = userRepository.findByEmail(username);
		//if ("javainuse".equals(username)) {/* "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6"*/
		if (user != null) {			
			return new User(user.getEmail(), user.getPassword(),
					user.getRoles());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}