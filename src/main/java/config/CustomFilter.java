package config;

import components.UserInfo;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
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
    urlPatterns = {"/*"},
    filterName = "setEncoding"
)
public class CustomFilter implements Filter {

  private String encoding = "utf-8";

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
    boolean isLoginUrl = httpServletRequest.getRequestURI()
        .equals(httpServletRequest.getContextPath() + "/Login");
    boolean isRootUrl = httpServletRequest.getRequestURI()
        .equals(httpServletRequest.getContextPath() + "/");
    boolean isResourceFile = httpServletRequest.getRequestURI()
        .contains(httpServletRequest.getContextPath() + "/resources");
    boolean isResponseError = httpServletResponse.getStatus() != HttpServletResponse.SC_OK;

    // If logged in or at root page/login page or resource file or response status error, ignore it.
    // Else redirect to root page
    if (isLogin || isLoginUrl || isRootUrl || isResourceFile || isResponseError) {
      if (httpServletResponse.getHeader("X-Frame-Options") == null) {
        httpServletResponse.addHeader("X-Frame-Options", "SAMEORIGIN");
      }
      if (httpServletResponse.getHeader("X-XSS-Protection") == null) {
        httpServletResponse.addHeader("X-XSS-Protection", "1; mode=block");
      }
      httpServletResponse.setCharacterEncoding(encoding);
      httpServletRequest.setCharacterEncoding(encoding);
      filterChain.doFilter(new XssRequestWrapper(httpServletRequest), httpServletResponse);
    } else {
      httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
    }
  }

  /**
   * Add encoding.
   *
   * @param filterConfig FilterConfig
   * @throws ServletException exception
   */
  public void init(FilterConfig filterConfig) {
    String encodingParam = filterConfig.getInitParameter("encoding");
    if (encodingParam != null) {
      encoding = encodingParam;
    }
  }

  public void destroy() {
  }
}
