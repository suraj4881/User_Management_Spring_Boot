package in.beta.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import in.beta.model.UserDtls;
import in.beta.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;
	
	@Override
	public UserDtls createUser(UserDtls user) {
		
		user.setPassword(PasswordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		return  userRepo.save(user);
	}


	@Override
	public boolean checkEmail(String email) {
		return userRepo.existsByEmail(email);

	}
	
}
