package net.bulldozer.tourofall.qna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.qna.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	public List<Question> findByItemId(int itemId);
}
