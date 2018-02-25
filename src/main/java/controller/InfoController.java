package controller;

import components.UserInfo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Ivan_Wang on 2018-02-03.
 */
@WebServlet(urlPatterns = {"/Info"})
public class InfoController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    UserInfo userInfo = UserInfo.fetchInfoFromSession(session);
    if (userInfo != null) {
      request.setAttribute("homeInfo",
          "Hi, " + userInfo.getUserName() + " 這裡的資訊不需要登入就可以看.");
    } else {
      request.setAttribute("homeInfo", "這裡的資訊不需要登入就可以看.<br>帳密預設為admin.");
    }
    request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
  }
}
