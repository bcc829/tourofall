package net.bulldozer.tourofall.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.model.Answer;
import net.bulldozer.tourofall.model.Question;

@Repository
@Transactional
public class QnADao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Question> getQuestionsByItemId(int itemId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Question where item_id = :item_id");
		query.setParameter("item_id", itemId);
		List<Question> questionList = query.list();
		return questionList;
	}
	@SuppressWarnings("unchecked")
	public Question getQuestionById(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		return question;
	}
	public void addQuestion(Question question) {
		Session session = sessionFactory.getCurrentSession();
		session.save(question);
	}
	public void addAnswer(Answer answer) {
		Session session = sessionFactory.getCurrentSession();
		session.save(answer);
	}
	public void incrementVisitor(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question q = session.get(Question.class, questionId);
		q.incrementVisitor();
		session.update(q);
	}
}
