package academy.kata.service.action;

public abstract class ActionAbstract implements Action {
    private final String trigger;
    private final String description;

    protected ActionAbstract(String trigger, String description) {
        this.trigger = trigger;
        this.description = description;
    }

    @Override
    public String getTrigger() {
        return trigger;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
