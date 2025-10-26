import java.util.List;

public class ConfigProtector {
    public String hideSensitiveData(String configFile, List<String> sensitiveKeys) {
        StringBuilder result = new StringBuilder();

        // Split config into lines
        String[] lines = configFile.split("\n");
        for (String line : lines) {
            if (configFile.isEmpty()) {
                return "";
            }

            String[] parts = line.split("=", 2); // split only at the first '='
            String key = parts[0];
            String value = parts[1];

            if (sensitiveKeys.contains(key)) {
                // Replace value with same number of asterisks
                value = "*".repeat(value.length());
            }

            result.append(key).append("=").append(value).append("\n");
        }

        return result.toString();
    }
}
