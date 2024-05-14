package ru.gb.hw6.services;

import ru.gb.hw6.model.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes();
    Note getNoteById(Long id);
    Note createNote(Note note);
    Note updateNote(Note note);
    void deleteNote(Long id);
    List<Note> getNotesByTitleContaining(String keyword);
    List<Note> getNotesByContentContaining(String author);
    List<Note> getNotesByTitleAndContentContaining(String keywordTitle, String keywordContent);
}