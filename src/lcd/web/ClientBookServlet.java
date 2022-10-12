package lcd.web;

import lcd.pojo.Book;
import lcd.pojo.Page;
import lcd.service.impl.BookServiceImpl;
import lcd.utils.JdbcUtils;
import lcd.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-08-01 8:44
 */
public class ClientBookServlet extends BaseServlet{
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html; charset=UTF-8");
//        String action=req.getParameter("action");
//
//        try {
//            req.setCharacterEncoding("UTF-8");
//            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
//            method.invoke(this,req,resp);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    private BookServiceImpl bookService=new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("经过了CBS");
        Page<Book> page= null;
        Connection conn=null;
        try {
             conn = JdbcUtils.getConnection();
            int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
            int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
            page = bookService.page(pageNo,pageSize,conn);
            page.setUrl("client/bookservlet?action=page");

        } catch (Exception e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }

        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/Client/index.jsp").forward(req,resp);
    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("经过了CBS");
        Page<Book> page= null;
        Connection conn=null;
        try {
             conn = JdbcUtils.getConnection();
            int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
            int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
            int min=WebUtils.parseInt(req.getParameter("min"),0);
            int max=WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
            page = bookService.pageByPrice(pageNo,pageSize,min,max,conn);
            StringBuilder sb = new StringBuilder("client/bookservlet?action=pageByPrice");
            if (req.getParameter("min")!=null){
                sb.append("&min=").append(req.getParameter("min"));
            }
            if (req.getParameter("max")!=null){
                sb.append("&max=").append(req.getParameter("max"));
            }
            page.setUrl(sb.toString());

        } catch (Exception e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }

        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/Client/index.jsp").forward(req,resp);
    }
}
