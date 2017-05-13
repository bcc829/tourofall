package net.bulldozer.tourofall.evaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.evaluation.model.Evaluation;
import net.bulldozer.tourofall.evaluation.model.EvaluationKey;

public interface EvaluationRepository extends JpaRepository<Evaluation, EvaluationKey>{
}
