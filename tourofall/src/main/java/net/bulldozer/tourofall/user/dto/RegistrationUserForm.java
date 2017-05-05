package net.bulldozer.tourofall.user.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationUserForm {
	
	@NotEmpty(message="ID�� �Է����ּ���")
	@Size(max=45, message = "�ִ� 45���� �Է� �����մϴ�.")
	private String username;
	
	@NotEmpty(message="Password�� �Է����ּ���")
	@Size(min=8, message = "�ּ�8�� �̻� �Է����ּ���.")
	private String password;
	
	@NotEmpty(message="�̸��� �Է����ּ���")
	@Size(max=45, message = "�ִ� 45���� �Է� �����մϴ�.")
	private String firstName;
	
	@NotEmpty(message="���� �Է����ּ���")
	@Size(max=45, message = "�ִ� 45���� �Է� �����մϴ�.")
	private String lastName;
	
	private boolean gender;
	
	private Date birth;
	
	private boolean enabled = true;

	private int checked = -2;
	
	private String year;
	private String month;
	private String date;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean getGender() {
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
