package ru.gb.hw6.services;

import  ru.gb.hw6.aspects.TrackUserAction;
import  ru.gb.hw6.model.Note;
import  ru.gb.hw6.repositories.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    /**
     * Создание заметки и запись в базу
     *
     * @param note заметка
     * @return заметка
     */
    @TrackUserAction
    public Note createNote(Note note) {
        return repository.save(note);
    }

    /**
     * Получение списка заметок
     *
     * @return список заметок
     */
    public List<Note> getAllNote() {
        return repository.findAll();
    }

    /**
     * Получение записки по айди
     *
     * @param id айди записки
     * @return записка
     */
    public Optional<Note> getNoteById(Long id) {
        return repository.findById(id);
    }

    /**
     * Обновление записки по айди
     *
     * @param note новые данные записки
     * @param id   айди необходимой запиаски
     * @return измененная записка
     */
    public Note updateNote(Note note, Long id) {
        note.setId(id);
        note.setDateOfCreation(LocalDateTime.now());
        return repository.save(note);
    }

    /**
     * Удаление записки по айди
     *
     * @param id нужное айди
     */
    @TrackUserAction
    public void deleteNote(Long id) {
        repository.deleteById(id);
    }
}