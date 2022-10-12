package lcd.web;

import lcd.dao.OrderItemDao;
import lcd.dao.iml.BaseDao;
import lcd.dao.iml.OrderItemDaoImpl;
import lcd.pojo.Cart;
import lcd.pojo.User;
import lcd.service.OrderService;
import lcd.service.impl.OrderServiceImpl;
import lcd.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-08-03 18:39
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();

    protected void creatOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=null;
        try {
             conn = JdbcUtils.getConnection();
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            User user = (User) req.getSession().getAttribute("user");

            System.out.println("orderServlet程序在【" + Thread.currentThread().getName() + "】中");
            String orderId = orderService.createOrder(cart,user.getId(), conn);
            req.getSession().setAttribute("orderId",orderId);

        } catch (Exception e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

}
