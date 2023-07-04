package in.beta.service;

import in.beta.model.UserDtls;

public interface UserService {

	public UserDtls createUser(UserDtls user);
	public boolean checkEmail(String email);
	
}
