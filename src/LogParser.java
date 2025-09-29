import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    public static LogEntry parseLine (String logLine){
        Pattern pattern = Pattern.compile("\\[(\\d+)] \\[(\\w+)] (.+)");
        Matcher matcher = pattern.matcher(logLine);
        LogEntry user = new LogEntry();
        if (matcher.matches()){
            int timestamp = Integer.parseInt(matcher.group(1));
            String level = "["+matcher.group(2)+"]";
            String message = matcher.group(3);
            user.setTimestamp(timestamp);
            user.setLevel(level);
            user.setMessage(message);
        }else{
            return null;
        }
     return user;
    }
}
