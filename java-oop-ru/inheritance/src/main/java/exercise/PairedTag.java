package exercise;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PairedTag extends Tag {
    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        String attrs = renderAttributes();
        String openingTag = attrs.isEmpty() ? "<" + name + ">" : "<" + name + " " + attrs + ">";
        String childrenRendered = children.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());

        return openingTag + body + childrenRendered + "</" + name + ">";
    }
}
