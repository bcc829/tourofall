package net.bulldozer.tourofall.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.qna.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
