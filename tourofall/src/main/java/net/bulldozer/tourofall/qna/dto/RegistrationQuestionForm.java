package net.bulldozer.tourofall.qna.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationQuestionForm {
	@NotEmpty(message = "제목을 입력해주세요")
	@Size(max = 45, message = "최대 45까지 입력 가능합니다.")
	private String title;
	
	@NotEmpty(message = "내용을 입력해주세요")
	@Size(max = 255, message = "최대 255까지 입력 가능합니다.")
	private String content;

	private int itemId;	
	private int itemTypeId;
	
	
	
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
}
