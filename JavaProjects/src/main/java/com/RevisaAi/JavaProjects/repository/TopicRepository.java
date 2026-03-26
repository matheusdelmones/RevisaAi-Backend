package com.RevisaAi.JavaProjects.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.RevisaAi.JavaProjects.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByUserId(Long userId); 
}
