package lcd.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @athor lcdstart
 * @create 2022-08-02 16:47
 */
public class Cart {
    private Integer tltalCount;
    private BigDecimal totalPrice;
    private Map<Integer,CartItem> Items=new LinkedHashMap<>();
    //添加
    public void addItem(CartItem cartItem){
        CartItem cartItem1 = Items.get(cartItem.getId());
        if (cartItem1==null){
            Items.put(cartItem.getId(),cartItem);
        }else {
            cartItem1.setCount(cartItem1.getCount()+1);
            cartItem1.setTotalPrice(cartItem1.getPrice().multiply(new BigDecimal(cartItem1.getCount())));//更新总金额
        }
    }
//    删除
    public void deleteItem(Integer id){
        Items.remove(id);
    }
//    清空
    public void clear(){
        Items.clear();
    }
//    修改数量
    public void updateCount(Integer id,Integer count){
        CartItem cartItem = Items.get(id);
        if (cartItem==null){

        }else {
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }


















    public Integer getTltalCount() {
        tltalCount=0;
        for (Map.Entry<Integer,CartItem> entry:Items.entrySet()){
            tltalCount=tltalCount+entry.getValue().getCount();
        }
        return tltalCount;
    }



    public BigDecimal getTotalPrice() {
      totalPrice=new BigDecimal(0);
      for (Map.Entry<Integer,CartItem> entry:Items.entrySet()){
          totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
      }

        return totalPrice;
    }



    public Map<Integer, CartItem> getItems() {
        return Items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        Items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "tltalCount=" + getTltalCount() +
                ", totalPrice=" + getTotalPrice()+
                ", Items=" + Items +
                '}';
    }
}
