package in.beta.model;

import java.io.Serializable;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;


@Data
@Entity
public class UserDtls implements Serializable{
  
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    private String fullName;
	
	private long mobNo;
	
	private String email;
	
	private String address;
	
	private String qualification;
	
	private String password;
	
	private String role;
}
