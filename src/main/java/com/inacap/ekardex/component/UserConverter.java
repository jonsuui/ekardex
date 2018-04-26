package com.inacap.ekardex.component;

import org.springframework.stereotype.Component;

import com.inacap.ekardex.entity.User;
import com.inacap.ekardex.model.UserModel;

@Component("userConverter")
public class UserConverter {
	public User convertUserModeltoUser(UserModel userModel) {
		User user = new User();
		user.setId(userModel.getId());
		user.setEmail(userModel.getEmail());
		user.setPassword(userModel.getPassword());
		user.setName(userModel.getName());
		user.setLastName(userModel.getLastName());
		user.setActive(1);
		return user;
	}
	public UserModel convertUsertoUserModel(User user) {
		UserModel userModel = new UserModel();
		userModel.setId(user.getId());
		userModel.setEmail(user.getEmail());
		userModel.setPassword(user.getPassword());
		userModel.setName(user.getName());
		userModel.setLastName(user.getLastName());
		userModel.setActive(1);
		return userModel;
	}
}
