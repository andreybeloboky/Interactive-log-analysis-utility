import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogAnalyzer {
    public static int countLogsByLevel(ArrayList<LogEntry> logs, String level) {
        String lvl = findLevel(level);
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
        //    String findKey = findKeyword(keyword.toLowerCase());
        for (LogEntry log : logs) {
            if (log.getMessage().contains(keyword)) {
                keywordArray.add(log);
            }
        }
        return keywordArray;
    }

    public static String findLevel(String level) {
        Pattern pattern = Pattern.compile("\\[(.*?)]");
        Matcher matcher = pattern.matcher(level);
        if (matcher.find()) {
            return "[" + matcher.group(1) + "]";
        }
        return null;
    }


   /* public static String findKeyword(String keyword) {
        String replace = keyword.replaceAll("\\bsearch\\b\\s*", "");
        return replace;
    }

    */

    public static LogEntry findMostRecentError(ArrayList<LogEntry> log) {
        Integer counterError;
        String indicator = "[Error]";
        int i = log.size() - 1;
        while (i >= 0) {
            if (log.get(i).getLevel().equalsIgnoreCase(indicator)) {
                counterError = i;
                return log.get(counterError);
            } else {
                i--;
            }
        }
        return null;
    }
}
