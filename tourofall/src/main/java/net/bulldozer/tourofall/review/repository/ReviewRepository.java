package net.bulldozer.tourofall.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.review.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	public List<Review> findByItemId(int itemId);
}
