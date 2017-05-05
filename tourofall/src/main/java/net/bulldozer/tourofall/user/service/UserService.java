package net.bulldozer.tourofall.user.service;

import net.bulldozer.tourofall.user.dto.RegistrationUserForm;
import net.bulldozer.tourofall.user.model.User;

public interface UserService {
	public void registerNewUser(RegistrationUserForm registrationUserForm);
	public User getUserByUserId(long id);
	public int checkDuplicate(String username);
}
