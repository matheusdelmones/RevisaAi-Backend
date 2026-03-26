package com.RevisaAi.JavaProjects.dto.response;

import java.util.List;

import com.RevisaAi.JavaProjects.model.Topic;

public class TopicResponseDTO {
    
    private Long id;
    private String title;
    private String description;
    private List<ReviewResponseDTO> reviewDTOs;

    public TopicResponseDTO(Long id, String title, String description,List<ReviewResponseDTO> reviewDTOs) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.reviewDTOs = reviewDTOs;
    }

    public TopicResponseDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.description = topic.getDescription();
        List<ReviewResponseDTO> reviewDTOs = topic.getReviews()
        .stream()
        .map(review -> new ReviewResponseDTO(review.getId(), 
        review.getReviewDate(), 
        review.getConcluido()))
        .toList();
        this.reviewDTOs = reviewDTOs;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<ReviewResponseDTO> getReviews() { return reviewDTOs; }
}
