package net.bulldozer.tourofall.user.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import net.bulldozer.tourofall.user.validation.annotation.FieldMatch;

@FieldMatch(first="password",second="confirmPassword", message="��й�ȣ�� ����ġ�մϴ�.")
public class UserModificationForm {
	private long id;
	
	private String username;
	
	@NotEmpty(message="���� Password�� �Է����ּ���")
	@Size(min=8, message = "�ּ�8�� �̻� �Է����ּ���.")
	private String existingPassword;
	
	@NotEmpty(message="���ο� Password�� �Է����ּ���")
	@Size(min=8, message = "�ּ�8�� �̻� �Է����ּ���.")
	private String newPassword;
	
	@NotEmpty(message="���ο� Password �ߺ�Ȯ����  �Է����ּ���")
	@Size(min=8, message = "�ּ�8�� �̻� �Է����ּ���.")
	private String newConfirmPassword;
	
	@NotEmpty(message="�̸��� �Է����ּ���")
	@Size(max=45, message = "�ִ� 45���� �Է� �����մϴ�.")
	private String firstName;
	
	@NotEmpty(message="���� �Է����ּ���")
	@Size(max=45, message = "�ִ� 45���� �Է� �����մϴ�.")
	private String lastName;
	
	private boolean gender;

	private Date birth;

	private SocialService signInProvider;
	
	private String year;
	private String month;
	private String date;
	
	public void setBirth() throws Exception{
		String dateString = year+"/"+month+"/"+date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		birth = sdf.parse(dateString);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getExistingPassword() {
		return existingPassword;
	}
	public void setExistingPassword(String existingPassword) {
		this.existingPassword = existingPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewConfirmPassword() {
		return newConfirmPassword;
	}
	public void setNewConfirmPassword(String newConfirmPassword) {
		this.newConfirmPassword = newConfirmPassword;
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
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public SocialService getSignInProvider() {
		return signInProvider;
	}
	public void setSignInProvider(SocialService signInProvider) {
		this.signInProvider = signInProvider;
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
