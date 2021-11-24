package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.UserDto;

public interface UserNotificationService {
	public void notify(UserDto user, UserNotification notification);
}
