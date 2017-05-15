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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.bulldozer.tourofall.common.util.CheckUserUtil;
import net.bulldozer.tourofall.evaluation.model.Evaluation;
import net.bulldozer.tourofall.user.model.User;

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
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.MERGE)
	private User user;
	
	@Column(name="item_id")
	private int itemId;
	
	
	@JoinColumn(name="evaluation_id")
	@OneToOne(cascade={CascadeType.PERSIST})
	private Evaluation evaluation = new Evaluation();
	
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
	
	
	public Evaluation getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
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
		public Builder user(User user){
			review.user = user;
			return this;
		}
		public Builder evaluation(Evaluation evaluation){
			review.evaluation = evaluation;
			return this;
		}
		public Review build(){
			return review;
		}
	}
	
}
