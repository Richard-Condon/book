package lcd.test;

import lcd.pojo.Cart;
import lcd.pojo.CartItem;
import lcd.service.impl.OrderServiceImpl;
import lcd.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @athor lcdstart
 * @create 2022-08-03 18:34
 */
class OrderServiceTest {

    @Test
    void createOrder() {
        Connection conn = JdbcUtils.getConnection();

        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"asdsad",100,new BigDecimal(123),new BigDecimal(4561)));
        cart.addItem(new CartItem(2,"asd12sad",12300,new BigDecimal(121233),new BigDecimal(45361)));
        cart.addItem(new CartItem(3,"asd32sad",12100,new BigDecimal(121233),new BigDecimal(4512361)));
        cart.addItem(new CartItem(4,"asd12sad",10320,new BigDecimal(1233),new BigDecimal(4561231)));
        OrderServiceImpl orderService = new OrderServiceImpl();
        System.out.println("订单号为："+orderService.createOrder(cart, 1, conn));

        JdbcUtils.CloseConnrctionAndCommit(conn);
    }
}