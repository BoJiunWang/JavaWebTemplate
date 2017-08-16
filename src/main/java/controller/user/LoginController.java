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

/**
 * Created by Ivan_Wang on 2017-07-01.
 */
@WebServlet(urlPatterns = {"/Login"})
public class LoginController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.sendRedirect("/");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    UserInfo userInfo;
    try {
      userInfo =
          SqlDao.getUserInfoByLogin(
              request.getParameter("userAccount"), request.getParameter("userPassword"));
    } catch (Exception e) {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }
    if (userInfo == null) {
      request.setAttribute("homeInfo", "帳號或密碼有誤");
      Logger.logInfo("帳號/密碼錯誤 IP", request.getRemoteAddr());
      request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    } else {
      userInfo.writeInfoToSession(session);
      response.sendRedirect("/");
    }
  }
}
