package lcd.web;

import com.google.gson.Gson;
import lcd.pojo.Book;
import lcd.pojo.Cart;
import lcd.pojo.CartItem;
import lcd.service.BookService;
import lcd.service.impl.BookServiceImpl;
import lcd.utils.JdbcUtils;
import lcd.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @athor lcdstart
 * @create 2022-08-03 9:19
 */
public class CartServlet extends  BaseServlet {
   private BookService bookService=new BookServiceImpl();
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart= null;
        Connection conn=null;
        Map<String,Object> map=new HashMap<>();
        Gson gson = new Gson();
        try {
             conn = JdbcUtils.getConnection();
            int id = WebUtils.parseInt(req.getParameter("id"), 0);
            Book book = bookService.queryBookById(id, conn);
            String lastBook=book.getName();



            if (req.getSession().getAttribute("cart")==null){
                cart=new Cart();
                req.getSession().setAttribute("cart",cart);
            }


            cart = (Cart) req.getSession().getAttribute("cart");
            cart.addItem(new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice()));
            Integer tltalCount = cart.getTltalCount();
            map.put("tltalCount",tltalCount);
            map.put("lastname",lastBook);
        } catch (Exception e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }
        System.out.println(cart);
        String json = gson.toJson(map);
        resp.getWriter().write(json);//此处使用了Ajax所以下面的重定向不再使用

        //重定向
//        resp.sendRedirect(req.getHeader("referer"));//req.getHeader("referer")获取发起请求参数的页面地址
    }


    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        resp.sendRedirect(req.getHeader("referer"));

        }


    protected void clearItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getHeader("referer"));
    }


    protected void upDataCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        int id = WebUtils.parseInt(req.getParameter("bookid"), 0);
        System.out.println(req.getParameter("bookid"));
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("referer"));
        }


    }
}
