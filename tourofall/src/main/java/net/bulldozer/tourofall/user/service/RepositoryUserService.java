package net.bulldozer.tourofall.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.user.dto.RegistrationUserForm;
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
	public void registerNewUser(RegistrationUserForm registrationUserForm) {
		User newUser = User.getBuilder()
				.username(registrationUserForm.getUsername())
				.firstName(registrationUserForm.getFirstName())
				.lastName(registrationUserForm.getLastName())
				.password(encoder.encode(registrationUserForm.getPassword()))
				.birth(registrationUserForm.getBirth())
				.gender(registrationUserForm.getGender())
				.build();
		System.out.println(newUser);
		userRepository.save(newUser);
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
