package net.bulldozer.tourofall.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerRenderingModel{
	private long id;
	private String questionTitle;
	private String content;
	
}
