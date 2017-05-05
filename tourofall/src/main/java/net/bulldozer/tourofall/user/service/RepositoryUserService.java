package net.bulldozer.tourofall.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.user.model.User;
import net.bulldozer.tourofall.user.repository.UserRepository;


@Service
public class RepositoryUserService implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	@Override
	public User registerNewUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@Transactional(readOnly=true)
	public User getUserByUserId(long id){
		return userRepository.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public int checkDuplicate(String username) {
		User user = userRepository.findByUsername(username);
		if(user == null){
			return 0;
		}
		return 1;
	}
}
