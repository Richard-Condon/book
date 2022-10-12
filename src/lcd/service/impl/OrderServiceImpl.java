package lcd.service.impl;

import lcd.dao.BookDao;
import lcd.dao.OrderDao;
import lcd.dao.OrderItemDao;
import lcd.dao.iml.BookDaoImpl;
import lcd.dao.iml.OrderDaoImpl;
import lcd.dao.iml.OrderItemDaoImpl;
import lcd.pojo.*;
import lcd.service.OrderService;

import java.sql.Connection;
import java.util.Date;
import java.util.Map;

/**
 * @athor lcdstart
 * @create 2022-08-03 18:14
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId, Connection conn) {
        System.out.println("OrderServiceImpl程序在【" + Thread.currentThread().getName() + "】中");
        //生成订单号--唯一性
        String orderid=System.currentTimeMillis()+"-"+userId;
        //创建一个订单对象
        Order order=new Order(orderid,0,new Date(),cart.getTotalPrice(),userId);
        orderDao.saveOrder(order,conn);

        for (Map.Entry<Integer,CartItem> entry:cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderid);
            orderItemDao.saveOrderItem(orderItem,conn);

            //对产生变化的量进行修改 （购买时，销量增加，库存减少）
            Book book = bookDao.queryBookByeId(cartItem.getId(), conn);
            book.setSales(book.getSales()+cartItem.getCount());
            book.setstock(book.getstock()-cartItem.getCount());
            bookDao.updataBook(book,conn);
        }
        cart.clear();

        return orderid;
    }
}
