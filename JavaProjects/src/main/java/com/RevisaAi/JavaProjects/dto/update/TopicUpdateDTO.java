package com.RevisaAi.JavaProjects.dto.update;

import org.springframework.lang.Nullable;

public class TopicUpdateDTO {

    @Nullable 
    private String title;
    
    @Nullable
    private String description;
    
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
}