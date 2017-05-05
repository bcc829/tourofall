package net.bulldozer.tourofall.review.model;

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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import net.bulldozer.tourofall.user.model.User;
import net.bulldozer.tourofall.util.CheckUserUtil;

@Entity
@Table(name="reviews")
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_id")
	private long id;
	
	
	@NotEmpty(message="제목을 입력해주세요")
	@Size(max=45, message = "최대 45까지 입력 가능합니다.")
	private String title;
	
	
	@NotEmpty(message="내용을 입력해주세요")
	@Size(max=255, message = "최대 255까지 입력 가능합니다.")
	private String content;
	
	
	@Column(name="created_date")
	private Date createdDate = new Date();
	
	private double score;
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.MERGE)
	private User user;
	
	@Column(name="item_id")
	private int itemId;
	@Column(name="item_type_id")
	private int itemTypeId;
	
	@Column(name="item_title")
	private String itemTitle;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public void setUser(User newUser) {
		if(CheckUserUtil.sameAsFormer(user, newUser))
			return ;
		User oldUser = this.user;
		this.user = newUser;
		if(oldUser != null)
			oldUser.removeReview(this);
		if(user != null)
			user.addReview(this);
		
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
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getId());
        return builder.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Review)) {
            return false;
        }
        Review otherReview  = (Review) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.id, otherReview.getId());
        return builder.isEquals();
	}
	
	
	
}
