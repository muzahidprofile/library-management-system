package com.example.librarymanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Cacheable(value = "users", key="#id")
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("No user record exist for given id");
        }
	}
	
	
	@Override
	public List<User> getUsers() {		
		return userRepository.findAll();
	}
	

	@Override
	public List<User> getUsers(Integer page, Integer pageSize, String sortBy) {		
		Pageable pageable = PageRequest.of(page.intValue(), pageSize, Sort.by(sortBy));
		Page<User> userPage = userRepository.findAll(pageable);
		return userPage.getContent();
	}
	
	@Override
	public Page<User> getUsersPage(Integer page, Integer pageSize, String sortBy) {		
		Pageable pageable = PageRequest.of(page.intValue(), pageSize, Sort.by(sortBy));
		Page<User> userPage = userRepository.findAll(pageable);
		return userPage;
	}

	@Override
	@CacheEvict(value = "users", key = "#user.id")
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	@CacheEvict(value = "users", key = "#id")
	public void deleteUserById(Long id) {
		try {
			userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException ex) {
			throw new RuntimeException("No user record exist to delete");
		}
	}
	
	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

	
}
