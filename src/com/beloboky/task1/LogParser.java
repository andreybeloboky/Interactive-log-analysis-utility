package com.beloboky.task1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    public static LogEntry parseLine(String logLine) throws InvalidChoiceException {
        Pattern pattern = Pattern.compile("\\[(\\d+)] \\[(\\w+)] (.+)");
        Matcher matcher = pattern.matcher(logLine);
        LogEntry logEntry;
        if (matcher.matches()) {

            logEntry = new LogEntry(Integer.parseInt(matcher.group(1)), "[" + matcher.group(2) + "]", matcher.group(3));
        } else {
            throw new InvalidChoiceException("Incorrect data");
        }
        return logEntry;
    }
}