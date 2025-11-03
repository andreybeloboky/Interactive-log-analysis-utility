package org.example.controller;

import org.example.model.Command;
import org.example.model.Level;
import org.example.exception.InvalidChoiceException;
import org.example.repository.LogStorageRepository;
import org.example.service.LogAnalyzerService;
import org.example.service.LogEntryService;
import org.example.service.LogParserService;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleController {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LogStorageRepository logEntries = new LogStorageRepository();
        System.out.println("Welcome to the Interactive Log Analyzer. Type 'HELP' for commands. 'EXIT' to leave.");
        String chooseOption = "";
        do {
            System.out.println("Enter command");
            int countLog;
            try {
                chooseOption = scanner.nextLine().trim();
                String[] arg = chooseOption.split(" ");
                Command commandFind;
                if (arg.length > 1) {
                    commandFind = Command.valueOf(arg[0].toUpperCase());
                } else {
                    commandFind = Command.valueOf(chooseOption.toUpperCase());
                }
                switch (commandFind) {
                    case ADD:
                        countLog = addLog(scanner, logEntries);
                        System.out.println(countLog + " log(s) added");
                        break;
                    case COUNT:
                        String transferToLevel = arg[1].substring(1, arg[1].length() - 1).toUpperCase();
                        int countLevel = LogAnalyzerService.countLogsByLevel(logEntries.getLogEntries(), Level.valueOf(transferToLevel));
                        if (countLevel > 0) {
                            System.out.println("Total " + arg[1].toUpperCase() + " logs: " + countLevel);
                        } else {
                            System.err.println("There is no data");
                        }
                        break;
                    case SEARCH:
                        ArrayList<LogEntryService> keywordArray = LogAnalyzerService.findMessagesContaining(logEntries.getLogEntries(), arg[1]);
                        if (!keywordArray.isEmpty()) {
                            System.out.println("Found " + keywordArray.size() + " log(s):");
                            for (LogEntryService key : keywordArray) {
                                System.out.println("[" + key.getTimestamp() + "]" + " " + key.getLevel() + " " + key.getMessage());
                            }
                        } else {
                            System.err.println("This keyword wasn't found");
                        }
                        break;
                    case LATEST_ERROR:
                        LogEntryService findLatestError = LogAnalyzerService.findMostRecentError(logEntries.getLogEntries());
                        if (findLatestError != null) {
                            System.out.println(findLatestError.getLevel() + " " + findLatestError.getMessage());
                        } else {
                            System.err.println("The [ERROR] wasn't found");
                        }
                        break;
                    case HELP:
                        System.out.println("Available commands:");
                        System.out.println("ADD - Enter log entry mode. Type 'END_ADD' to finish");
                        System.out.println("COUNT <level> - Count logs by a specific level (e.g., COUNT ERROR)");
                        System.out.println("SEARCH <keyword> - Find logs containing a keyword (e.g., SEARCH database");
                        System.out.println("LATEST_ERROR - Find the latest [ERROR]");
                        System.out.println("EXIT - Close the application");
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Incorrect input. Please check your enter information");
            }
        } while (!chooseOption.toLowerCase().contains("exit"));
    }

    private static int addLog(Scanner scanner, LogStorageRepository logEntries) {
        String logLine;
        int countLog = 0;
        do {
            System.out.println("Enter log lines. Type 'END_ADD' to finish");
            logLine = scanner.nextLine();
            if (!logLine.toLowerCase().contains("end_add")) {
                try {
                    LogEntryService logEntry = LogParserService.parseLine(logLine);
                    logEntries.addItem(logEntry);
                    countLog++;
                } catch (InvalidChoiceException e) {
                    System.err.println("Your inter data is incorrect. Must be the next form [123] [ERROR] Example.");
                }
            }
        } while (!logLine.equalsIgnoreCase("END_ADD"));
        return countLog;
    }
}