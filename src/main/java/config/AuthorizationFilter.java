package config;

import components.UserInfo;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Ivan_Wang on 2017-07-02.
 */
@WebFilter(
    urlPatterns = {"/Profile", "/Info/Link"},
    filterName = "checkAuthorization"
)
public class AuthorizationFilter implements Filter {

  public void init(FilterConfig filterConfig) {
  }

  /**
   * Filter all url.
   *
   * @param request ServletRequest
   * @param response ServletResponse
   * @param filterChain FilterChain
   * @throws IOException exception
   * @throws ServletException exception
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpSession session = httpServletRequest.getSession(true);
    boolean isLogin = UserInfo.fetchInfoFromSession(session) != null;
    if (httpServletResponse.getHeader("X-Frame-Options") == null) {
      httpServletResponse.addHeader("X-Frame-Options", "SAMEORIGIN");
    }
    if (httpServletResponse.getHeader("X-XSS-Protection") == null) {
      httpServletResponse.addHeader("X-XSS-Protection", "1; mode=block");
    }
    if (isLogin) {
      filterChain.doFilter(new XssRequestWrapper(httpServletRequest), httpServletResponse);
    } else {
      httpServletRequest.setAttribute("homeInfo", "您沒有權限檢視此頁面");
      httpServletRequest.getRequestDispatcher("/WEB-INF/views/index.jsp")
          .forward(request, response);
    }
  }

  public void destroy() {
  }
}
