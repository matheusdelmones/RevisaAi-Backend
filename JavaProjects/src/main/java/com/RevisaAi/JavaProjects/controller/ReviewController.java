package com.RevisaAi.JavaProjects.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RevisaAi.JavaProjects.dto.response.ReviewResponseDTO;
import com.RevisaAi.JavaProjects.dto.update.ReviewUpdateDTO;
import com.RevisaAi.JavaProjects.model.Review;
import com.RevisaAi.JavaProjects.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    
    private final ReviewService reviewService;
    
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PatchMapping("/{id}") 
    public ResponseEntity<Review> atualizarReview(@PathVariable Long id, @RequestBody ReviewUpdateDTO dto) {
      return ResponseEntity.ok(reviewService.atualizarReview(id, dto));
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deletarReview(@PathVariable Long id) {
        reviewService.deletarReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hoje") 
    public ResponseEntity<List<Review>> buscarRevisoesDeHoje() {
        return ResponseEntity.ok(reviewService.buscarRevisoesDeHoje());
    }

    @PatchMapping("/concluida/{id}") 
    public ResponseEntity<ReviewResponseDTO> marcarConcluida(@PathVariable Long id) {
        ReviewResponseDTO review = reviewService.marcarConcluida(id);
        return ResponseEntity.ok(review);
    }
}
