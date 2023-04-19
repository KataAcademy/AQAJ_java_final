package academy.kata.config;

import academy.kata.service.action.Action;

import java.util.logging.Logger;

public class RequestHandler {
    private static final Logger LOGGER = Logger.getLogger(RequestHandler.class.getName());

    public void run() {
        showWelcomeMsg();
        while(!Context.isClosed()) {
            handleRequest();
        }
    }

    private void showWelcomeMsg() {
        System.out.println("Welcome");
        System.out.println("Доступные команды " + Context.getActions().keySet().stream().reduce((s1, s2) -> s1 + ", " + s2).orElse(""));
    }

    private void handleRequest() {
        String request = InputHandler.nextLine().trim();
        LOGGER.fine("Вызвана команда %s".formatted(request));
        Action action = Context.getActions().get(request);
        if (action != null) {
            action.handleRequest();
        } else {
            LOGGER.warning("Неизвестная команда %s".formatted(request));
            System.out.printf("Неизвестная команда %s%n", request);
        }
    }
}
