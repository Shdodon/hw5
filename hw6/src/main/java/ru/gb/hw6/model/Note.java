package ru.gb.hw6.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Тело заметки
 */
@Data
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String header;
    @Column(nullable = false)
    private String description;
    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    /**
     * Предсоздание времени в записки
     */
    @PrePersist
    private void createNote() {
        if (this.dateOfCreation == null) this.dateOfCreation = LocalDateTime.now();
    }
}