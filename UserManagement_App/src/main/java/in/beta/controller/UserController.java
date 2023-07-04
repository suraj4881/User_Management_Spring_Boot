package in.beta.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.beta.model.UserDtls;
import in.beta.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;
	
	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		
		String email=p.getName();
	    UserDtls user=userRepo.findByEmail(email);
	    m.addAttribute("user",user);
	}
	@GetMapping("/")
	public String home() {
		
		return "user/home";
		
	}
	@GetMapping("/changpass")
	public String loadChangePassword() {
		
		return "user/change_password";
		
	}
	@PostMapping("/updatePassword")
	public String changePassword(Principal p,@RequestParam("oldPass") String oldPass,@RequestParam("newPass") String newPass ,HttpSession session) {
		
		String email=p.getName();
	    UserDtls loginuser=userRepo.findByEmail(email);
	    
	   boolean  flag = PasswordEncoder.matches(oldPass,loginuser.getPassword());
	   
	   if(flag)
	   {
		   loginuser.setPassword(PasswordEncoder.encode(newPass));
		  UserDtls updatepassword= userRepo.save(loginuser);
		  if(updatepassword!=null) {
			  
			  session.setAttribute("msg", "Password Change Successfully");
		  }
		  else {
			  session.setAttribute("msg", "Password Change Failed");
		  }
	   }
	   else 
	   {
		   session.setAttribute("msg", "OLD Password Incorrect");
	   }
	    
		return "redirect:/user/changpass";
		
	}
	
}
