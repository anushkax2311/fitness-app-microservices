package com.fitness.gateway.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

	
	@NotBlank(message="email is reuqired")
	@Email(message="Inavlid Email format")
	private String email;
	
	@NotBlank(message = "Password is Required")
	@Size(min=6,message = "Password must be atleast 6 characters long")
	private String password;
	
	private String keycloakId;
	
	private String FirstName;
	private String LastName;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	
	
}
