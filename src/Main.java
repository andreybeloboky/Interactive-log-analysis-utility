import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Application items = new Application();
        System.out.println("Welcome to the Interactive Log Analyzer. Type 'HELP' for commands");
        String command = scanner.nextLine();
        int counter;
        if (command.equalsIgnoreCase("HELP")) {
            do {
                System.out.println("Available commands:");
                System.out.println("ADD - Enter log entry mode. Type 'END_ADD' to finish");
                System.out.println("COUNT <level> - Count logs by a specific level (e.g., COUNT ERROR)");
                System.out.println("SEARCH <keyword> - Find logs containing a keyword (e.g., SEARCH database");
                System.out.println("EXIT - Close the application");
                System.out.println("Enter command");
                String chooseOption = scanner.nextLine();
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
                    int countLevel = LogAnalyzer.countLogsByLevel(items.getItems(), chooseOption);
                    String lvl = "[" + LogAnalyzer.findLevel(chooseOption) + "]";
                    System.out.println("Total " + lvl.toUpperCase() + " logs: " + countLevel);
                } else if (chooseOption.toLowerCase().contains("search")) {
                    ArrayList<LogEntry> keywordArray = LogAnalyzer.findMessagesContaining(items.getItems(), chooseOption);
                    System.out.println("Found " + keywordArray.size() + " log(s):");
                    for (LogEntry key : keywordArray) {
                        System.out.println(key.getTimestamp() + " " + key.getLevel() + " " + key.getMessage());
                    }
                } else {
                    System.out.println("Incorrect input");
                }
                System.out.println("Do you want to choose any options? Yes - tap 1, no - end of the work tap 2");
                counter = scanner.nextInt();
                String out = scanner.nextLine();
            }
            while (counter == 1);
        } else {
            System.out.println("Goodbye");
        }
    }
}