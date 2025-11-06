package org.example.service;

import org.example.model.Level;
import org.example.exception.InvalidChoiceException;
import org.example.model.LogEntry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParserService {

    public static LogEntry parseLine(String logLine) throws InvalidChoiceException {
        Pattern pattern = Pattern.compile("\\[(\\d+)] \\[(\\w+)] (.+)");
        Matcher matcher = pattern.matcher(logLine);
        LogEntry logEntry;
        if (matcher.matches()) {
            Level level = Level.valueOf(matcher.group(2).toUpperCase());
            logEntry = new LogEntry(Integer.parseInt(matcher.group(1)), level, matcher.group(3));
            return logEntry;
        } else {
            throw new InvalidChoiceException("Incorrect , provided data %s".formatted(logLine));
        }
    }
}