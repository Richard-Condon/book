package lcd.dao.iml;

import lcd.dao.OrderDao;
import lcd.pojo.Order;

import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-08-03 16:54
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order, Connection conn) {
        System.out.println("OrderDaoImpl程序在【" + Thread.currentThread().getName() + "】中");
        String sql="insert into t_order(order_id,create_time,total_money,status,user_id)values(?,?,?,?,?)";
        return  updatauser(conn,sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());

    }

}
