package org.example.service;

import org.example.model.Level;

import java.util.ArrayList;
import java.util.Optional;

public class LogAnalyzerService {

    public static int countLogsByLevel(ArrayList<LogEntryService> logs, Level level) {
        int count = 0;
        for (LogEntryService log : logs) {
            if (log.getLevel() == level) {
                count++;
            }
        }
        return count;
    }

    public static ArrayList<LogEntryService> findMessagesContaining(ArrayList<LogEntryService> logs, String keyword) {
        ArrayList<LogEntryService> keywordArray = new ArrayList<>();
        for (LogEntryService log : logs) {
            if (log.getMessage().toLowerCase().contains(keyword.toLowerCase())) {
                keywordArray.add(log);
            }
        }
        return keywordArray;
    }

    public static Optional<LogEntryService> findMostRecentError(ArrayList<LogEntryService> log) {
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
