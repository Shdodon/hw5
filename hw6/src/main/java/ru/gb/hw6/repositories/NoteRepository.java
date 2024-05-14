package ru.gb.hw6.repositories;

import ru.gb.hw6.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findById(Long id);
    List<Note> findByTitleContainingIgnoreCase(String keywordTitle);

    List<Note> findNotesByContentContainingIgnoreCase(String keywordContent);

    List<Note> findByTitleContainingIgnoreCaseAndContentContainingIgnoreCase(
            String keywordTitle, String keywordContent);
}