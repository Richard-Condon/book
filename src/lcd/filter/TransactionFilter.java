package lcd.filter;

import lcd.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @athor lcdstart
 * @create 2022-08-04 16:36
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        try {                                                       //数据库事务处理的方式二，但需要重写一次JDBC工具类所以本项目就不使用了，方法一就是现在用的zai
                                                                       //每一个使用到数据库连接的Servlet程序中try-catch ，即 将下面的try-catch用到每一个用到数据库连接的
        //                                                             //Servlet程序中。
//            filterChain.doFilter(servletRequest,servletResponse);
//            JdbcUtils.CloseConnrctionAndCommit();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ServletException e) {
//            JdbcUtils.CloseConnrctionAndRollback();
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void destroy() {

    }
}
