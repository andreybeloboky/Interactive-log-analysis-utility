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
       /* Pattern pattern = Pattern.compile("admin");
        Matcher matcher = pattern.matcher(keyword);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
            return matcher.group(1);
        } */
        String kek = keyword.replaceAll("\\bsearch\\b\\s*", "");
        return kek;
    }
}
