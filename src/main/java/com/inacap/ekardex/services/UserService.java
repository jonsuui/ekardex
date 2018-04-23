package com.inacap.ekardex.services;

import com.inacap.ekardex.entity.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public void updateEmail(User user,String email);
}