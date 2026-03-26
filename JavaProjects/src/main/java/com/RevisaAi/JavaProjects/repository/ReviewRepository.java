package com.RevisaAi.JavaProjects.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.RevisaAi.JavaProjects.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReviewDateAndConcluidoFalse(LocalDate date);

}
