import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogLine {

  private String log;
  private LogLevel logLevel;

  private ArrayList<String> levels = new ArrayList<String>(
    Arrays.asList("TRC", "DBG", "INF", "WRN", "ERR", "FTL")
  );

  public LogLine(String logLine) {
    this.log = logLine;
  }

  public LogLevel getLogLevel() {
    //System.out.println("is a level: "+isALogLevel(this.log));
    if (!isALogLevel(log)) {
      return logLevel.UNKNOWN;
    } else {
      String shortLog = shortLevel(log);
      switch (shortLog) {
        case "TRC":
          return logLevel.TRACE;
        case "DBG":
          return logLevel.DEBUG;
        case "INF":
          return logLevel.INFO;
        case "WRN":
          return logLevel.WARNING;
        case "ERR":
          return logLevel.ERROR;
        case "FTL":
          return logLevel.FATAL;
        default:
          return logLevel.UNKNOWN;
      }
    }
    //return logLevel.UNKNOWN;
  }

  public String shortLevel(String log) {
    return log.substring(1, 4);
  }

  public boolean isALogLevel(String str) {
    String sub = str.substring(1, 4);
    //System.out.println("sub: "+sub);
    return levels.contains(sub);
  }

  public String getOutputForShortLog() {
    LogLevel lgl = getLogLevel();
    return lgl.getNumLevel() + ":" + (log.substring(7));
  }
}
