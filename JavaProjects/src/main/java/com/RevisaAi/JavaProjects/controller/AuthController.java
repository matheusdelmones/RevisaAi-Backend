package com.RevisaAi.JavaProjects.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.RevisaAi.JavaProjects.dto.request.AuthRequestDTO;
import com.RevisaAi.JavaProjects.dto.request.UserRequestDTO;
import com.RevisaAi.JavaProjects.dto.response.TokenResponseDTO;
import com.RevisaAi.JavaProjects.dto.response.UserResponseDTO;
import com.RevisaAi.JavaProjects.model.User;
import com.RevisaAi.JavaProjects.service.TokenService;
import com.RevisaAi.JavaProjects.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userService = userService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody AuthRequestDTO dto) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var token = tokenService.gerarToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenResponseDTO((String) token));
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO request) {
        
        UserResponseDTO response = userService.criarUsuario(request);
        return ResponseEntity.ok(response);

    }
    
}
