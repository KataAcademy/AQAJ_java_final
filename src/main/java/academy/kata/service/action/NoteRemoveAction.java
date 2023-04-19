package academy.kata.service.action;

import academy.kata.config.InputHandler;
import academy.kata.exception.ConstraintValidationException;
import academy.kata.repo.NoteRepository;
import academy.kata.util.validation.NoteValidator;

public class NoteRemoveAction extends ActionAbstract {

    private final NoteRepository noteRepository;

    public NoteRemoveAction(NoteRepository noteRepository) {
        super("note-remove", "Удаление заметки");
        this.noteRepository = noteRepository;
    }

    @Override
    public void handleRequest() {
        System.out.println("Введите id удаляемой заметки");
        String id = InputHandler.nextLine().trim();
        try {
            NoteValidator.validateId(id);
            noteRepository.deleteById(Long.parseLong(id));
            System.out.println("Заметка удалена");
        } catch (ConstraintValidationException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
