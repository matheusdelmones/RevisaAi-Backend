package com.RevisaAi.JavaProjects.config;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.RevisaAi.JavaProjects.model.Review;
import com.RevisaAi.JavaProjects.model.Topic;
import com.RevisaAi.JavaProjects.repository.ReviewRepository;
import com.RevisaAi.JavaProjects.repository.TopicRepository;

@Profile("dev") 
@Component 
public class DataSeeder implements CommandLineRunner{ 

    private static final Long DEFAULT_TOPIC_ID = 1L; 

    private final ReviewRepository reviewRepository; 
    private final TopicRepository topicRepository;

    public DataSeeder(ReviewRepository reviewRepository, TopicRepository topicRepository) {
        this.reviewRepository = reviewRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public void run(String... args) throws Exception { 
    ensureTopicsExist();   
    ensureReviewsExist();  
}

    private Topic getOrCreateTopic(Long id) { 
        return topicRepository.findById(id)
            .orElseGet(()-> {
                Topic topic = new Topic();
                topic.setTitle("Teste");
                topic.setDescription("Teste");
                return topicRepository.save(topic);
            });
    }

    private void ensureTopicsExist() { 
    if (topicRepository.count() == 0) {
        getOrCreateTopic(DEFAULT_TOPIC_ID);
        System.out.println("Tópicos não encontrados, criando...");
    }
}

    private void ensureReviewsExist() { 
        if (reviewRepository.findByReviewDateAndConcluidoFalse(LocalDate.now()).isEmpty()) {
            Review review = new Review();
            review.setConcluido(false);
            review.setReviewDate(LocalDate.now());
            review.setTopic(getOrCreateTopic(DEFAULT_TOPIC_ID));
            reviewRepository.save(review);
            System.out.println("Revisões nao encontradas, criando...");
        }else{
            System.out.println("O dashboard ja foi criado!");
        }
    }




}
