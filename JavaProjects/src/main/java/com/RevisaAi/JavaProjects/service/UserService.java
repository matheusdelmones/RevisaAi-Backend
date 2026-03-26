package com.RevisaAi.JavaProjects.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.RevisaAi.JavaProjects.dto.request.UserRequestDTO;
import com.RevisaAi.JavaProjects.dto.response.UserResponseDTO;
import com.RevisaAi.JavaProjects.exception.ResourceNotFoundException;
import com.RevisaAi.JavaProjects.model.User;
import com.RevisaAi.JavaProjects.repository.UserRepository;

    
    @Service
    public class UserService {   

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public UserResponseDTO criarUsuario(UserRequestDTO request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email ja cadastrado!");
        }

        User user = request.toEntity();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

            return UserResponseDTO.fromEntity(savedUser);
        }

    public List<UserResponseDTO> listarUsuarios() {
        return userRepository.findAll()
            .stream()
            .map(UserResponseDTO::fromEntity)
            .toList();
    }

    public UserResponseDTO buscarUsuario(Long id) {
        User user = findUserOrThrow(id);
        return UserResponseDTO.fromEntity(user);
    }

    public UserResponseDTO deletarUsuario(Long id) {
        User user = findUserOrThrow(id);
        userRepository.delete(user);
        return UserResponseDTO.fromEntity(user);
    }

    public UserResponseDTO atualizarUsuario(Long id, UserRequestDTO request) {

        User userExistente = findUserOrThrow(id);

        if (request.getName() != null) {    

            userExistente.setName(request.getName());

        }

        if (request.getEmail() != null) {

            userExistente.setEmail(request.getEmail());

        }


        if (request.getPassword() != null) { 

            userExistente.setPassword(passwordEncoder.encode(request.getPassword()));
            

        }

        User userAtualizado = userRepository.save(userExistente);
        return UserResponseDTO.fromEntity(userAtualizado);

    }



}