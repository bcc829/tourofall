package net.bulldozer.tourofall.user.service;

import net.bulldozer.tourofall.user.dto.UserRegistrationForm;
import net.bulldozer.tourofall.user.model.User;

public interface UserService {
	public User registerNewUser(UserRegistrationForm registrationUserForm)throws DuplicateUsernameException;
	public User getUserByUsername(String username);
	public User getUserByUserId(long id);
}
