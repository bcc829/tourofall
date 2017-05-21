package net.bulldozer.tourofall.answer.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerRenderingModel{
	private String lastName;
	private String firstName;
	private Date createdDate;
	private String questionTitle;
	private String content;
	
	
	public static Builder getBuilder(){
		return new Builder();
	}
	
	public static class Builder{
		private AnswerRenderingModel answerRenderingModel;
		
		public Builder(){
			answerRenderingModel = new AnswerRenderingModel();
		}
		public Builder lastName(String lastName){
			answerRenderingModel.lastName = lastName;
			return this;
		}
		public Builder firstName(String firstName){
			answerRenderingModel.firstName = firstName;
			return this;
		}
		public Builder createdDate(Date createdDate){
			answerRenderingModel.createdDate = createdDate;
			return this;
		}
		public Builder questionTitle(String questionTitle){
			answerRenderingModel.questionTitle = questionTitle;
			return this;
		}
		public Builder content(String content){
			answerRenderingModel.content = content;
			return this;
		}
		public AnswerRenderingModel build(){
			return answerRenderingModel;
		}
	}
}
