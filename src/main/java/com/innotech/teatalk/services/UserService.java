package com.innotech.teatalk.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innotech.teatalk.models.User;
import com.innotech.teatalk.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	
	public User fetchUser(Long id) {
		return uRepo.findById(id).orElse(null);
	}
	
	public User fetchUser(String email) {
		return uRepo.findByEmail(email);
	}
	
	public List<User> fetchAll() {
		return uRepo.findAll();
	}
	
	public boolean authenticateUser(String email, String password) {
		User user = uRepo.findByEmail(email);
		if(user == null) {
			return false;
		}
		else if(BCrypt.checkpw(password, user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
	
	public User createUser(User user) {
		String hashedPw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedPw);
		return uRepo.save(user);
	}
	
	public void updatePermissions(Long id, int permission) {
		User user = uRepo.findById(id).get();
		user.setId(id);
		user.setPermissions(permission);
		uRepo.save(user);
	}
}
