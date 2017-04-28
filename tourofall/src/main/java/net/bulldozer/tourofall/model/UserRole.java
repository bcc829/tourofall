package net.bulldozer.tourofall.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_role_id")
	private int id;
	
	@JoinColumn(name="user_id")
	@OneToOne(cascade=CascadeType.ALL)
	private User user;
	
	private String role;

	public UserRole(){}
	public UserRole(String role){
		this.role = role;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
