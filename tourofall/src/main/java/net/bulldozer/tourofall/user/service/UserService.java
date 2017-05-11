package net.bulldozer.tourofall.user.service;

import net.bulldozer.tourofall.user.dto.RegistrationUserForm;
import net.bulldozer.tourofall.user.model.User;

public interface UserService {
	public User registerNewUser(RegistrationUserForm registrationUserForm)throws DuplicateUsernameException;
	public User getUserByUsername(String username);
	public User getUserByUserId(long id);
}
