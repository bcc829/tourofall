package net.bulldozer.tourofall.evaluation.dto;

import java.util.List;

public class EvaluationRegistrationsForm {
	private List<EvaluationRegistration> evaluationRegistrations;
	
	public EvaluationRegistrationsForm(){}

	public EvaluationRegistrationsForm(List<EvaluationRegistration> evaluationRegistrations) {
		this.evaluationRegistrations = evaluationRegistrations;
	}

	public List<EvaluationRegistration> getEvaluationRegistrations() {
		return evaluationRegistrations;
	}

	public void setEvaluationRegistrations(List<EvaluationRegistration> evaluationRegistrations) {
		this.evaluationRegistrations = evaluationRegistrations;
	}

	@Override
	public String toString() {
		return "EvaluationRegistrationsForm [evaluationRegistrations=" + evaluationRegistrations + "]";
	}
}
