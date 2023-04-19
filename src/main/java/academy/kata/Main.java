package academy.kata;

import academy.kata.config.Context;

public class Main {
    public static void main(String[] args) {
        Context.ContextBuilder.build();
        Context.start();
    }
}