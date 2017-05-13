package net.bulldozer.tourofall.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.recommend.model.Evaluation;
import net.bulldozer.tourofall.recommend.model.EvaluationKey;

public interface EvaluationRepository extends JpaRepository<Evaluation, EvaluationKey>{
}
