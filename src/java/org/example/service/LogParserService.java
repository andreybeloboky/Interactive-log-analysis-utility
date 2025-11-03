package org.example.service;

import org.example.model.Level;
import org.example.exception.InvalidChoiceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParserService {

    public static LogEntryService parseLine(String logLine) throws InvalidChoiceException {
        Pattern pattern = Pattern.compile("\\[(\\d+)] \\[(\\w+)] (.+)");
        Matcher matcher = pattern.matcher(logLine);
        LogEntryService logEntry;
        if (matcher.matches()) {
            Level level = Level.valueOf(matcher.group(2).toUpperCase());
            logEntry = new LogEntryService(Integer.parseInt(matcher.group(1)), level, matcher.group(3));
        } else {
            throw new InvalidChoiceException("Incorrect data");
        }
        return logEntry;
    }
}