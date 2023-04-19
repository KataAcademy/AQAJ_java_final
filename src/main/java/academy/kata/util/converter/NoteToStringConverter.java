package academy.kata.util.converter;

import academy.kata.model.Note;

import java.util.Collection;
import java.util.Iterator;

public final class NoteToStringConverter {
    public static String convert(Note note) {
        return "%d#%s\n%s".formatted(
                note.getId(),
                note.getText(),
                String.join(";", note.getLabels())
        );
    }

    public static String convert(Collection<Note> notes) {
        StringBuilder sbNotes = new StringBuilder();
        String noteSeparator = "\n\n==========================================\n\n";
        Iterator<Note> notesIterator = notes.iterator();
        while (notesIterator.hasNext()) {
            sbNotes.append(convert(notesIterator.next()));
            if (notesIterator.hasNext()) {
                sbNotes.append(noteSeparator);
            }
        }
        return sbNotes.toString();
    }
}
