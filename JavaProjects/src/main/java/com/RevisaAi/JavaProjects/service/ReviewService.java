package com.RevisaAi.JavaProjects.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import com.RevisaAi.JavaProjects.exception.ResourceNotFoundException;
import com.RevisaAi.JavaProjects.dto.response.ReviewResponseDTO;
import com.RevisaAi.JavaProjects.dto.update.ReviewUpdateDTO;
import com.RevisaAi.JavaProjects.model.Review;
import com.RevisaAi.JavaProjects.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }

    public Review atualizarReview(Long reviewId, ReviewUpdateDTO dto) {
        Review review = reviewRepository.findById(reviewId)
        .orElseThrow(() -> new ResourceNotFoundException("Revisão nao encontrada!"));
        
            if (dto.getConcluido() != null) {
                review.setConcluido(dto.getConcluido());
            }

            if (dto.getReviewDate() != null) {
                review.setReviewDate(dto.getReviewDate());
            }

        return reviewRepository.save(review);
    }

    public void deletarReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new ResourceNotFoundException("Revisão nao encontrada!");
        }
        reviewRepository.deleteById(reviewId);
    }

    public List <Review> buscarRevisoesDeHoje() {
        return reviewRepository.findByReviewDateAndConcluidoFalse(LocalDate.now());
    }

    @Transactional 
    public ReviewResponseDTO marcarConcluida(Long reviewId) {

        Review review = reviewRepository.findById(reviewId)
        .orElseThrow(() -> new ResourceNotFoundException("Revisão nao encontrada!"));

        review.setConcluido(true);

        Review reviewSalva = reviewRepository.save(review);
        
        return new ReviewResponseDTO(
            reviewSalva.getId(), 
            reviewSalva.getReviewDate(), 
            reviewSalva.getConcluido());
            
    }
}
