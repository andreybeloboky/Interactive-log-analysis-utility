package org.example.repository;

import org.example.model.LogEntry;

import java.util.ArrayList;

public class LogStorageRepository {

    private final ArrayList<LogEntry> logEntries = new ArrayList<>();

    public void addItem(LogEntry item) {
        logEntries.add(item);
    }

    public ArrayList<LogEntry> getLogEntries() {
        return logEntries;
    }

}
