package net.bulldozer.tourofall.review.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRenderingModel {
	private String title;
	private String content;
	private Date createdDate;
	private double score;
	private String itemTitle;
}
