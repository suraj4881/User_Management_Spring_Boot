package in.beta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.beta.model.UserDtls;


public interface UserRepository extends JpaRepository<UserDtls, Integer>{

	public boolean existsByEmail(String email);
	public UserDtls findByEmail(String email);
	
}
