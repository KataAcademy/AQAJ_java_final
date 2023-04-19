package academy.kata.config;

import academy.kata.service.action.Action;
import academy.kata.service.action.ActionAbstract;

import java.util.Map;

public class HelpAction extends ActionAbstract {
    private final Map<String, Action> actionMap;

    public HelpAction(Map<String, Action> actionMap) {
        super("help", "Показать список доступных команд с описанием");
        this.actionMap = actionMap;
    }

    @Override
    public void handleRequest() {
        System.out.println("Список доступных команд:");
        actionMap.forEach((key, value) -> System.out.printf("%" + getMaxTriggerLength() + "s   -   %s%n", key, value.getDescription()));
    }

    private int getMaxTriggerLength() {
        return actionMap.keySet().stream().mapToInt(String::length).max().orElse(0);
    }
}
