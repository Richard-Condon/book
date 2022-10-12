package lcd.test;

import lcd.dao.OrderDao;
import lcd.dao.OrderItemDao;
import lcd.dao.iml.OrderDaoImpl;
import lcd.dao.iml.OrderItemDaoImpl;
import lcd.pojo.Order;
import lcd.pojo.OrderItem;
import lcd.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @athor lcdstart
 * @create 2022-08-03 17:33
 */
class OrderItemDaoTest {

    @Test
    void saveOrderItem() {
        OrderItemDao orderDao=new OrderItemDaoImpl();
        Connection conn = JdbcUtils.getConnection();
        orderDao.saveOrderItem(new OrderItem(null,"java-100",100,new BigDecimal(100),new BigDecimal(10000),"1234567890"),conn);
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }
}