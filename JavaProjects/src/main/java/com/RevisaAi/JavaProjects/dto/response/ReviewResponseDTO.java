package com.RevisaAi.JavaProjects.dto.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDTO {
    
    private Long id;
    private LocalDate reviewDate;
    private Boolean concluido;
    
    public ReviewResponseDTO(Long id, LocalDate reviewDate, Boolean concluido) {
        this.id = id;
        this.reviewDate = reviewDate;
        this.concluido = concluido;
    }


}
