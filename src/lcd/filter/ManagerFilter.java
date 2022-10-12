package lcd.filter;

import lcd.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @athor lcdstart
 * @create 2022-08-04 10:46
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        User user = (User) req.getSession().getAttribute("user");
        if (user==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
            return;
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
