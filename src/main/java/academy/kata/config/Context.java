package academy.kata.config;

import academy.kata.service.action.Action;
import academy.kata.service.action.NoteCreateAction;
import academy.kata.service.action.NoteExportAction;
import academy.kata.service.action.NoteRemoveAction;
import academy.kata.service.action.NoteSelectAction;
import academy.kata.exception.TriggerActionAmbiguityException;
import academy.kata.repo.impl.InMemoryNoteRepository;
import academy.kata.repo.NoteRepository;
import academy.kata.service.NoteExporter;
import academy.kata.service.impl.ToFileExporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;

public final class Context {
    private static Map<String, Action> actions;
    private static RequestHandler requestHandler;

    private static boolean isClosed = false;

    static void close() {
        isClosed = true;
    }

    static boolean isClosed() {
        return isClosed;
    }

    static Map<String, Action> getActions() {
        return actions;
    }

    public static void start() {
        requestHandler.run();
    }

    public static class ContextBuilder {
        public static void build() {
            requestHandler = new RequestHandler();
            actions = new HashMap<>();
            NoteRepository noteRepository = new InMemoryNoteRepository();
            NoteExporter noteExporter = new ToFileExporter();
            registerAction(new HelpAction(actions));
            registerAction(new NoteCreateAction(noteRepository));
            registerAction(new NoteSelectAction(noteRepository));
            registerAction(new NoteRemoveAction(noteRepository));
            registerAction(new NoteExportAction(noteRepository, noteExporter));
            registerAction(new ExitAction());
            setLogHandler();
        }

        public static void registerAction(Action action) {
            String actionTrigger = action.getTrigger().toLowerCase();
            if (actions.containsKey(actionTrigger)) {
                throw new TriggerActionAmbiguityException("Действие с триггером %s уже существует".formatted(actionTrigger));
            }
            actions.put(actionTrigger, action);
        }

        public static void setLogHandler() {
            try {
                LogManager.getLogManager().readConfiguration(ContextBuilder.class.getClassLoader().getResourceAsStream("log.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
