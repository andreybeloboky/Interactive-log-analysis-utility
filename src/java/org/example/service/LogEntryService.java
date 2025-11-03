package org.example.service;

import org.example.model.Level;

public class LogEntryService {
    private final long timestamp;
    private final Level level;
    private final String message;

    public LogEntryService(long timestamp, Level level, String message) {
        this.timestamp = timestamp;
        this.level = level;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Level getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }
}
