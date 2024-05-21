package ru.gb.hw6.controllers;

import ru.gb.hw6.exceptions.NoteNotFoundException;
import ru.gb.hw6.model.Note;
import ru.gb.hw6.services.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/note")
public class NoteController {

    private final NoteService service;

    /**
     * Создание заметки и запись в базу
     *
     * @param note заметка
     * @return заметка
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(service.createNote(note), HttpStatus.CREATED);
    }

    /**
     * Получение списка заметок
     *
     * @return список заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNote() {
        return new ResponseEntity<>(service.getAllNote(), HttpStatus.OK);
    }

    /**
     * Получение записки по айди
     *
     * @param id айди записки
     * @return записка, если нет записки с таким айди, то ошибка
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable Long id) {
        Optional<Note> note = service.getNoteById(id);
        if (note.isEmpty()) {
            return ResponseEntity.badRequest().body(new NoteNotFoundException("Заметки с данным id не существует"));
        }
        return new ResponseEntity<>(note.get(), HttpStatus.OK);
    }

    /**
     * Обновление записки по айди
     *
     * @param note новые данные записки
     * @param id   айди необходимой запиаски
     * @return измененная записка
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody Note note, @PathVariable Long id) {
        return new ResponseEntity<>(service.updateNote(note, id), HttpStatus.OK);
    }

    /**
     * Удаление записки по айди
     *
     * @param id нужное айди
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        service.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
