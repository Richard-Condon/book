package lcd.dao.iml;

import lcd.dao.OrderItemDao;
import lcd.pojo.OrderItem;

import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-08-03 16:55
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem, Connection conn) {
        System.out.println("OrderItemDaoImpl程序在【" + Thread.currentThread().getName() + "】中");
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_money` ,`order_id`)"+
                "values(?,?,?,?,?)";
        return   updatauser(conn,sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());

    }
}
