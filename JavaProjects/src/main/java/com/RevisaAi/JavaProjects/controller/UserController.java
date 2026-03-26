package com.RevisaAi.JavaProjects.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RevisaAi.JavaProjects.dto.request.UserRequestDTO;
import com.RevisaAi.JavaProjects.dto.response.UserResponseDTO;
import com.RevisaAi.JavaProjects.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api") 
public class UserController { 
    private UserService userService; 

    public UserController(UserService userService) {  
        this.userService = userService;
    }

    @PostMapping("/users") 
    public ResponseEntity<UserResponseDTO> criarUsuario(
        @Valid @RequestBody UserRequestDTO request) {

    UserResponseDTO response = userService.criarUsuario(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    
    } 

    @GetMapping("/users") 
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.listarUsuarios());
    }

    @GetMapping("/users/{id}") 
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.buscarUsuario(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> deletarUser(@PathVariable Long id) {
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/users/{id}") 
    public ResponseEntity<UserResponseDTO> atualizarUser(@PathVariable Long id, @RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(userService.atualizarUsuario(id, request));
    }

}

    



