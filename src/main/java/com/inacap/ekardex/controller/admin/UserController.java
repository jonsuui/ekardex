package com.inacap.ekardex.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inacap.ekardex.constant.ViewConstant;
import com.inacap.ekardex.model.UserModel;
import com.inacap.ekardex.services.UserService;

@Controller
@RequestMapping("users/")
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@RequestMapping(value="cancel", method = RequestMethod.GET)
	public String cancel() {
		return ViewConstant.HOME_ADMIN;
	}
	
	@RequestMapping(value="userform", method = RequestMethod.GET)
	public String redirectUserForm(Model model){
		model.addAttribute("userModel", new UserModel());
		return ViewConstant.USER_FORM;
	}
	
	@PostMapping(value="adduser")
	public String addcontact(@ModelAttribute(name="usermodel") UserModel userModel) {
		userService.addUser(userModel);
		return ViewConstant.HOME_ADMIN;
	}
}
