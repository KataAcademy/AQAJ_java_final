package academy.kata.util;

public final class IdGenerator {
    private static long currentId = 0;

    public static long getNextId() {
        return ++currentId;
    }
}
