package cn.xxx.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(HttpSession session, String username, String password) 
			throws Exception{
		
		//下面的代码是判断是否存在该用户
		//...
		
		session.setAttribute("username", username);
		
		return "redirect:/items/queryItems.action";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "login";
	}
	
}





















