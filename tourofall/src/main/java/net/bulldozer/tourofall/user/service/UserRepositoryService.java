package net.bulldozer.tourofall.user.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bulldozer.tourofall.answer.dto.AnswerRenderingModel;
import net.bulldozer.tourofall.answer.model.Answer;
import net.bulldozer.tourofall.evaluation.dto.EvaluationRenderingModel;
import net.bulldozer.tourofall.evaluation.model.Evaluation;
import net.bulldozer.tourofall.question.dto.QuestionRenderingModel;
import net.bulldozer.tourofall.question.model.Question;
import net.bulldozer.tourofall.review.dto.ReviewRenderingModel;
import net.bulldozer.tourofall.review.model.Review;
import net.bulldozer.tourofall.user.dto.UserRegistrationForm;
import net.bulldozer.tourofall.user.model.User;
import net.bulldozer.tourofall.user.repository.UserRepository;


@Service
public class UserRepositoryService implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	@Override
	public User registerNewUser(UserRegistrationForm registrationUserForm) throws DuplicateUsernameException{
		if(checkUsernameDuplicate(registrationUserForm.getUsername())){
			 throw new DuplicateUsernameException("The Username: " + registrationUserForm.getUsername() + " is already in use.");
		}
		
		User newUser = User.getBuilder()
				.username(registrationUserForm.getUsername())
				.firstName(registrationUserForm.getFirstName())
				.lastName(registrationUserForm.getLastName())
				.password(encoder.encode(registrationUserForm.getPassword()))
				.birth(registrationUserForm.getBirth())
				.gender(registrationUserForm.getGender())
				.signInProvider(registrationUserForm.getSignInProvider())
				.build();
		// user의 role은 따로 건네주지 않음 : 기본적으로 ROLE_USER 권한을 부여받음
		System.out.println(newUser);
		return userRepository.save(newUser);
	}
	
	@Transactional(readOnly=true)
	public User getUserByUserId(long id){
		return userRepository.findOne(id);
	}
	@Transactional(readOnly=true)
	public User getUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	
	private boolean checkUsernameDuplicate(String username) {
		User user = userRepository.findByUsername(username);
		
		if(user != null)
			return true;
		
		return false;
	}
	
	@Transactional(readOnly=true)
	@Override
	public int getQuestionsSizeByUserId(long id){
		User user = userRepository.findOne(id);
		return user.getQuestions().size();
	}
	@Transactional(readOnly=true)
	@Override
	public int getAnswersSizeByUserId(long id){
		User user = userRepository.findOne(id);
		return user.getAnswers().size();
	}
	@Transactional(readOnly=true)
	@Override
	public int getReviewsSizeByUserId(long id){
		User user = userRepository.findOne(id);
		return user.getReviews().size();
	}
	@Transactional(readOnly=true)
	@Override
	public int getEvaluationsSizeByUserId(long id){
		User user = userRepository.findOne(id);
		return user.getEvaluations().size();
	}
	
	
	
	@Transactional(readOnly=true)
	@Override
	public Collection<QuestionRenderingModel> getQuestionsByUserId(long id) {
		User user = userRepository.findOne(id);
		Collection<Question> questions = user.getQuestions();
		Collection<QuestionRenderingModel> questionRenderingModels = new ArrayList<QuestionRenderingModel>(); 
		for(Question question : questions){
			QuestionRenderingModel questionRenderingModel = new QuestionRenderingModel();
			questionRenderingModel.setId(question.getId());
			questionRenderingModel.setTitle(question.getTitle());
			questionRenderingModel.setContent(question.getContent());
			questionRenderingModel.setCreatedDate(question.getCreatedDate());
			questionRenderingModel.setLastName(user.getLastName());
			questionRenderingModel.setFirstName(user.getFirstName());
			questionRenderingModel.setVisitor(question.getVisitor());
			questionRenderingModels.add(questionRenderingModel);
		}
		return questionRenderingModels;
	}
	
	@Transactional(readOnly=true)
	@Override
	public Collection<AnswerRenderingModel> getAnswersByUserId(long id){
		User user = userRepository.findOne(id);
		Collection<Answer> answers = user.getAnswers();
		Collection<AnswerRenderingModel> answerRenderingModels = new ArrayList<AnswerRenderingModel>();
		for(Answer answer : answers){
			AnswerRenderingModel answerRenderingModel = new AnswerRenderingModel();
			answerRenderingModel.setId(answer.getId());
			answerRenderingModel.setQuestionTitle(answer.getQuestion().getTitle());
			answerRenderingModel.setContent(answer.getContent());
			answerRenderingModels.add(answerRenderingModel);
		}
		return answerRenderingModels;
	}

	@Override
	public Collection<ReviewRenderingModel> getReviewsByUserId(long id) {
		User user = userRepository.findOne(id);
		Collection<Review> reviews = user.getReviews();
		Collection<ReviewRenderingModel> reviewRenderingModels = new ArrayList<ReviewRenderingModel>();
		
		for(Review review : reviews){
			ReviewRenderingModel reviewRenderingModel = new ReviewRenderingModel();
			reviewRenderingModel.setTitle(review.getTitle());
			reviewRenderingModel.setContent(review.getContent());
			// 보류 ㅣ: reviewRenderingModel.setItemTitle(review.get);
			reviewRenderingModel.setCreatedDate(review.getCreatedDate());
			reviewRenderingModel.setScore(review.getEvaluation().getScore());
			reviewRenderingModels.add(reviewRenderingModel);
		}
		return reviewRenderingModels;
	}

	@Override
	public Collection<EvaluationRenderingModel> getEvaluationsByUserId(long id) {
		User user = userRepository.findOne(id);
		Collection<Evaluation> evaluations = user.getEvaluations();
		Collection<EvaluationRenderingModel> evaluationRenderingModels = new ArrayList<EvaluationRenderingModel>();
		
		for(Evaluation evaluation : evaluations){
			EvaluationRenderingModel evaluationRenderingModel = new EvaluationRenderingModel();
			evaluationRenderingModel.setId(evaluation.getId());
			evaluationRenderingModel.setItemId(evaluation.getItemId());
			evaluationRenderingModel.setScore(evaluation.getScore());
			evaluationRenderingModels.add(evaluationRenderingModel);
		}
		return evaluationRenderingModels;
	}
}
