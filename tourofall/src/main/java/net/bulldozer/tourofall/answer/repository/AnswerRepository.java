package net.bulldozer.tourofall.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.answer.dto.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
