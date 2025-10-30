import java.util.ArrayList;

public class LogAnalyzer {
    public static int countLogsByLevel(ArrayList<LogEntry> logs, String level) {
        int count = 0;
        for (LogEntry log : logs) {
            if (log.getLevel().equalsIgnoreCase(level)) {
                count++;
            }
        }
        return count;
    }

    public static ArrayList<LogEntry> findMessagesContaining(ArrayList<LogEntry> logs, String keyword) {
        ArrayList<LogEntry> keywordArray = new ArrayList<>();
        for (LogEntry log : logs) {
            if (log.getMessage().contains(keyword)) {
                keywordArray.add(log);
            }
        }
        return keywordArray;
    }

    public static LogEntry findMostRecentError(ArrayList<LogEntry> log) {
        int counterError;
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
