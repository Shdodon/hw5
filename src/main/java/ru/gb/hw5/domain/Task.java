package ru.gb.hw5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    /**
     * Поле id задачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    /**
     * Поле описание задачи
     */
    @Column(nullable = false)
    private String description;
    /**
     * Поле статус задачи
     */
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    /**
     * Поле дата создания задачи
     */
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}