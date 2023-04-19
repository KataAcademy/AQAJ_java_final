package academy.kata.service.action;

import academy.kata.config.InputHandler;
import academy.kata.exception.ConstraintValidationException;
import academy.kata.model.Note;
import academy.kata.repo.NoteRepository;
import academy.kata.util.converter.LabelConvertor;
import academy.kata.util.converter.NoteToStringConverter;
import academy.kata.util.validation.NoteValidator;

import java.util.List;
import java.util.Set;

public class NoteSelectAction extends ActionAbstract {
    private final NoteRepository noteRepository;

    public NoteSelectAction(NoteRepository noteRepository) {
        super("note-list", "Вывод заметок на экран");
        this.noteRepository = noteRepository;
    }


    @Override
    public void handleRequest() {
        System.out.println("Введите метки, чтобы отобразить определенные заметки или оставьте пустым для отображения всех заметок");
        try {
            showNotes();
        } catch (ConstraintValidationException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void showNotes() {
        String labels = InputHandler.nextLine().trim();
        if (labels.isBlank()) {
            showAllNotes();
        } else {
            NoteValidator.validateLabels(labels);
            Set<String> labelSet = LabelConvertor.getLabelSetFromString(labels);
            showByLabels(labelSet);
        }
    }

    private void showByLabels(Set<String> labelSet) {
        List<Note> notes = noteRepository.findByLabels(labelSet);
        System.out.println(NoteToStringConverter.convert(notes));
    }

    private void showAllNotes() {
        List<Note> notes = noteRepository.findAll();
        System.out.println(NoteToStringConverter.convert(notes));
    }


}
