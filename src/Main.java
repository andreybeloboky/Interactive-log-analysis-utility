import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Application items = new Application();
        System.out.println("Welcome to the Interactive Log Analyzer. Type 'HELP' for commands");
        String command = scanner.nextLine();
        String chooseOption;
        if (command.equalsIgnoreCase("HELP")) {
            do {
                System.out.println("Available commands:");
                System.out.println("ADD - Enter log entry mode. Type 'END_ADD' to finish");
                System.out.println("COUNT <level> - Count logs by a specific level (e.g., COUNT ERROR)");
                System.out.println("SEARCH <keyword> - Find logs containing a keyword (e.g., SEARCH database");
                System.out.println("LATEST_ERROR - Find the latest [ERROR]");
                System.out.println("EXIT - Close the application");
                System.out.println("Enter command");
                chooseOption = scanner.nextLine();
                String logLine;
                int countLog = 0;
                if (chooseOption.equalsIgnoreCase("ADD")) {
                    do {
                        System.out.println("Enter log lines. Type 'END_ADD' to finish");
                        logLine = scanner.nextLine();
                        if (!logLine.toLowerCase().contains("end_add")) {
                            LogEntry user = LogParser.parseLine(logLine);
                            items.addItem(user);
                            countLog++;
                        }
                    } while (!logLine.equalsIgnoreCase("END_ADD"));
                    System.out.println(countLog + " log(s) added");
                } else if (chooseOption.toLowerCase().contains("count")) {
                    int countLevel = 0;
                    try {
                        countLevel = LogAnalyzer.countLogsByLevel(items.getItems(), chooseOption);
                    } catch (NullPointerException e) {
                        System.err.println("Counter is null");
                    }
                    String lvl = "[" + LogAnalyzer.findLevel(chooseOption) + "]";
                    System.out.println("Total " + lvl.toUpperCase() + " logs: " + countLevel);
                } else if (chooseOption.toLowerCase().contains("search")) {
                    ArrayList<LogEntry> keywordArray = new ArrayList<>();
                    try {
                        keywordArray = LogAnalyzer.findMessagesContaining(items.getItems(), chooseOption);
                    } catch (NullPointerException e) {
                        System.err.println("This keyword wasn't found");
                    }
                    System.out.println("Found " + keywordArray.size() + " log(s):");
                    for (LogEntry key : keywordArray) {
                        System.out.println(key.getTimestamp() + " " + key.getLevel() + " " + key.getMessage());
                    }
                } else if (chooseOption.toLowerCase().contains("latest_error")) {
                    LogEntry findLatestError = LogAnalyzer.findMostRecentError(items.getItems());
                    System.out.println(findLatestError.getLevel() + " " + findLatestError.getMessage());
                }
                if (!chooseOption.toLowerCase().contains("exit")) {
                    do {
                        System.out.println("Do you want to choose any options? If you want - write continue, if you don't want to end - EXIT");
                        chooseOption = scanner.nextLine();
                        try {
                            validateChoice(chooseOption);
                        } catch (InvalidChoiceException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    } while (!chooseOption.toLowerCase().contains("exit") && !chooseOption.toLowerCase().contains("continue"));
                }
            } while (!chooseOption.toLowerCase().contains("exit"));
        } else {
            System.out.println("Goodbye");
        }
    }

    public static void validateChoice(String chooseOption) throws InvalidChoiceException {
        if (!chooseOption.equalsIgnoreCase("EXIT") && !chooseOption.equalsIgnoreCase("continue")) {
            throw new InvalidChoiceException("Incorrect. Choose a correct option");
        }
    }
}