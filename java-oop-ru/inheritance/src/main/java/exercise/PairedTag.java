package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag {
    private String body;
    private List<Tag> tags;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> tags) {
        super(name, attributes);
        this.body = body;
        this.tags = tags;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder bs = new StringBuilder();

        sb.append(super.toString());
        if (tags.size() != 0) {
            for (Tag tag : tags) {
                bs.append(tag.toString());
            }

            sb.append(bs.toString().trim());
        } else {
            sb.append(body);
        }
        return sb.append("</").append(super.getName()).append(">").toString();
    }
}
// END
