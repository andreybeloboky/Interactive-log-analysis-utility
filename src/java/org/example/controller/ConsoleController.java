package org.example.controller;

import org.example.model.Command;
import org.example.model.Level;
import org.example.exception.InvalidChoiceException;
import org.example.repository.LogStorageRepository;
import org.example.service.LogAnalyzerService;
import org.example.model.LogEntry;
import org.example.service.LogParserService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleController {

    public static final String INTRODUCTION = "Welcome to the Interactive Log Analyzer. Type 'HELP' for commands. 'EXIT' to leave.";
    public static final String LOG_ADD = "Enter log. Type 'END_ADD' to finish";
    public static final String ENTER = "Enter command";
    public static final String MESSAGE_ERROR = "Incorrect input. Please check your enter information";
    public static final String CORRECT_FORM_ERROR = "Your inter data is incorrect. Must be the next form. Example: [123] [ERROR] Example.";
    public static final String NOTHING_FOUND_ERROR = "The %s wasn't found";
    public static final String FOUND_LOGS = "Found %s log(s):";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LogStorageRepository logEntries = new LogStorageRepository();
        System.out.println(INTRODUCTION);
        String chooseOption;
        Command commandFind = null;
        do {
            System.out.println(ENTER);
            try {
                chooseOption = scanner.nextLine().trim();
                String[] arg = chooseOption.split(" ");
                commandFind = arg.length > 1 ? Command.valueOf(arg[0].toUpperCase()) : Command.valueOf(chooseOption.toUpperCase());
                switch (commandFind) {
                    case ADD:
                        printAdd(scanner, logEntries);
                        break;
                    case COUNT:
                        printCount(arg, logEntries);
                        break;
                    case SEARCH:
                        printSearch(arg, logEntries);
                        break;
                    case LATEST_ERROR:
                        printLastError(logEntries);
                        break;
                    case HELP:
                        printHelpInfo();
                        break;
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.err.println(MESSAGE_ERROR);
            }
        } while (commandFind != Command.EXIT);
    }

    private static void printAdd(Scanner scanner, LogStorageRepository logEntries) {
        int countLog = 0;
        String logLine;
        Command command = Command.END_ADD;
        do {
            System.out.println(LOG_ADD);
            logLine = scanner.nextLine();
            if (!command.name().equals(logLine.toUpperCase())) {
                try {
                    LogEntry logEntry = LogParserService.parseLine(logLine);
                    logEntries.addItem(logEntry);
                    countLog++;
                } catch (InvalidChoiceException e) {
                    System.err.println(CORRECT_FORM_ERROR);
                }
            }
        } while (!command.name().equals(logLine.toUpperCase()));
        System.out.println(countLog + " log(s) added");
    }

    private static void printCount(String[] arg, LogStorageRepository logEntries) {
        String transferToLevel = arg[1].substring(1, arg[1].length() - 1).toUpperCase();
        int countLevel = LogAnalyzerService.countLogsByLevel(logEntries.getLogEntries(), Level.valueOf(transferToLevel));
        if (countLevel > 0) {
            System.out.println("Total " + arg[1].toUpperCase() + " logs: " + countLevel);
        } else {
            System.err.printf(NOTHING_FOUND_ERROR, "level");
        }
    }

    private static void printSearch(String[] arg, LogStorageRepository logEntries) {
        ArrayList<LogEntry> keywordArray = LogAnalyzerService.findMessagesContaining(logEntries.getLogEntries(), arg[1]);
        if (!keywordArray.isEmpty()) {
            System.out.printf(FOUND_LOGS, keywordArray.size());
            for (LogEntry key : keywordArray) {
                System.out.println("[" + key.getTimestamp() + "]" + " " + key.getLevel() + " " + key.getMessage());
            }
        } else {
            System.err.printf(NOTHING_FOUND_ERROR, "keyword");
        }
    }

    private static void printLastError(LogStorageRepository logEntries) {
        Optional<LogEntry> findLatestError = LogAnalyzerService.findMostRecentError(logEntries.getLogEntries());
        if (findLatestError.isPresent()) {
            System.out.println(findLatestError.get().getLevel() + " " + findLatestError.get().getMessage());
        } else {
            System.err.printf(NOTHING_FOUND_ERROR, "[ERROR]");
        }
    }

    private static void printHelpInfo() {
        System.out.println("Available commands:");
        System.out.println("ADD - Enter log entry mode. Type 'END_ADD' to finish");
        System.out.println("COUNT <level> - Count logs by a specific level (e.g., COUNT ERROR)");
        System.out.println("SEARCH <keyword> - Find logs containing a keyword (e.g., SEARCH database");
        System.out.println("LATEST_ERROR - Find the latest [ERROR]");
        System.out.println("EXIT - Close the application");
    }
}