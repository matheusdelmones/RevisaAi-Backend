package com.RevisaAi.JavaProjects.dto.response;

import java.time.LocalDate;
import com.RevisaAi.JavaProjects.model.User;

public class UserResponseDTO {  

    private Long id;
    private String name;
    private String email;
    private LocalDate dateCreated;

    public UserResponseDTO(Long id, String name, String email, LocalDate dateCreated) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateCreated = dateCreated;
    }

    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getDateCreated()
        );
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDate getDateCreated() { return dateCreated; } 
    
}