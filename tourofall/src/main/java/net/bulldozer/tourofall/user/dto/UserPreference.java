package net.bulldozer.tourofall.user.dto;

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
@Table(name="userPreferences")
public class UserPreference {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="item_type_id")
	private int itemTypeId;

	
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

	public int getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	
	public static class Builder{
		UserPreference  userPreference;
		
		public Builder(){
			userPreference = new UserPreference();
		}
		
		public Builder user(User user){
			userPreference.user = user;
			return this;
		}
		public Builder itemTypeId(int itemTypeId){
			userPreference.itemTypeId = itemTypeId;
			return this;
		}
		public UserPreference build(){
			return userPreference;
		}
	}
}
