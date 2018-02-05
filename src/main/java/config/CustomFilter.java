package config;

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
   * Add encoding.
   *
   * @param filterConfig FilterConfig
   */
  public void init(FilterConfig filterConfig) {
    String encodingParam = filterConfig.getInitParameter("encoding");
    if (encodingParam != null) {
      encoding = encodingParam;
    }
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
    if (httpServletResponse.getHeader("X-Frame-Options") == null) {
      httpServletResponse.addHeader("X-Frame-Options", "SAMEORIGIN");
    }
    if (httpServletResponse.getHeader("X-XSS-Protection") == null) {
      httpServletResponse.addHeader("X-XSS-Protection", "1; mode=block");
    }
    httpServletResponse.setCharacterEncoding(encoding);
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    httpServletRequest.setCharacterEncoding(encoding);
    filterChain.doFilter(new XssRequestWrapper(httpServletRequest), httpServletResponse);
  }

  public void destroy() {
  }
}
