package net.bulldozer.tourofall.dao;

import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.model.Answer;
import net.bulldozer.tourofall.model.FakeUser;
import net.bulldozer.tourofall.model.Question;
import net.bulldozer.tourofall.model.QuestionInfo;
import net.bulldozer.tourofall.model.Review;

@Repository
@Transactional
public class DestinationDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private JdbcOperations jo;
	
	@Autowired
	public DestinationDao(DataSource dataSource){
		jo = new JdbcTemplate(dataSource);
	}
	public FakeUser getUserByUserId(int id){
		Session session = sessionFactory.getCurrentSession();
		return session.get(FakeUser.class, id);
	}
	
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
//	public List<Review> getReviewsByItemId(int itemId){
//		return jo.query("select title, content, created_date, visitor,score, user_id from reviews where item_id=?", new Object[]{itemId}, new RowMapper<Review>(){
//			@Override
//			public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Review review = new Review();
//				review.setTitle(rs.getString("title"));
//				review.setContent(rs.getString("content"));
//				review.setCreatedDate(rs.getDate("created_date"));
//				review.setVisitor(rs.getInt("visitor"));
//				review.setScore(rs.getDouble("score"));
//				review.setUserId(rs.getInt("user_id"));
//				return review;
//			}
//			
//		});
//	}
//	public boolean addReview(Review review) {
//		String title = review.getTitle();
//		String content = review.getContent();
//		double score = review.getScore();
//		int user_id = review.getUser().getId();
//		int item_id = review.getItemId();
//		
//		return (jo.update("insert into reviews(title, content, score, user_id, item_id) values(?,?,?,?,?)", new Object[]{title, content, score, user_id, item_id}) == 1);
//	}
	@SuppressWarnings("unchecked")
	public List<QuestionInfo> getQuestionInfoesByItemId(int itemId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Question where item_id = :item_id");
		query.setParameter("item_id", itemId);
		List<QuestionInfo> questionList = query.list();
		return questionList;
	}
	@SuppressWarnings("unchecked")
	public Question getQuestionById(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, questionId);
		Query query = session.createQuery("from Answer where question_id = :question_id");
		query.setParameter("question_id", questionId);
		List<Answer> answers = query.list();
		question.setAnswers(answers);
		return question;
	}
	
	public QuestionInfo getQuestionInfoById(int questionId) {
		Session session = sessionFactory.getCurrentSession();
		QuestionInfo questionInfo = session.get(QuestionInfo.class, questionId);
		return questionInfo;
	}
	public void addQuestion(Question question) {
		Session session = sessionFactory.getCurrentSession();
		session.save(question);
	}
	public void addAnswer(Answer answer) {
		Session session = sessionFactory.getCurrentSession();
		session.save(answer);
	}
}
