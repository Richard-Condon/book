package lcd.service;

import lcd.pojo.Cart;

import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-08-03 18:12
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId, Connection conn);
}
