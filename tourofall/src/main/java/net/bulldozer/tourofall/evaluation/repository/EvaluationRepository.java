package net.bulldozer.tourofall.evaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.evaluation.dto.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{
	public Evaluation findByUserIdAndItemId(long id,int itemId);
}
