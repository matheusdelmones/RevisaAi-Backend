package com.RevisaAi.JavaProjects;

import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.test.web.servlet.MockMvc;
import com.RevisaAi.JavaProjects.model.User;
import com.RevisaAi.JavaProjects.repository.TopicRepository;
import com.RevisaAi.JavaProjects.repository.UserRepository;
import com.RevisaAi.JavaProjects.service.TokenService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest 
@AutoConfigureMockMvc 
public class UserAuthenticationTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @BeforeEach
    void setup() {
        
        topicRepository.deleteAll();

        userRepository.deleteAll();

        User user = new User();
        user.setName("Matheus Teste");
        user.setEmail("adhasd@gmail.com");
        user.setPassword(passwordEncoder.encode("123456")); 
        
        repository.save(user);
    }

    @Test
    @DisplayName("Deve retornar 200 OK ao acessar com token válido")
    void deveRetornar200ComToken() throws Exception {
        
        User user = repository.findByEmail("adhasd@gmail.com")
                .orElseThrow(() -> new RuntimeException("Não foi possível encontrar o usuário"));   

        String token = tokenService.gerarToken(user);
        
        mockMvc.perform(get("/api/users").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar 403 Forbidden ao acessar com um token malformado")
    void deveRetornar403ComTokenInvalido() throws Exception {
    String tokenInvalido = "eyJhbGciOiJIUzI1NiJ9.token.falso"; 

    mockMvc.perform(get("/api/users")
            .header("Authorization", "Bearer " + tokenInvalido))
            .andExpect(status().isForbidden()); 
    }
}