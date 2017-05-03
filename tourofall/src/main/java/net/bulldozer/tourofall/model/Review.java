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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="reviews")
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_id")
	private int id;
	
	
	@NotEmpty(message="������ �Է����ּ���")
	@Size(max=45, message = "�ִ� 45���� �Է� �����մϴ�.")
	private String title;
	
	
	@NotEmpty(message="������ �Է����ּ���")
	@Size(max=255, message = "�ִ� 255���� �Է� �����մϴ�.")
	private String content;
	
	
	@Column(name="created_date")
	private Date createdDate = new Date();
	
	private double score;
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	
	@Column(name="item_id")
	private int itemId;
	@Column(name="item_type_id")
	private int itemTypeId;
	
	@Column(name="item_title")
	private String itemTitle;
	
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
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
}
