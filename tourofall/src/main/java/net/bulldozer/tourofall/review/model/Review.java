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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.bulldozer.tourofall.user.model.User;
import net.bulldozer.tourofall.util.CheckUserUtil;

@Entity
@Table(name="reviews")
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_id")
	private long id;
	
	private String title;
	
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
	
	public static Builder getBuilder(){
		return new Builder();
	}
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
	
	public static class Builder{
		Review review;
		public Builder(){
			review = new Review();
		}
		public Builder title(String title){
			review.title = title;
			return this;
		}
		public Builder content(String content){
			review.content = content;
			return this;
		}
		public Builder itemId(int itemId){
			review.itemId = itemId;
			return this;
		}
		public Builder itemTypeId(int itemTypeId){
			review.itemTypeId = itemTypeId;
			return this;
		}
		public Builder itemTitle(String itemTitle){
			review.itemTitle = itemTitle;
			return this;
		}
		public Builder user(User user){
			review.user = user;
			return this;
		}
		public Review build(){
			return review;
		}
	}
	
}
