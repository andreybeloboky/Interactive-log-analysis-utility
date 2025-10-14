import java.util.ArrayList;

public class Application {
    private ArrayList<LogEntry> logEntries = new ArrayList<>();

    public void addItem(LogEntry item) {
        logEntries.add(item);
    }

    public ArrayList<LogEntry> getLogEntries() {
        return logEntries;
    }

    public void removeItem(int index) {
        if (index >= 0 && index < logEntries.size()) {
            logEntries.remove(index);
        }
    }

    public void clearItems() {
        logEntries.clear();
    }
}
