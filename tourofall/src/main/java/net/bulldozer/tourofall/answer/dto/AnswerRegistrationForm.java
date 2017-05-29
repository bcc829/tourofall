package net.bulldozer.tourofall.answer.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AnswerRegistrationForm {
	@NotEmpty(message = "������ �Է����ּ���")
	@Size(max = 255, message = "�ִ� 255���� �Է� �����մϴ�.")
	private String content;

	private long questionId;
	
	
	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
