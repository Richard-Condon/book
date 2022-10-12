package lcd.dao;

import lcd.pojo.OrderItem;

import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-08-03 16:53
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem, Connection conn);
}
