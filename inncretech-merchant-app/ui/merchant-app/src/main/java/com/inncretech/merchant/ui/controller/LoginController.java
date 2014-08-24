package com.inncretech.merchant.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inncretech.merchant.ui.bean.LoginBean;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

//	@Autowired
//	AuthenticationManager authenticationManager;

	@RequestMapping(method = RequestMethod.GET)
	public String welcomeLogIn(Model model) {
		model.addAttribute("loginBean", new LoginBean());
		return "login";
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public String login(@Valid @ModelAttribute("loginBean") LoginBean loginBean, final BindingResult bindingResult,
//			Model model) {
//		if (bindingResult.hasErrors()) {
//			return null;
//		}
//		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
//				loginBean.getUsername(), loginBean.getPassword());
//		authenticationManager.authenticate(authRequest);
//		return "addProduct";
//	}
}