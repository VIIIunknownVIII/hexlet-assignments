package exercise;

import java.util.Map;

public class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {
        String attrs = renderAttributes();
        return attrs.isEmpty()
                ? "<" + name + ">"
                : "<" + name + " " + attrs + ">";
    }
}
