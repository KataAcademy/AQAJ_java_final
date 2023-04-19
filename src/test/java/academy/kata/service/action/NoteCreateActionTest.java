package academy.kata.service.action;

import academy.kata.model.Note;
import academy.kata.repo.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class NoteCreateActionTest {
    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteCreateAction noteCreateAction;


    @Test
    void createNote_success() {
        String inputData = """
                Now good note
                one two three
                
                bad
                one two three
                
                Bad labels
                one two 3
                """;

        setSystemIn(inputData);
        //note success created
        noteCreateAction.handleRequest();
        verify(noteRepository).save(new Note("Now good note", Set.of("ONE", "TWO", "THREE")));

        //test bad text
        reset(noteRepository);
        noteCreateAction.handleRequest();
        verify(noteRepository, never()).save(any());

        //test bad labels
        reset(noteRepository);
        noteCreateAction.handleRequest();
        verify(noteRepository, never()).save(any());
    }

    private void setSystemIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}