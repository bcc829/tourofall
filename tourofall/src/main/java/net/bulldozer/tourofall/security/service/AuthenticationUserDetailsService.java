package net.bulldozer.tourofall.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.bulldozer.tourofall.security.dto.AuthenticationUserDetails;
import net.bulldozer.tourofall.user.model.User;
import net.bulldozer.tourofall.user.repository.UserRepository;

public class AuthenticationUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		
		
		if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
		
		
		
		AuthenticationUserDetails userDetails = AuthenticationUserDetails.getBuilder()
				.username(user.getUsername())
				.password(user.getPassword())
				.role(user.getRole())
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.birth(user.getBirth())
				.gender(user.getGender())
				.build();
		
		
		return userDetails;
	}

}
