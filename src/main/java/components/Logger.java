package components;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * Created by Ivan_Wang on 2017-07-02.
 */
public class Logger {

  private static org.apache.logging.log4j.Logger logger = null;

  private static org.apache.logging.log4j.Logger getInstance() {
    if (logger == null) {
      logger = LogManager.getLogger();
    }
    return logger;
  }

  public static void logInfo(final String notes, final String message) {
    getInstance().log(Level.INFO, notes + ": " + message);
  }

  public static void logWarn(final String notes, final String message) {
    getInstance().log(Level.WARN, notes + ": " + message);
  }

  public static void logDebug(final String notes, final String message) {
    getInstance().log(Level.DEBUG, notes + ": " + message);
  }
}
