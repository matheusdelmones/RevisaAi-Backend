package com.RevisaAi.JavaProjects.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RevisaAi.JavaProjects.dto.request.TopicRequestDTO;
import com.RevisaAi.JavaProjects.dto.response.TopicResponseDTO;
import com.RevisaAi.JavaProjects.dto.update.TopicUpdateDTO;
import com.RevisaAi.JavaProjects.service.TopicService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}") 
    public ResponseEntity<TopicResponseDTO> buscarTopic(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.buscarTopic(id));
    }

    @GetMapping("/all") 
    public ResponseEntity<List<TopicResponseDTO>> listarTopics() {
        return ResponseEntity.ok(topicService.listarTopics());
    }

    @PostMapping("/create") 
    public ResponseEntity<TopicResponseDTO> createTopic(@Valid @RequestBody TopicRequestDTO dto) { 
        return ResponseEntity.ok(topicService.createTopic(dto));
    }

    @DeleteMapping("delete/{id}") 
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}") 
    public ResponseEntity<TopicResponseDTO> updateTopic(@PathVariable Long id, @RequestBody TopicUpdateDTO dto) {
        ResponseEntity<TopicResponseDTO> response = ResponseEntity.ok(topicService.updateTopic(id, dto));
        return response;
    }

}
