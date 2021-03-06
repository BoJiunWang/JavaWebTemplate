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
@WebServlet(urlPatterns = {"/Info/Link"})
public class LinkController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setAttribute("homeInfo", "這裡必須登入才可以看到!");
    request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
  }
}
