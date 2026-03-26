package com.RevisaAi.JavaProjects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import com.RevisaAi.JavaProjects.dto.response.ReviewResponseDTO;
import com.RevisaAi.JavaProjects.exception.ResourceNotFoundException;
import com.RevisaAi.JavaProjects.model.Review;
import com.RevisaAi.JavaProjects.repository.ReviewRepository;
import com.RevisaAi.JavaProjects.service.ReviewService;


@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {
    
    @Mock 
    private ReviewRepository reviewRepository;

    @InjectMocks 
    private ReviewService reviewService;

    @Test
    @DisplayName("Deve buscar as revisoes marcadas como concluidas")
    void deveBuscarRevisoesConcluidas() {
        Review reviewFake = new Review();
        reviewFake.setId(1L);
        reviewFake.setConcluido(false);

        
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(reviewFake));
        when(reviewRepository.save(any(Review.class))).thenAnswer(i -> i.getArguments()[0]);
        

        ReviewResponseDTO resultado = reviewService.marcarConcluida(1L);

        assertTrue(resultado.getConcluido()); 
        verify(reviewRepository, times(1)).save(any(Review.class));         
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar uma revisão inexistente")
    void deveLancarExcecaoAoBuscarRevisaoInexistente() {

        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());        
        assertThrows(ResourceNotFoundException.class, () -> {
            reviewService.marcarConcluida(1L);
        });


    }

}
