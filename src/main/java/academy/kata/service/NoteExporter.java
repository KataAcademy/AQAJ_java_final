package academy.kata.service;

import academy.kata.exception.NoteExportException;
import academy.kata.model.Note;

import java.util.List;

public interface NoteExporter {
    String export(List<Note> notes) throws NoteExportException;
}
