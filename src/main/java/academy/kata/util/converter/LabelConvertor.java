package academy.kata.util.converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class LabelConvertor {
    public static Set<String> getLabelSetFromString(String labels) {
        return Arrays.stream(labels.split(" "))
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
    }
}
