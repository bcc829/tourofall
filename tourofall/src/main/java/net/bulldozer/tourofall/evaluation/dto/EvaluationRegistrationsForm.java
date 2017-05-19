package net.bulldozer.tourofall.evaluation.dto;

import java.util.List;

public class EvaluationRegistrationsForm {
	private List<EvaluationRegistration> evaluationRegistrations;
	private long totalPage;
	
	public EvaluationRegistrationsForm(){}

	public EvaluationRegistrationsForm(List<EvaluationRegistration> evaluationRegistrations, long totalPage) {
		this.evaluationRegistrations = evaluationRegistrations;
		this.totalPage = totalPage;
	}

	public List<EvaluationRegistration> getEvaluationRegistrations() {
		return evaluationRegistrations;
	}

	public void setEvaluationRegistrations(List<EvaluationRegistration> evaluationRegistrations) {
		this.evaluationRegistrations = evaluationRegistrations;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "EvaluationRegistrationsForm [evaluationRegistrations=" + evaluationRegistrations + ", totalPage="
				+ totalPage + "]";
	}


}
