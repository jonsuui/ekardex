package com.inacap.ekardex.services;

import java.util.List;

import com.inacap.ekardex.entity.User;
import com.inacap.ekardex.model.UserModel;

public interface UserService {
	public abstract UserModel addUser(UserModel userModel);
	public abstract List<User> listAllUsers();
	public User findUserByEmail(String email);
	public void saveUser(User user);
}