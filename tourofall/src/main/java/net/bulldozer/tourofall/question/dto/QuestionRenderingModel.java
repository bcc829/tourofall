package net.bulldozer.tourofall.question.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
public class QuestionRenderingModel{
	private long id;
	private String title;
	private String content;
	private Date createdDate;
	private int visitor;
	private String lastName;
	private String firstName;
	
	public static Builder getBuilder(){
		return new Builder();
	}
	public static class Builder{
		private QuestionRenderingModel questionRenderingModel;
		
		public Builder(){
			questionRenderingModel =new QuestionRenderingModel();
		}
		public Builder id(long id){
			questionRenderingModel.id = id;
			return this;
		}
		public Builder title(String title){
			questionRenderingModel.title=title;
			return this;
		}
		public Builder content(String content){
			questionRenderingModel.content=content;
			return this;
		}
		public Builder createdDate(Date createdDate){
			questionRenderingModel.createdDate = createdDate;
			return this;
		}
		public Builder visitor(int visitor){
			questionRenderingModel.visitor = visitor;
			return this;
		}
		public Builder lastName(String lastName){
			questionRenderingModel.lastName = lastName;
			return this;
		}
		public Builder firstName(String firstName){
			questionRenderingModel.firstName = firstName;
			return this;
		}
		public QuestionRenderingModel build(){
			return questionRenderingModel;
		}
	}
}
