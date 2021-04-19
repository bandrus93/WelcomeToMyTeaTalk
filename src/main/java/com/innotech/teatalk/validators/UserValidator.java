package com.innotech.teatalk.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.innotech.teatalk.models.User;
import com.innotech.teatalk.repositories.UserRepository;

@Component
public class UserValidator {
	@Autowired
	private UserRepository uRepo;
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if(!user.getPassword().equals(user.getPassConfirm())) {
			errors.rejectValue("passConfirm", "match", "Passwords do not match");
		}
		if(user.getPassword().length() < 8 || user.getPassword().length() > 20) {
			errors.rejectValue("password", "size", "Password must be between 8 and 20 characters long");
		}
		if(uRepo.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "exists", "A user with this email already exists");
		}
	}
}
