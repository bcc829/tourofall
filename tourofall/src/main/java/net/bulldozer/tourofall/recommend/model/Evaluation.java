package net.bulldozer.tourofall.recommend.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.bulldozer.tourofall.user.model.User;

@Entity
@Table(name="evaluations")
public class Evaluation implements Serializable{
	
	@EmbeddedId
	private EvaluationKey key = new EvaluationKey();
	
	
	private double score;

	public static Builder getBuilder(){
		return new Builder();
	}
	public User getUser() {
		return key.getUser();
	}

	public void setUser(User user) {
		key.setUser(user);
	}

	public int getItemId() {
		return key.getItemId();
	}

	public void setItemId(int itemId) {
		key.setItemId(itemId);
	}
	
	

	public EvaluationKey getKey() {
		return key;
	}


	public void setKey(EvaluationKey key) {
		this.key = key;
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
			evaluation.setUser(user);
			return this;
		}
		
		public Builder itemId(int itemId){
			evaluation.setItemId(itemId);
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
