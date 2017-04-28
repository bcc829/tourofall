package net.bulldozer.tourofall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bulldozer.tourofall.dao.JoinDao;
import net.bulldozer.tourofall.model.User;

@Service
public class JoinService {

	@Autowired
	private JoinDao dao;
	
	public void addUser(User user){
		dao.addUser(user);
	}
}
