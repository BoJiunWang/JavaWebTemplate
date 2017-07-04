package dao;

import components.Logger;
import components.UserInfo;
import components.Util;
import config.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Ivan_Wang on 2017-07-01.
 */
public class SqlDao {

  /**
   * Verify user account and userPassword.
   *
   * @param userAccount User account
   * @param userPassword User userPassword
   * @return UserInfo
   */
  public static UserInfo getUserInfoByLogin(final String userAccount, final String userPassword)
      throws Exception {
    final String sql =
        "SELECT userAccount, userName, userEmail, adminPermission FROM account "
            + "WHERE userAccount = ? AND userPassword = ?;";
    UserInfo userInfo = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DatabaseConnection dbConnection = new DatabaseConnection();
    try {
      preparedStatement = dbConnection.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, userAccount);
      preparedStatement.setString(2, Util.hashBySha1(userPassword));
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        userInfo = new UserInfo();
        userInfo.setAdmin(resultSet.getBoolean("adminPermission"));
        userInfo.setUserAccount(resultSet.getString("userAccount"));
        userInfo.setUserName(resultSet.getString("userName"));
        userInfo.setUserEmail(resultSet.getString("userEmail"));
      }
    } catch (Exception e) {
      Logger.logWarn("getUserInfoByLogin", e.getMessage());
      throw e;
    } finally {
      closeResultSet(resultSet);
      closePreparedStatement(preparedStatement);
      dbConnection.closeConnection();
    }
    return userInfo;
  }

  /**
   * Update userName and userEmail via userAccount.
   *
   * @param userAccount validation
   * @param userName new user name
   * @param userEmail new user email
   */
  public static void setUserInfo(
      final String userAccount, final String userName, final String userEmail) throws Exception {
    final String sql = "UPDATE account SET userName = ?, userEmail = ? WHERE userAccount = ?;";
    PreparedStatement preparedStatement = null;
    DatabaseConnection dbConnection = new DatabaseConnection();
    try {
      preparedStatement = dbConnection.getConnection().prepareStatement(sql);
      preparedStatement.setString(1, userName);
      preparedStatement.setString(2, userEmail);
      preparedStatement.setString(3, userAccount);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      Logger.logWarn("setUserInfo", e.getMessage());
      throw e;
    } finally {
      closePreparedStatement(preparedStatement);
      dbConnection.closeConnection();
    }
  }

  private static void closeResultSet(ResultSet resultSet) {
    try {
      if (resultSet != null) {
        resultSet.close();
      }
    } catch (Exception e) {
      Logger.logWarn("closeResultSet", e.getMessage());
    }
  }

  private static void closePreparedStatement(PreparedStatement preparedStatement) {
    try {
      if (preparedStatement != null) {
        preparedStatement.close();
      }
    } catch (Exception e) {
      Logger.logWarn("closePreparedStatement", e.getMessage());
    }
  }
}
