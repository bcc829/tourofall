package net.bulldozer.tourofall.review.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class ReviewRegistrationForm {
	@NotEmpty(message="������ �Է����ּ���")
	@Size(max=45, message = "�ִ� 45���� �Է� �����մϴ�.")
	private String title;
	
	@NotEmpty(message="������ �Է����ּ���")
	@Size(max=255, message = "�ִ� 255���� �Է� �����մϴ�.")
	private String content;
	
	@Range(min=0,max=5,message="�ִ� 0~5������ �Է� �����մϴ�.")
	private double score;
	
	private int itemId;
	
	private int itemTypeId;

	private String itemTitle;

	public ReviewRegistrationForm() {
	}

	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
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
}
