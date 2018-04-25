package com.inacap.ekardex.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.inacap.ekardex.constant.ViewConstant;
import com.inacap.ekardex.entity.User;
import com.inacap.ekardex.services.UserService;

@Controller
@RequestMapping("admin/")
public class AdminController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName(ViewConstant.REGISTRATION_VIEW);
		return modelAndView;
	}
	
	@RequestMapping(value="home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Bienvenido Usuario administrador " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Este contenido es solo para usuarios administradores.");
		modelAndView.addObject("users", userService.listAllUsers());
		modelAndView.setViewName(ViewConstant.HOME_ADMIN);
		return modelAndView;
	}
	
}
