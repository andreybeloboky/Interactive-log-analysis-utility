package org.example.service;

import org.example.model.Level;
import org.example.model.LogEntry;

import java.util.ArrayList;
import java.util.Optional;

public class LogAnalyzerService {

    public static int countLogsByLevel(ArrayList<LogEntry> logs, Level level) {
        int count = 0;
        for (LogEntry log : logs) {
            if (log.getLevel() == level) {
                count++;
            }
        }
        return count;
    }

    public static ArrayList<LogEntry> findMessagesContaining(ArrayList<LogEntry> logs, String keyword) {
        ArrayList<LogEntry> keywordArray = new ArrayList<>();
        for (LogEntry log : logs) {
            if (log.getMessage().toLowerCase().contains(keyword.toLowerCase())) {
                keywordArray.add(log);
            }
        }
        return keywordArray;
    }

    public static Optional<LogEntry> findMostRecentError(ArrayList<LogEntry> log) {
        int counterError;
        Level level = Level.ERROR;
        for (int i = log.size() - 1; i >= 0; i--) {
            if (log.get(i).getLevel().equals(level)) {
                counterError = i;
                return Optional.of(log.get(counterError));
            }
        }
        return Optional.empty();
    }
}
