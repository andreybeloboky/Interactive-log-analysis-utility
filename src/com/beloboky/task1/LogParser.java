package com.beloboky.task1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    public static LogEntry parseLine(String logLine) {
        Pattern pattern = Pattern.compile("\\[(\\d+)] \\[(\\w+)] (.+)");
        Matcher matcher = pattern.matcher(logLine);
        LogEntry logEntry = new LogEntry();
        if (matcher.matches()) {
            long timestamp = Integer.parseInt(matcher.group(1));
            String level = "[" + matcher.group(2) + "]";
            String message = matcher.group(3);
            logEntry.setTimestamp(timestamp);
            logEntry.setLevel(level);
            logEntry.setMessage(message);
        } else {
            return null;
        }
        return logEntry;
    }

}