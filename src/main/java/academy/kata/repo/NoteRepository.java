package academy.kata.repo;

import academy.kata.model.Note;

import java.util.List;
import java.util.Set;

public interface NoteRepository {
    void save(Note note);
    List<Note> findAll();

    void deleteById(Long id);

    List<Note> findByLabels(Set<String> labelSet);
}
