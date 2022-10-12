package lcd.dao;

import lcd.pojo.Order;

import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-08-03 16:51
 */
public interface OrderDao {
    public int saveOrder(Order order, Connection conn);

}
