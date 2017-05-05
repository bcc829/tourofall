package net.bulldozer.tourofall.user.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import net.bulldozer.tourofall.qna.model.Answer;
import net.bulldozer.tourofall.qna.model.Question;
import net.bulldozer.tourofall.review.model.Review;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	
	@NotEmpty(message="ID를 입력해주세요")
	@Size(max=45, message = "최대 45까지 입력 가능합니다.")
	private String username;
	
	@NotEmpty(message="Password를 입력해주세요")
	@Size(min=8, message = "최소8자 이상 입력해주세요.")
	private String password;
	
	@NotEmpty(message="이름을 입력해주세요")
	@Size(max=45, message = "최대 45까지 입력 가능합니다.")
	private String name;
	
	
	private boolean gender;
	
	
	private Date birth;
	private boolean enabled = true;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private UserRole role;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL)
	private Collection<Review> reviews = new ArrayList<Review>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL)
	private Collection<Question> questions = new ArrayList<Question>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL)
	private Collection<Answer> answers = new ArrayList<Answer>();
	
	
	@Transient
	private int checked = -2;
	
	@Transient
	private String year;
	@Transient
	private String month;
	@Transient
	private String date;

	
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth() throws Exception{
		String dateString = year+"/"+month+"/"+date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		birth = sdf.parse(dateString);
	}

	public void setYear(String year) {

		this.year = year;
	}

	public void setMonth(String month) {

		this.month = month;
	}

	public void setDate(String date) {

		this.date = date;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", gender="
				+ gender + ", birth=" + birth + ", enabled=" + enabled + "]";
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
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
	
}
