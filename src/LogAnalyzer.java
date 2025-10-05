import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogAnalyzer {
    public static int countLogsByLevel(ArrayList<LogEntry> logs, String level) {
        String lvl = "[" + findLevel(level) + "]";
        int count = 0;
        for (LogEntry log : logs) {
            if (log.getLevel().equalsIgnoreCase(lvl)) {
                count++;
            }
        }
        return count;
    }

    public static ArrayList<LogEntry> findMessagesContaining(ArrayList<LogEntry> logs, String keyword) {
        ArrayList<LogEntry> keywordArray = new ArrayList<>();
        String findKey = findKeyword(keyword.toLowerCase());
        for (LogEntry log : logs) {
            if (log.getMessage().contains(findKey)) {
                keywordArray.add(log);
            }
        }
        return keywordArray;
    }

    public static String findLevel(String level) {
        Pattern pattern = Pattern.compile("\\[(.*?)]");
        Matcher matcher = pattern.matcher(level);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static String findKeyword(String keyword) {
        String replace = keyword.replaceAll("\\bsearch\\b\\s*", "");
        return replace;
    }

    public static LogEntry findMostRecentError(ArrayList<LogEntry> log) {
        ArrayList<Integer> counterError = new ArrayList<>();
        String indicator = "[Error]";
        for (int i = 0; i < log.size(); i++) {
            if (log.get(i).getLevel().equalsIgnoreCase(indicator)) {
                counterError.add(i);
            }
        }
        String level = log.get(counterError.getLast()).getLevel();
        String message = log.get(counterError.getLast()).getMessage();
        LogEntry lastError = new LogEntry();
        lastError.setLevel(level);
        lastError.setMessage(message);
        return lastError;
    }
}
