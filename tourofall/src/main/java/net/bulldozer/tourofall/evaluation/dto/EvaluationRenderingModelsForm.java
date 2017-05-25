package net.bulldozer.tourofall.evaluation.dto;

import java.util.List;

public class EvaluationRenderingModelsForm {
	private List<EvaluationRenderingModel> evaluationRenderingModels;
	private long totalPage;
	
	public EvaluationRenderingModelsForm(){}

	public EvaluationRenderingModelsForm(List<EvaluationRenderingModel> evaluationRenderingModels, long totalPage) {
		this.evaluationRenderingModels = evaluationRenderingModels;
		this.totalPage = totalPage;
	}

	public List<EvaluationRenderingModel> getEvaluationRenderingModels() {
		return evaluationRenderingModels;
	}

	public void setEvaluationRenderingModels(List<EvaluationRenderingModel> evaluationRenderingModels) {
		this.evaluationRenderingModels = evaluationRenderingModels;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "EvaluationRenderingModelsForm [evaluationRenderingModels=" + evaluationRenderingModels + ", totalPage="
				+ totalPage + "]";
	}
}
