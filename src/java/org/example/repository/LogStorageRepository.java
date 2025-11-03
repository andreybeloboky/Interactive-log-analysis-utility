package org.example.repository;

import org.example.service.LogEntryService;

import java.util.ArrayList;

public class LogStorageRepository {

    private final ArrayList<LogEntryService> logEntries = new ArrayList<>();

    public void addItem(LogEntryService item) {
        logEntries.add(item);
    }

    public ArrayList<LogEntryService> getLogEntries() {
        return logEntries;
    }

}
