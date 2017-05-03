package net.bulldozer.tourofall.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.model.User;

@Repository
@Transactional
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PasswordEncoder encoder;
	public User getUserByUserId(int id){
		Session session = sessionFactory.getCurrentSession();
		return session.get(User.class, id);
	}
	public void addUser(User user){
		Session session = sessionFactory.getCurrentSession();
		user.setPassword(encoder.encode(user.getPassword()));
		Integer id = (Integer) session.save(user);
		System.out.println(user + "" +id);
		
	}
	
	public int checkDuplicate(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where username = :username");
		query.setParameter("username", username);
		List<User> users = query.list();
		System.out.println(users.size());
		if(users.size() == 0){
			return 0;
		}
		return 1;
	}
}
