package net.bulldozer.tourofall.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.model.Review;

@Repository
@Transactional
public class ReviewDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Review> getReviewsByItemId(int itemId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Review where item_id = :item_id");
		query.setParameter("item_id", itemId);
		List<Review> reviewList = query.list();
		return reviewList;
	}
	public void addReview(Review review) {
		Session session = sessionFactory.getCurrentSession();
		session.save(review);
	}
}
