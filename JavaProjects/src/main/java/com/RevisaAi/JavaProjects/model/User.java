package com.RevisaAi.JavaProjects.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users") 
public class User implements UserDetails { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column (nullable = false) 
    private String name; 
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
        
    @Column (nullable = false, unique = true)
    private String email; 
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    @Column(nullable = false, length = 60) 
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Column(nullable = false, updatable = false) 
    private LocalDate dateCreated; 
    @PrePersist 
    public void prePersist() {
        dateCreated = LocalDate.now();
    }
    public LocalDate getDateCreated() {
        return dateCreated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getUsername() {
       
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        
        return true;
    }
    @Override
    public boolean isEnabled() {
        
        return true;
    }
}