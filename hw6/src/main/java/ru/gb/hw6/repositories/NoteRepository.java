package ru.gb.hw6.repositories;


import ru.gb.hw6.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Интерфейс взаимодействия с базой данных
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findById(Long id);
}