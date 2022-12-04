package exercise;

import java.util.Map;

// BEGIN
public class Tag {

    private String name;
    private Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder bs = new StringBuilder();

        sb.append("<").append(name);

        if (attributes.size() != 0) {
            for (Map.Entry<String, String> entry : attributes.entrySet()) {
                bs.append(entry.getKey()).append("=").append("\"").append(entry.getValue()).append("\"").append(" ");
            }
            sb.append(" ").append(bs.toString().trim());
        }

        return sb.append(">").toString();
    }
}
// END
