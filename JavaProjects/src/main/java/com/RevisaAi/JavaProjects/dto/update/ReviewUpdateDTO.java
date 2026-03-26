package com.RevisaAi.JavaProjects.dto.update;

import java.time.LocalDate;

import io.micrometer.common.lang.Nullable;

public class ReviewUpdateDTO {
    
    private LocalDate reviewDate;
    private Boolean concluido;

    public ReviewUpdateDTO(LocalDate reviewDate, Boolean concluido) {
        this.reviewDate = reviewDate;
        this.concluido = concluido;
    }

    @Nullable
    public LocalDate getReviewDate() {
        return reviewDate;
    }

    @Nullable
    public Boolean getConcluido() {
        return concluido;
    }
}
