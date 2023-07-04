package in.beta.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import in.beta.model.UserDtls;
import in.beta.repository.UserRepository;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserRepository userRepo;
	
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		
		String email=p.getName();
	    UserDtls user=userRepo.findByEmail(email);
	    m.addAttribute("user",user);
	}
	@GetMapping("/")
	public String home() {
		
		return "admin/home";
	
}
}