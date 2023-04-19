package academy.kata.service.action;

import academy.kata.exception.NoteExportException;
import academy.kata.model.Note;
import academy.kata.repo.NoteRepository;
import academy.kata.service.NoteExporter;

import java.util.List;

public class NoteExportAction extends ActionAbstract {
    private final NoteRepository noteRepository;
    private final NoteExporter noteExporter;

    public NoteExportAction(NoteRepository noteRepository, NoteExporter noteExporter) {
        super("note-export", "Экспорт заметок в тестовый файл");
        this.noteRepository = noteRepository;
        this.noteExporter = noteExporter;
    }

    @Override
    public void handleRequest() {
        System.out.println("Экспортируем заметки");
        List<Note> notes = noteRepository.findAll();
        try {
            String resourceAddress = noteExporter.export(notes);
            System.out.println(resourceAddress);
        } catch (NoteExportException e) {
            System.err.println(e.getMessage());
        }
    }
}
