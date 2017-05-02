package net.bulldozer.tourofall.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="answers")
public class Answer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="answer_id")
	private int id;
	
	private String content;
	
	@Column(name="created_date")
	private Date createdDate = new Date();
	
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.MERGE)
	private FakeUser user;
	
	
	@JoinColumn(name="question_id")
	@ManyToOne(cascade=CascadeType.MERGE)
	private QuestionInfo question;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public FakeUser getUser() {
		return user;
	}

	public void setUser(FakeUser user) {
		this.user = user;
	}

	public QuestionInfo getQuestion() {
		return question;
	}

	public void setQuestion(QuestionInfo question) {
		this.question = question;
	}
}
