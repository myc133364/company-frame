package com.hz.controller.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz.controller.BaseController;
import com.hz.entity.example.User;
import com.hz.service.example.UserService;

@Controller
@RequestMapping("/index.do")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("add/add");
	}

	@RequestMapping(params = "method=edit")
	public ModelAndView addUser() {
		User user = new User();
		user.setUsername(getString("username"));
		user.setPassword(getString("password"));
		user.setGender(getString("gender"));
		user.setEmail(getString("email"));
		user.setProvince(getString("province"));
		user.setCity(getString("city"));
		userService.saveUser(user);
		return new ModelAndView("add/ok");
	}

}
