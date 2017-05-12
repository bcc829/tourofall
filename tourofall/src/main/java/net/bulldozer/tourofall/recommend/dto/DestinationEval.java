package net.bulldozer.tourofall.recommend.dto;

public class DestinationEval {
	private String itemId;
	private String imageUrl;
	private String title;
	private double score;
	
	public DestinationEval(){}
	public DestinationEval(String itemId,String imageUrl, String title, double score) {
		this.itemId = itemId;
		this.imageUrl = imageUrl;
		this.title = title;
		this.score = score;
	}
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "DestinationEval [itemId=" + itemId + ", imageUrl=" + imageUrl + ", title=" + title + ", score=" + score
				+ "]";
	}
}
