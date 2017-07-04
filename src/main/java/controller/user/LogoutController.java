package controller.user;

import components.UserInfo;
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
@WebServlet(urlPatterns = {"/Logout"})
public class LogoutController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    UserInfo userInfo = UserInfo.fetchInfoFromSession(session);
    if (userInfo != null) {
      request.setAttribute("homeInfo", "Bye, " + userInfo.getUserName());
      userInfo.removeFromSession(session);
    } else {
      request.setAttribute("homeInfo", "您尚未登入");
    }
    request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.sendRedirect("/");
  }
}
