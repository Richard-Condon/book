package lcd.test;

import lcd.dao.OrderDao;
import lcd.dao.iml.OrderDaoImpl;
import lcd.pojo.Order;
import lcd.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @athor lcdstart
 * @create 2022-08-03 17:12
 */
class OrderDaoTest {

    @Test
    void saveOrder() {
        OrderDao orderDao=new OrderDaoImpl();
        Connection conn = JdbcUtils.getConnection();
        orderDao.saveOrder(new Order("1234567890",0,new Date(),new BigDecimal(100),1),conn);
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }
}