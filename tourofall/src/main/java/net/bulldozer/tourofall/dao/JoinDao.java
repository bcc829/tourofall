package net.bulldozer.tourofall.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.model.User;

@Repository
@Transactional
public class JoinDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public void addUser(User user){
		Session session = sessionFactory.getCurrentSession();
		
		Integer id = (Integer) session.save(user);
		System.out.println(user + "" +id);
		
	}
}
