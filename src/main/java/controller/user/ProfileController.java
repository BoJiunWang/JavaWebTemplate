package controller.user;

import static components.Util.encodeForHtml;

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
@WebServlet(urlPatterns = {"/Profile"})
public class ProfileController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    HttpSession session = request.getSession(true);
    UserInfo userInfo = UserInfo.fetchInfoFromSession(session);
    String userName = encodeForHtml(request.getParameter("userName"));
    String userEmail = encodeForHtml(request.getParameter("userEmail"));
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
