package academy.kata.service.action;

import academy.kata.config.InputHandler;
import academy.kata.exception.ConstraintValidationException;
import academy.kata.model.Note;
import academy.kata.repo.NoteRepository;
import academy.kata.util.converter.LabelConvertor;
import academy.kata.util.validation.NoteValidator;

import java.util.Set;

public class NoteCreateAction extends ActionAbstract {
    private final NoteRepository noteRepository;
    public NoteCreateAction(NoteRepository noteRepository) {
        super("note-new", "Создание новой заметки");
        this.noteRepository = noteRepository;
    }

    @Override
    public void handleRequest() {
        try {
            createNote();
            System.out.println("Заметка добавлена");
        } catch (ConstraintValidationException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void createNote() {
        String body = getBody();
        Set<String> labels = getLabels();
        noteRepository.save(new Note(body, labels));
    }

    private String getBody() {
        System.out.println("Введите заметку:");
        String body = InputHandler.nextLine();
        NoteValidator.validateNoteText(body);
        return body;
    }

    private Set<String> getLabels() {
        System.out.println("Добавить метки? Метки состоят из одного слова и могу содержать только буквы. Для добавления нескольких меток разделяйте слова пробелом.");
        String labels = InputHandler.nextLine().trim();
        NoteValidator.validateLabels(labels);
        return LabelConvertor.getLabelSetFromString(labels);
    }
}
