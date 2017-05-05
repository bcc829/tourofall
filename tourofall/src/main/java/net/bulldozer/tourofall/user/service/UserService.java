package net.bulldozer.tourofall.user.service;

import net.bulldozer.tourofall.user.model.User;

public interface UserService {
	public User registerNewUser(User user);
	public User getUserByUserId(long id);
	public int checkDuplicate(String username);
}
