package ru.gb.hw6.services;

import ru.gb.hw6.exceptions.NoteNotFoundException;
import ru.gb.hw6.model.Note;
import ru.gb.hw6.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found by id: " + id));
    }

    @Override
    public Note createNote(Note note) {
        note.setCreatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }

    @Override
    public Note updateNote(Note note) {
        Note existNote = getNoteById(note.getId());
        existNote.setTitle(note.getTitle());
        existNote.setContent(note.getContent());
        return noteRepository.save(existNote);
    }

    @Override
    public void deleteNote(Long id) {
        getNoteById(id);
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> getNotesByTitleContaining(String keyword) {
        List<Note> noteList = noteRepository.findByTitleContainingIgnoreCase(keyword);
        if (!noteList.isEmpty()) {
            return noteList;
        } else throw new NoteNotFoundException("Notes not found with the title: " + keyword);
    }

    @Override
    public List<Note> getNotesByContentContaining(String content) {
        List<Note> noteList = noteRepository.findNotesByContentContainingIgnoreCase(content);
        if (!noteList.isEmpty()) {
            return noteList;
        } else throw new NoteNotFoundException("Notes not found with the content: " + content);
    }

    @Override
    public List<Note> getNotesByTitleAndContentContaining(String keywordTitle, String keywordContent) {
        List<Note> noteList = noteRepository
                .findByTitleContainingIgnoreCaseAndContentContainingIgnoreCase(
                        keywordTitle, keywordContent);
        if (!noteList.isEmpty()) {
            return noteList;
        } else throw new NoteNotFoundException("Notes not found with the title: " + keywordTitle +
                " and content: " + keywordContent);
    }
}
