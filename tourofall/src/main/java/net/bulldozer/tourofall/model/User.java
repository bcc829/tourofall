package net.bulldozer.tourofall.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	private String username;
	private String password;
	private String name;
	private boolean gender;
	private Date birth;
	private boolean enabled = true;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private UserRole role;
	
	@Transient
	private String year;
	@Transient
	private String month;
	@Transient
	private String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth() throws Exception{
		String dateString = year+"/"+month+"/"+date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		birth = sdf.parse(dateString);
	}

	public void setYear(String year) {

		this.year = year;
	}

	public void setMonth(String month) {

		this.month = month;
	}

	public void setDate(String date) {

		this.date = date;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", gender="
				+ gender + ", birth=" + birth + ", enabled=" + enabled + "]";
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
}
