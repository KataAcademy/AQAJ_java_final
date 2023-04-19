package academy.kata.service.impl;

import academy.kata.exception.NoteExportException;
import academy.kata.model.Note;
import academy.kata.service.NoteExporter;
import academy.kata.util.converter.NoteToStringConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;

public class ToFileExporter implements NoteExporter {
    @Override
    public String export(List<Note> notes) throws NoteExportException {
        Path targetFile = createPath();
        try {
            writeToFile(targetFile, NoteToStringConverter.convert(notes));
        } catch (IOException e) {
            throw new NoteExportException(e.getMessage(), e);
        }
        return targetFile.toAbsolutePath().toString();
    }

    private Path createPath() {
        LocalDateTime now = LocalDateTime.now();
        return Paths.get("notes_%tY.%tM.%td_%tH:%tM:%tS.txt".formatted(now, now, now, now, now, now));
    }

    private void writeToFile(Path path, String data) throws IOException {
        Files.write(
                path,
                data.getBytes(),
                StandardOpenOption.CREATE
        );
    }
}
