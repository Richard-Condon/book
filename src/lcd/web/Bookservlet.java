package lcd.web;

import lcd.pojo.Book;
import lcd.pojo.Page;
import lcd.service.BookService;
import lcd.service.impl.BookServiceImpl;
import lcd.utils.JdbcUtils;
import lcd.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * @athor lcdstart
 * @create 2022-07-29 16:20
 */
public class Bookservlet extends BaseServlet{
    private BookServiceImpl bookService=new BookServiceImpl();
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pn = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pn+=1;
        Connection conn=null;
        try {
             conn = JdbcUtils.getConnection();
            //将请求参数封装成为Book对象
            Book book = WebUtils.copyParamBean(req.getParameterMap(),new Book());
            //调用bookservice.addbook()保存图书
            bookService.addBook(book,conn);

        } catch (Exception e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }
        resp.sendRedirect(req.getContextPath()+"/manager/bookservlet?action=page&pageNo="+pn);//重定向，解决F5刷像页面时反复执行上一个操作
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=null;
        try {
            conn = JdbcUtils.getConnection();
            Integer id = Integer.valueOf(req.getParameter("id"));
            bookService.deleteBook(id,conn);

        } catch (NumberFormatException e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }

        resp.sendRedirect(req.getContextPath()+"/manager/bookservlet?action=page&pageNo="+req.getParameter("pageNo"));//重定向
    }
    protected void updata(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=null;
        try {
             conn = JdbcUtils.getConnection();
            Book book = WebUtils.copyParamBean(req.getParameterMap(), new Book());
            bookService.updataBook(book,conn);

        } catch (Exception e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }

        resp.sendRedirect(req.getContextPath()+"/manager/bookservlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = null;
        Connection conn=null;
        try {
             conn = JdbcUtils.getConnection();
            books = bookService.queryBooks(conn);

        } catch (Exception e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }

        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);//请求转发

    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Book book = null;
        Connection conn=null;
        try {
             conn = JdbcUtils.getConnection();
            Integer id= Integer.valueOf(req.getParameter("id"));
            book = bookService.queryBookById(id, conn);

        } catch (NumberFormatException e) {
           JdbcUtils.CloseConnrctionAndRollback(conn);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }

        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);


    }


    //分页功能
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Page<Book> page= null;
        Connection conn=null;
        try {
             conn = JdbcUtils.getConnection();
            int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),1);
            int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
            page = bookService.page(pageNo,pageSize,conn);
            page.setUrl("manager/bookservlet?action=page");

        } catch (Exception e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }

        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
