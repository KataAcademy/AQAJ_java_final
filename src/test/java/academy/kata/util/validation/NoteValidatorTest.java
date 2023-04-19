package academy.kata.util.validation;

import academy.kata.exception.ConstraintValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NoteValidatorTest {

    @Test
    void validateNoteText() {
        // test good data
        NoteValidator.validateNoteText("Новая успешная заметка");
        //test bad data
        assertThrows(ConstraintValidationException.class, () -> NoteValidator.validateNoteText("bad"));
    }

    @Test
    void validateLabels() {
        //test good data
        NoteValidator.validateLabels("one two three");
        NoteValidator.validateLabels("on");
        NoteValidator.validateLabels("o");
        NoteValidator.validateLabels("");

        //test bad data
        assertThrows(ConstraintValidationException.class, () -> NoteValidator.validateLabels("label1 label2"));
        assertThrows(ConstraintValidationException.class, () -> NoteValidator.validateLabels("one,two"));
        assertThrows(ConstraintValidationException.class, () -> NoteValidator.validateLabels("%one two"));
    }

    @Test
    void validateId() {
        //test good data
        NoteValidator.validateId("1234567890");
        NoteValidator.validateId("0");

        //test bad data
        assertThrows(ConstraintValidationException.class, () -> NoteValidator.validateId(""));
        assertThrows(ConstraintValidationException.class, () -> NoteValidator.validateId("one"));
        assertThrows(ConstraintValidationException.class, () -> NoteValidator.validateId("0.1"));
        assertThrows(ConstraintValidationException.class, () -> NoteValidator.validateId("0,1"));
        assertThrows(ConstraintValidationException.class, () -> NoteValidator.validateId("1 2"));

    }
}