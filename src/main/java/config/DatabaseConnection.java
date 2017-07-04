package config;

import components.Logger;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by Ivan_Wang on 2017-07-01.
 */
public class DatabaseConnection {

  private static final String CONFIGURATION_FILE = "../db.properties";
  private static final String JDBC_NAME = "com.mysql.jdbc.Driver";
  private static Connection connection = null;
  private String host = "";
  private String port = "";
  private String userName = "";
  private String userPassword = "";
  private String dbName = "";

  private void initial() {
    Properties properties = new Properties();
    InputStream fs = null;
    try {
      fs = getClass().getResourceAsStream(CONFIGURATION_FILE);
      properties.load(fs);
      host = properties.getProperty("DB_HOST");
      port = properties.getProperty("DB_PORT");
      userName = properties.getProperty("DB_USER");
      userPassword = properties.getProperty("DB_PASS");
      dbName = properties.getProperty("DB_NAME");
      Logger.logDebug(
          "get DB properties",
          host + ", " + port + ", " + userName + ", " + userPassword + ", " + dbName);
    } catch (Exception e) {
      Logger.logWarn("Exception occurs at DataBaseConnection.initial()", e.getMessage());
    } finally {
      if (fs != null) {
        try {
          fs.close();
        } catch (Exception e) {
          Logger.logWarn("InputStream fail closed at DataBaseConnection.initial()", e.getMessage());
        }
      }
    }
  }

  private void buildConnection() throws Exception {
    initial();
    try {
      if (connection != null) {
        connection.close();
      }
      Class.forName(JDBC_NAME);
      String url =
          "jdbc:mysql://"
              + this.host
              + ":"
              + this.port
              + "/"
              + this.dbName
              + "?useSSL=false&useUnicode=true&characterEncoding=utf-8";
      Logger.logDebug("buildConnection connect to: ", url);
      connection = DriverManager.getConnection(url, this.userName, this.userPassword);
    } catch (Exception e) {
      Logger.logWarn("Exception occurs at DataBaseConnection.buildConnection()", e.getMessage());
      throw e;
    }
  }

  /**
   * Get Connection.
   *
   * @return Connection
   * @throws Exception When get connection error
   */
  public Connection getConnection() throws Exception {
    if (connection == null) {
      try {
        buildConnection();
      } catch (Exception e) {
        Logger.logWarn("Exception occurs at DataBaseConnection.getConnection()", e.getMessage());
        throw e;
      }
    }
    return connection;
  }

  /**
   * Close connection.
   */
  public void closeConnection() {
    try {
      if (connection != null) {
        connection.close();
        connection = null;
      }
      Logger.logDebug("closeConnection", "Disconnect...");
    } catch (Exception e) {
      Logger.logWarn("Exception occurs at DataBaseConnection.closeConnection()", e.getMessage());
    }
  }
}
