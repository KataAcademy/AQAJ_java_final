package academy.kata.service.action;

public interface Action {
    String getTrigger();

    String getDescription();

    void handleRequest();
}
