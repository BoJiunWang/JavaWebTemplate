package config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ivan_Wang on 2017-07-02.
 */
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
    HttpServletResponse resp = (HttpServletResponse) response;
    resp.addHeader("X-Frame-Options", "SAMEORIGIN");
    resp.addHeader("X-XSS-Protection", "1; mode=block");
    request.setCharacterEncoding(encoding);
    filterChain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
  }

  /**
   * Add encoding.
   *
   * @param filterConfig FilterConfig
   * @throws ServletException exception
   */
  public void init(FilterConfig filterConfig) throws ServletException {
    String encodingParam = filterConfig.getInitParameter("encoding");
    if (encodingParam != null) {
      encoding = encodingParam;
    }
  }

  public void destroy() {
  }
}
