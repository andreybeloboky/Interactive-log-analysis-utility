import java.util.ArrayList;

public class Application {
    private ArrayList<LogEntry> items = new ArrayList<>();

    public void addItem(LogEntry item) {
        items.add(item);
    }

    public ArrayList<LogEntry> getItems() {
        return items;
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public void clearItems() {
        items.clear();
    }
}
