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
	public User registerNewUser(RegistrationUserForm registrationUserForm) throws DuplicateUsernameException{
		if(checkUsernameDuplicate(registrationUserForm.getUsername())){
			 throw new DuplicateUsernameException("The Username: " + registrationUserForm.getUsername() + " is already in use.");
		}
		
		User newUser = User.getBuilder()
				.username(registrationUserForm.getUsername())
				.firstName(registrationUserForm.getFirstName())
				.lastName(registrationUserForm.getLastName())
				.password(encoder.encode(registrationUserForm.getPassword()))
				.birth(registrationUserForm.getBirth())
				.gender(registrationUserForm.getGender())
				.signInProvider(registrationUserForm.getSignInProvider())
				.build();
		// user의 role은 따로 건네주지 않음 : 기본적으로 ROLE_USER 권한을 부여받음
		System.out.println(newUser);
		return userRepository.save(newUser);
	}
	
	@Transactional(readOnly=true)
	public User getUserByUserId(long id){
		return userRepository.findOne(id);
	}
	@Transactional(readOnly=true)
	public User getUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	
	private boolean checkUsernameDuplicate(String username) {
		User user = userRepository.findByUsername(username);
		
		if(user != null)
			return true;
		
		return false;
	}
}
