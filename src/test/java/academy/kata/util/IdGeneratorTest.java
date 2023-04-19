package academy.kata.util;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IdGeneratorTest {
    @Test
    void getNextId() {
        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            assertTrue(ids.add(IdGenerator.getNextId()));
        }
    }
}