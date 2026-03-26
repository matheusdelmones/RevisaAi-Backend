package com.RevisaAi.JavaProjects.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.RevisaAi.JavaProjects.dto.request.TopicRequestDTO;
import com.RevisaAi.JavaProjects.dto.response.TopicResponseDTO;
import com.RevisaAi.JavaProjects.dto.update.TopicUpdateDTO;
import com.RevisaAi.JavaProjects.exception.ResourceNotFoundException;
import com.RevisaAi.JavaProjects.model.Review;
import com.RevisaAi.JavaProjects.model.Topic;
import com.RevisaAi.JavaProjects.model.User;
import com.RevisaAi.JavaProjects.repository.TopicRepository;
import com.RevisaAi.JavaProjects.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TopicService {

    private Topic findTopicOrThrow(Long id) {
        return topicRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado com ID: " + id));
    }

    public TopicResponseDTO buscarTopic(Long id) {
        return new TopicResponseDTO(findTopicOrThrow(id));  
    }

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    public List<TopicResponseDTO> listarTopics () {
        return topicRepository.findAll().stream().map(TopicResponseDTO::new).toList(); 
    }

    @Transactional
    public TopicResponseDTO createTopic(TopicRequestDTO dto) { 
    
    if (dto.getUserId() == null) {
        throw new IllegalArgumentException("O ID do usuário não foi enviado corretamente no JSON!");
    }

    User user = userRepository
        .findById(dto.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("Usuário nao encontrado com ID: " + dto.getUserId()));
        
        Topic topic = new Topic();
        topic.setTitle(dto.getTitle());
        topic.setDescription(dto.getDescription());
        topic.setUser(user);
        
        int [] intervalos = {1,3,7,30};
        for (int i = 0; i < intervalos.length; i++) {
            Review review = new Review();
            review.setTopic(topic);
            review.setReviewDate(LocalDate.now().plusDays(intervalos[i]));
            topic.getReviews().add(review);
        }

        Topic savedTopic = topicRepository.save(topic);
        return new TopicResponseDTO(savedTopic);
    }

    public void deleteTopic(Long id) {
        Topic topic = findTopicOrThrow(id);
        topicRepository.delete(topic);
    }

    private void atualizarCampos(Topic topic, TopicUpdateDTO dto) {

        if (dto.getTitle() != null && dto.getTitle().length() > 5) {
            topic.setTitle(dto.getTitle());
        }else if (dto.getTitle() != null) {
            throw new IllegalArgumentException("Titulo deve ter no mínimo 5 caracteres!");
        }
        
        if (dto.getDescription() != null && dto.getDescription().length() > 5) {
            topic.setDescription(dto.getDescription());
        }else if (dto.getDescription() != null) {
            throw new IllegalArgumentException("Descrição deve ter no mínimo 5 caracteres!");
        }
    }

    @Transactional 
    public TopicResponseDTO updateTopic(Long id, TopicUpdateDTO dto) {
        Topic topic = findTopicOrThrow(id);
        atualizarCampos(topic, dto);
        Topic updatedTopic = topicRepository.save(topic);
        return new TopicResponseDTO(updatedTopic);
    }
    

}
