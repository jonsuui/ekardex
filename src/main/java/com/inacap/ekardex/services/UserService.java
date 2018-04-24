package com.inacap.ekardex.services;

import java.util.List;

import com.inacap.ekardex.entity.User;

public interface UserService {
	public abstract List<User> listAllUsers();
	public User findUserByEmail(String email);
	public void saveUser(User user);
}