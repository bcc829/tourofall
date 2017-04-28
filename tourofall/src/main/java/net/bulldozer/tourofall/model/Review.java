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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="reviews")
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_id")
	private int id;
	
	
	@NotEmpty(message="제목을 입력해주세요")
	@Size(max=45, message = "최대 45까지 입력 가능합니다.")
	private String title;
	
	
	@NotEmpty(message="내용을 입력해주세요")
	@Size(max=255, message = "최대 255까지 입력 가능합니다.")
	private String content;
	
	
	@Column(name="created_date")
	private Date createdDate = new Date();
	
	private int visitor;
	private double score;
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.ALL)
	private FakeUser user;
	
	@Column(name="item_id")
	private int itemId;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getVisitor() {
		return visitor;
	}
	public void setVisitor(int visitor) {
		this.visitor = visitor;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public FakeUser getUser() {
		return user;
	}
	public void setUser(FakeUser user) {
		this.user = user;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
}
