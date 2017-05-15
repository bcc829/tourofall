package net.bulldozer.tourofall.question.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class QuestionRenderingModel{
	private long id;
	private String title;
	private String content;
	private Date createdDate;
	private int visitor;
	private String lastName;
	private String firstName;
}
