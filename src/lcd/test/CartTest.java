package lcd.test;

import lcd.pojo.Cart;
import lcd.pojo.CartItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @athor lcdstart
 * @create 2022-08-02 17:11
 */
class CartTest {

    @Test
    void addItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"asdsad",100,new BigDecimal(123),new BigDecimal(4561)));
        cart.addItem(new CartItem(2,"asd12sad",12300,new BigDecimal(121233),new BigDecimal(45361)));
        cart.addItem(new CartItem(3,"asd32sad",12100,new BigDecimal(121233),new BigDecimal(4512361)));
        cart.addItem(new CartItem(4,"asd12sad",10320,new BigDecimal(1233),new BigDecimal(4561231)));
        System.out.println(cart);
    }

    @Test
    void deleteItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"asdsad",100,new BigDecimal(123),new BigDecimal(4561)));
        cart.addItem(new CartItem(2,"asd12sad",12300,new BigDecimal(121233),new BigDecimal(45361)));
        cart.addItem(new CartItem(3,"asd32sad",12100,new BigDecimal(121233),new BigDecimal(4512361)));
        cart.addItem(new CartItem(4,"asd12sad",10320,new BigDecimal(1233),new BigDecimal(4561231)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    void clear() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"asdsad",100,new BigDecimal(123),new BigDecimal(4561)));
        cart.addItem(new CartItem(2,"asd12sad",12300,new BigDecimal(121233),new BigDecimal(45361)));
        cart.addItem(new CartItem(3,"asd32sad",12100,new BigDecimal(121233),new BigDecimal(4512361)));
        cart.addItem(new CartItem(4,"asd12sad",10320,new BigDecimal(1233),new BigDecimal(4561231)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    void updateCount() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"asdsad",100,new BigDecimal(123),new BigDecimal(4561)));
        cart.addItem(new CartItem(2,"asd12sad",12300,new BigDecimal(121233),new BigDecimal(45361)));
        cart.addItem(new CartItem(3,"asd32sad",12100,new BigDecimal(121233),new BigDecimal(4512361)));
        cart.addItem(new CartItem(4,"asd12sad",10320,new BigDecimal(1233),new BigDecimal(4561231)));
        cart.updateCount(1,-1);
        System.out.println(cart);
    }
}