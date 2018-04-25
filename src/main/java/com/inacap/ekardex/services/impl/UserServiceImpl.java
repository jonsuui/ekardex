package com.inacap.ekardex.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inacap.ekardex.component.UserConverter;
import com.inacap.ekardex.entity.Role;
import com.inacap.ekardex.entity.User;
import com.inacap.ekardex.model.UserModel;
import com.inacap.ekardex.repository.RoleRepository;
import com.inacap.ekardex.repository.UserRepository;
import com.inacap.ekardex.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    @Qualifier("userConverter")
    private UserConverter userConverter;
    
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public List<User> listAllUsers() {
	    List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public UserModel addUser(UserModel userModel) {
		User user = userRepository.save(userConverter.convertUserModeltoUser(userModel));
		return userConverter.convertUsertoUserModel(user);
	}

}