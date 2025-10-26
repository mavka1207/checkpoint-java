import java.util.*;

public class HTMLValidator {
    private static final Set<String> VALID_TAGS = Set.of(
            "html", "body", "div", "p", "b", "i", "h1", "h2", "br"
    );

    public boolean validateHTML(String html) {
        Stack<String> stack = new Stack<>();
        int i = 0;

        while (i < html.length()) {
            if (html.charAt(i) == '<') {
                int j = html.indexOf('>', i);
                if (j == -1) {
                    return false; // no closing >
                }

                String tag = html.substring(i + 1, j);

                if (tag.endsWith("/")) {
                    String tagName = tag.substring(0, tag.length() - 1);
                    if (!VALID_TAGS.contains(tagName)) return false;
                    // do nothing (don’t push onto stack)
                }
                // Closing tag
                else if (tag.startsWith("/")) {
                    String tagName = tag.substring(1);
                    if (!VALID_TAGS.contains(tagName)) return false;
                    if (stack.isEmpty() || !stack.peek().equals(tagName)) {
                        return false; // mismatch
                    }
                    stack.pop();
                }
                // Opening tag
                else {
                    if (!VALID_TAGS.contains(tag)) return false;
                    stack.push(tag);
                }
                i = j + 1;
            } else {
                i++;
            }
        }
        return stack.isEmpty();
    }
}
