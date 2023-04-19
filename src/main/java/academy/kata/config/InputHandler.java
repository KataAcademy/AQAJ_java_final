package academy.kata.config;

import java.util.Scanner;

public final class InputHandler {
    private static Scanner scanner;
    public static String nextLine() {
        return getScanner().nextLine();
    }

    private static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
