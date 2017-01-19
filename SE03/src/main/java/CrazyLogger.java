import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

/**
 * Created by Air on 19/01/2017.
 */
public class CrazyLogger {

    private StringBuilder log;
    private SimpleDateFormat formatter;
    private String pattern = "dd-M-YYYY ':' hh-mm";

    public CrazyLogger() {
        log = new StringBuilder();
        formatter = new SimpleDateFormat(pattern);
    }

    public void addMessage(String message) {
        addMessage(new Date(), message);
    }

    public void addMessage(Date date, String message) {
        log.append(formatter.format(date)).append(" - ").append(message).append("\n");
    }

    public void printLog() {
        System.out.println(log.toString());
    }

    public String find(String text) {
        int pos = 0;
        StringBuilder result = new StringBuilder("Search results:\n");
        while (true) {
            pos = log.indexOf(text, pos);
            if (pos >= 0) {
                int start = log.lastIndexOf("\n", pos);
                int stop = log.indexOf("\n", pos);
                result.append(log.substring(start < 0 ? 0 : start, stop > log.length() ? log.length() : stop).trim())
                        .append("\n");
                pos++;
            } else {
                return result.toString();
            }
        }
    }
}
