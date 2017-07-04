package components;

import java.io.Serializable;
import javax.servlet.http.HttpSession;

/**
 * Created by Ivan_Wang on 2017-07-01.
 */
public class UserInfo implements Serializable {

  private String userAccount;
  private String userName;
  private String userEmail;
  private boolean isAdmin = false;

  /**
   * Get user info from session.
   *
   * @param session HttpSession
   * @return userInfo
   */
  public static UserInfo fetchInfoFromSession(HttpSession session) {
    if (session.getAttribute("userInfo") == null) {
      return null;
    } else {
      UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
      return userInfo;
    }
  }

  public void writeInfoToSession(HttpSession session) {
    session.setAttribute("userInfo", this);
  }

  public void removeFromSession(HttpSession session) {
    session.removeAttribute("userInfo");
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public String getUserAccount() {
    return userAccount;
  }

  public void setUserAccount(String userAccount) {
    this.userAccount = userAccount;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
}