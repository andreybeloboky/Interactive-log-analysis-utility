import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidChoiceException {
        Scanner scanner = new Scanner(System.in);
        Application logEntries = new Application();
        System.out.println("Welcome to the Interactive Log Analyzer. Type 'HELP' for commands. 'EXIT' to leave.");
        String command;
        String chooseOption;
        do {
            command = scanner.nextLine();
            try {
                validateCommand(command);
            } catch (InvalidChoiceException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (!command.toLowerCase().contains("exit") && !command.toLowerCase().contains("help"));
        if (command.equalsIgnoreCase("HELP")) {
            do {
                System.out.println("Available commands:");
                System.out.println("ADD - Enter log entry mode. Type 'END_ADD' to finish");
                System.out.println("COUNT <level> - Count logs by a specific level (e.g., COUNT ERROR)");
                System.out.println("SEARCH <keyword> - Find logs containing a keyword (e.g., SEARCH database");
                System.out.println("LATEST_ERROR - Find the latest [ERROR]");
                System.out.println("EXIT - Close the application");
                System.out.println("Enter command");
                int countLog = 0;
                chooseOption = scanner.nextLine();
                if (chooseOption.equalsIgnoreCase("ADD")) {
                    countLog = getCountLog(scanner, logEntries, countLog);
                    System.out.println(countLog + " log(s) added");
                } else if (chooseOption.toLowerCase().contains("count")) {
                    int countLevel = LogAnalyzer.countLogsByLevel(logEntries.getLogEntries(), chooseOption);
                    if (countLevel > 0) {
                        String lvl = LogAnalyzer.findLevel(chooseOption);
                        System.out.println("Total " + lvl.toUpperCase() + " logs: " + countLevel);
                    } else {
                        System.err.println("There is no data");
                    }
                } else if (chooseOption.toLowerCase().contains("search")) {
                    ArrayList<LogEntry> keywordArray = LogAnalyzer.findMessagesContaining(logEntries.getLogEntries(), chooseOption);
                    if (!keywordArray.isEmpty()) {
                        keywordArray = LogAnalyzer.findMessagesContaining(logEntries.getLogEntries(), chooseOption);
                        System.out.println("Found " + keywordArray.size() + " log(s):");
                        for (LogEntry key : keywordArray) {
                            System.out.println(key.getTimestamp() + " " + key.getLevel() + " " + key.getMessage());
                        }
                    } else {
                        System.err.println("This keyword wasn't found");
                    }
                } else if (chooseOption.toLowerCase().contains("latest_error")) {
                    LogEntry findLatestError = LogAnalyzer.findMostRecentError(logEntries.getLogEntries());
                    if (findLatestError != null) {
                        System.out.println(findLatestError.getLevel() + " " + findLatestError.getMessage());
                    } else {
                        System.err.println("The [ERROR] wasn't found");
                    }
                } else if (!chooseOption.toLowerCase().contains("exit")) {
                    System.err.println("Error");
                } else {
                    chooseOption = getString(scanner);
                }
            } while (!chooseOption.toLowerCase().contains("exit"));
        }
        System.out.println("Goodbye.");
    }

    private static int getCountLog(Scanner scanner, Application logEntries, int countLog) {
        String logLine;
        do {
            System.out.println("Enter log lines. Type 'END_ADD' to finish");
            logLine = scanner.nextLine();
            if (!logLine.toLowerCase().contains("end_add")) {
                LogEntry user = LogParser.parseLine(logLine);
                logEntries.addItem(user);
                countLog++;
            }
        } while (!logLine.equalsIgnoreCase("END_ADD"));
        return countLog;
    }

    private static String getString(Scanner scanner) {
        String chooseOption;
        do {
            System.out.println("Do you want to choose any options? If you want - write continue, if you don't want to end - EXIT");
            chooseOption = scanner.nextLine();
            try {
                validateChoice(chooseOption);
            } catch (InvalidChoiceException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while (!chooseOption.toLowerCase().contains("exit") && !chooseOption.toLowerCase().contains("continue"));
        return chooseOption;
    }

    public static void validateChoice(String chooseOption) throws InvalidChoiceException {
        if (!chooseOption.equalsIgnoreCase("EXIT") && !chooseOption.equalsIgnoreCase("continue")) {
            throw new InvalidChoiceException("Incorrect. Choose a correct option CONTINUE or EXIT");
        }
    }

    public static void validateCommand(String chooseOption) throws InvalidChoiceException {
        if (!chooseOption.equalsIgnoreCase("EXIT") && !chooseOption.equalsIgnoreCase("HELP")) {
            throw new InvalidChoiceException("Incorrect. Choose a correct option HELP or EXIT");
        }
    }
}