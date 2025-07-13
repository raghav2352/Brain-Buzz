package com.app.brainbuzz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClassroomSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(unique = true , nullable = false)
    private String joinCode;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "session" , cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonManagedReference
    private List<Question> questions;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
