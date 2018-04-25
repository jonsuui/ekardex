package com.inacap.ekardex.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inacap.ekardex.constant.ViewConstant;
import com.inacap.ekardex.entity.User;
import com.inacap.ekardex.model.UserModel;
import com.inacap.ekardex.services.UserService;

@Controller
@RequestMapping("users/")
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public ModelAndView UserList(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Bienvenido Usuario administrador " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("users", userService.listAllUsers());
		modelAndView.setViewName(ViewConstant.USER_LIST_VIEW);
		return modelAndView;
	}
	
	@RequestMapping(value="cancel", method = RequestMethod.GET)
	public ModelAndView cancel() {
		return UserList();
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

	@RequestMapping(value="removeuser", method = RequestMethod.GET)
	public ModelAndView removeUser(@RequestParam(name="id", required=true) int id) {
		userService.removeUser(id);
		return UserList();
	}
}
