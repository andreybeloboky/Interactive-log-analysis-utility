package com.beloboky.task1;

public class LogEntry {
    private final long timestamp;
    private final String level;
    private final String message;

    public LogEntry(long timestamp, String level, String message) {
        this.timestamp = timestamp;
        this.level = level;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }
}
