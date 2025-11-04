package org.example.model;

public class LogEntry {

    private final long timestamp;
    private final Level level;
    private final String message;

    public LogEntry(long timestamp, Level level, String message) {
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
