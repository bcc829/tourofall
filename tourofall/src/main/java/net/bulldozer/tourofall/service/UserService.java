package net.bulldozer.tourofall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bulldozer.tourofall.dao.UserDao;
import net.bulldozer.tourofall.model.User;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	
	public User getUserByUserId(int userId){
		return dao.getUserByUserId(userId);
	}
	public void addUser(User user){
		dao.addUser(user);
	}
}
