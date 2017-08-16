package controller.user;

import components.Logger;
import components.UserInfo;
import dao.SqlDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.ESAPI;

/**
 * Created by Ivan_Wang on 2017-07-01.
 */
@WebServlet(urlPatterns = {"/Profile"})
public class ProfileController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    UserInfo userInfo = UserInfo.fetchInfoFromSession(session);
    if (userInfo == null) {
      Logger.logInfo("Unauthorized attempt to view the profile page IP", request.getRemoteAddr());
      response.sendRedirect("/");
    } else {
      request.setAttribute("homeInfo", "Developing...");
      request.setAttribute("userInfo", userInfo);
      request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    UserInfo userInfo = UserInfo.fetchInfoFromSession(session);
    String userName = ESAPI.encoder().canonicalize(request.getParameter("userName"));
    String userEmail = ESAPI.encoder().canonicalize(request.getParameter("userEmail"));
    try {
      if (userInfo != null) {
        SqlDao.setUserInfo(userInfo.getUserAccount(), userName, userEmail);
        userInfo.setUserName(userName);
        userInfo.setUserEmail(userEmail);
        userInfo.writeInfoToSession(session);
        response.getWriter().write("Update Successful");
      }
    } catch (Exception e) {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}
