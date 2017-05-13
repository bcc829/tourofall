package net.bulldozer.tourofall.answer.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AnswerRegistrationForm {
	@NotEmpty(message = "������ �Է����ּ���")
	@Size(max = 255, message = "�ִ� 255���� �Է� �����մϴ�.")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
