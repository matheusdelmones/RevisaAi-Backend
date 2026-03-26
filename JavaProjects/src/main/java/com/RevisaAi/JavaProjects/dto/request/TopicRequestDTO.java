package com.RevisaAi.JavaProjects.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopicRequestDTO {
    
    @NotBlank(message = "Titulo é obrigatório")
    private String title;
    
    @NotBlank(message = "Descrição é obrigatória")
    private String description;
    @JsonProperty("userId")
    @NotNull(message = "Usuário é obrigatório")
    private Long userId;
    
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
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
