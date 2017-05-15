package net.bulldozer.tourofall.evaluation.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.bulldozer.tourofall.user.model.User;

@Entity
@Table(name="evaluations")
public class Evaluation implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="evaluation_id")
	private long id;
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.MERGE)
	private User user;
	
	@Column(name="item_id")
	private int itemId;
	
	private double score;

	public static Builder getBuilder(){
		return new Builder();
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	
	public static class Builder {
		private Evaluation evaluation;
		
		public Builder(){
			evaluation = new Evaluation();
		}
		public Builder user(User user){
			evaluation.user = user;
			return this;
		}
		
		public Builder itemId(int itemId){
			evaluation.itemId = itemId;
			return this;
		}
		
		public Builder score(double score){
			evaluation.score = score;
			return this;
		}
		public Evaluation build(){
			return evaluation;
		}
	}
}
