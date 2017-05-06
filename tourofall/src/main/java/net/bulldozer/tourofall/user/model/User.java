package net.bulldozer.tourofall.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import net.bulldozer.tourofall.qna.model.Answer;
import net.bulldozer.tourofall.qna.model.Question;
import net.bulldozer.tourofall.review.model.Review;
import net.bulldozer.tourofall.security.dto.Role;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	
	private String username;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String password;
	private boolean gender;
	private Date birth;
	
	
	@Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role = Role.ROLE_USER;
	
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL)
	private Collection<Review> reviews = new ArrayList<Review>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL)
	private Collection<Question> questions = new ArrayList<Question>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL)
	private Collection<Answer> answers = new ArrayList<Answer>();


	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public static Builder getBuilder(){
		return new Builder();
	}
	
	public Collection<Review> getReviews() {
		return new ArrayList<Review>(reviews);
	}

	public void addReview(Review review){
		if(reviews.contains(review))
			return ;
		reviews.add(review);
		review.setUser(this);
	}
	public void removeReview(Review review){
		if(reviews.contains(review))
			return ;
		reviews.remove(review);
		review.setUser(null);
	}
	
	public Collection<Question> getQuestions(){
		return new ArrayList<Question>(questions);
	}
	

	public void addQuestion(Question question){
		if(questions.contains(question))
			return ;
		questions.add(question);
		question.setUser(this);
	}
	public void removeQuestion(Question question){
		if(questions.contains(question))
			return ;
		questions.remove(question);
		question.setUser(null);
	}
	
	public Collection<Answer> getAnswers(){
		return new ArrayList<Answer>(answers);
	}
	

	public void addAnswer(Answer answer){
		if(answers.contains(answer))
			return ;
		answers.add(answer);
		answer.setUser(this);
	}
	public void removeAnswer(Answer answer){
		if(answers.contains(answer))
			return ;
		answers.remove(answer);
		answer.setUser(null);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}
	public Role getRole() {
		return role;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	
	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getId());
        return builder.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
            return false;
        }
		User otherUser  = (User) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.id, otherUser.getId());
        return builder.isEquals();
	}
	
	public static class Builder{
		private User user;
		
		public Builder(){
			user = new User();
			user.role = Role.ROLE_USER;
		}
		public Builder username(String username){
			user.username = username;
			return this;
		}
		public Builder password(String password){
			user.password = password;
			return this;
		}
		public Builder firstName(String firstName){
			user.firstName = firstName;
			return this;
		}
		public Builder lastName(String lastName){
			user.lastName = lastName;
			return this;
		}
		public Builder birth(Date birth){
			user.birth = birth;
			return this;
		}
		public Builder gender(boolean gender){
			user.gender = gender;
			return this;
		}
		public Builder role(Role role){
			user.role = role;
			return this;
		}
		public User build(){
			return user;
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", gender=" + gender + ", birth=" + birth + ", role=" + role + ", reviews="
				+ reviews + ", questions=" + questions + ", answers=" + answers + "]";
	}
}
