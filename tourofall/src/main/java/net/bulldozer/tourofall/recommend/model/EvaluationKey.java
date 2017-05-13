package net.bulldozer.tourofall.recommend.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bulldozer.tourofall.user.model.User;

@Embeddable
public class EvaluationKey implements Serializable{
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.MERGE)
	private User user;
	
	@Column(name="item_id")
	private int itemId;

	
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
}
