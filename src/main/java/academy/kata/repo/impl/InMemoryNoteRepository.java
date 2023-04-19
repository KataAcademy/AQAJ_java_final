package academy.kata.repo.impl;


import academy.kata.model.Note;
import academy.kata.repo.NoteRepository;
import academy.kata.util.IdGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InMemoryNoteRepository implements NoteRepository {
    private final Map<Long, Note> noteStorage = new HashMap<>();

    @Override
    public void save(Note note) {
        note.setId(IdGenerator.getNextId());
        noteStorage.put(note.getId(), note);
    }

    @Override
    public List<Note> findAll() {
        return new ArrayList<>(noteStorage.values());
    }

    @Override
    public void deleteById(Long id) {
        noteStorage.remove(id);
    }

    @Override
    public List<Note> findByLabels(Set<String> labelSet) {
        return noteStorage.values().stream()
                .filter(note -> !Collections.disjoint(note.getLabels(), labelSet))
                .toList();
    }
}
