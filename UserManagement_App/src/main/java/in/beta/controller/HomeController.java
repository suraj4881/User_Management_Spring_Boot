package in.beta.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.beta.model.UserDtls;
import in.beta.repository.UserRepository;
import in.beta.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		if(p!=null) {
		String email=p.getName();
	    UserDtls user=userRepo.findByEmail(email);
	    m.addAttribute("user",user);
	}
	}
	
	@GetMapping("/")
	public String index() {
		
		return "index";
	}
	@GetMapping("/signin")
	public String login() {
		
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		
		return "register";
	}
	@PostMapping("/createUser")
	public String createuser(@ModelAttribute UserDtls user,HttpSession session) {
		
		
		
		boolean flag=userService.checkEmail(user.getEmail());
		
		if(flag) {
			session.setAttribute("msg", "Email Already Exist");
		}
		else {
			
			UserDtls userdtls=userService.createUser(user);
			
			if(userdtls!=null) {
				session.setAttribute("msg", "Register Successfull");
			}
			else {
				session.setAttribute("msg", "Register Failed");
			}
		}
		
		
		return "register";
	}
}
