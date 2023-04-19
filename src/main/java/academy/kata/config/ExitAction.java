package academy.kata.config;

import academy.kata.service.action.ActionAbstract;

public class ExitAction extends ActionAbstract {

    public ExitAction() {
        super("exit", "Завершение работы");
    }

    @Override
    public void handleRequest() {
        System.out.println("Завершение работы ...");
        Context.close();
    }
}
