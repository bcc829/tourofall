package net.bulldozer.tourofall.recommend.dto;

import java.util.List;

public class DestinationEvalForm {
	private List<DestinationEval> destinationEvals;
	
	public DestinationEvalForm(){}

	public DestinationEvalForm(List<DestinationEval> destinationEvals) {
		this.destinationEvals = destinationEvals;
	}

	public List<DestinationEval> getDestinationEvals() {
		return destinationEvals;
	}

	public void setDestinationEvals(List<DestinationEval> destinationEvals) {
		this.destinationEvals = destinationEvals;
	}

	@Override
	public String toString() {
		return "DestinationEvalForm [destinationEvals=" + destinationEvals + "]";
	}
	
}
