package net.bulldozer.tourofall.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.evaluation.model.Evaluation;
import net.bulldozer.tourofall.review.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	public List<Review> findByItemId(int itemId);
	public Review findByUserIdAndItemId(long id,int itemId);
}
