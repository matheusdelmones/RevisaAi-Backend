package com.RevisaAi.JavaProjects.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "topic")
public class Topic { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany( 
        fetch = FetchType.LAZY, 
        mappedBy = "topic", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true ) 
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();
    
    @NotBlank(message = "Titulo é obrigatório")
    @Size(min = 5, message = "Titulo deve ter no mínimo 5 caracteres")
    private String title;
    
    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 5, message = "Descrição deve ter no mínimo 5 caracteres")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviews() { 
    return reviews;
    }

    public void setReviews(List<Review> reviews) { 
    this.reviews = reviews;
    }   

    @ManyToOne 
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }  
}
