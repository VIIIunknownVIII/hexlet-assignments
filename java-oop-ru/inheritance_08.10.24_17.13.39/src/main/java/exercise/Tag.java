package exercise;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class Tag {
    protected String name;
    protected Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    // Метод для форматирования атрибутов тега в строку
    protected String renderAttributes() {
        return attributes.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(" "));
    }

    // Каждый наследник должен реализовать этот метод
    @Override
    public abstract String toString();
}
