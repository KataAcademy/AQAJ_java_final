package academy.kata.util.validation;

import academy.kata.exception.ConstraintValidationException;

import java.util.logging.Logger;
import java.util.regex.Pattern;

public final class NoteValidator {
    private static final Logger LOGGER = Logger.getLogger(NoteValidator.class.getName());
    private static final Pattern labelPattern = Pattern.compile("[^a-zA-Zа-яА-Я ]");
    private static final Pattern idPattern = Pattern.compile("[^0-9]");
    public static void validateNoteText(String text) {
        if (text == null || text.length() < 5) {
            LOGGER.info("Текст заметки короче минимальной длины в 5 символов - %s".formatted(text));
            throw new ConstraintValidationException("Текст заметки должен быть не менее 5 символов");
        }
    }

    public static void validateLabels(String labels) {
        if (labelPattern.matcher(labels).find()) {
            LOGGER.info("Некорректные символы в метке - %s".formatted(labels));
            throw new ConstraintValidationException("Метки должны состоять только из букв");
        }
    }

    public static void validateId(String id) {
        if (id.isBlank() || idPattern.matcher(id).find()) {
            LOGGER.info("Некорректный формат id - %s".formatted(id));
            throw new ConstraintValidationException("Некорректный id");
        }
    }
}
