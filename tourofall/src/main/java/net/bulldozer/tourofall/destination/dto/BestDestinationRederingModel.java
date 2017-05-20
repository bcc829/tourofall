package net.bulldozer.tourofall.destination.dto;

public class BestDestinationRederingModel {
	private String title;	
	private String address;
	private String imageUrl;
	private String destinationType;
	private double meanScore;
	
	
	public static Builder getBuilder(){
		return new Builder();
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
	public double getMeanScore() {
		return meanScore;
	}
	public void setMeanScore(double meanScore) {
		this.meanScore = meanScore;
	}

	public static class Builder{
		private BestDestinationRederingModel bestDestinationRederingModel;
		
		public Builder(){
			bestDestinationRederingModel = new BestDestinationRederingModel();
		}
		public Builder title(String title){
			bestDestinationRederingModel.title = title;
			return this;
		}
		public Builder address(String address){
			bestDestinationRederingModel.address = address;
			return this;
		}
		public Builder imageUrl(String imageUrl){
			bestDestinationRederingModel.imageUrl = imageUrl;
			return this;
		}
		public Builder destinationType(String destinationType){
			bestDestinationRederingModel.destinationType = destinationType;
			return this;
		}
		public Builder meanScore(double meanScore){
			bestDestinationRederingModel.meanScore = meanScore;
			return this;
		}
		public BestDestinationRederingModel build(){
			return bestDestinationRederingModel;
		}
	}
}
