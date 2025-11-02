package com.beloboky.task1;

import java.util.ArrayList;

public class LogStorage {

    private final ArrayList<LogEntry> logEntries = new ArrayList<>();

    public void addItem(LogEntry item) {
        logEntries.add(item);
    }

    public ArrayList<LogEntry> getLogEntries() {
        return logEntries;
    }

}
