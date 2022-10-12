package lcd.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @athor lcdstart
 * @create 2022-08-03 16:42
 */
public class Order {
    private String orderId;
    private Integer status=0;
    private Date createTime;
    private BigDecimal price;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Integer status, Date createTime, BigDecimal price, Integer userId) {
        this.orderId = orderId;
        this.status = status;
        this.createTime = createTime;
        this.price = price;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", price=" + price +
                ", userId=" + userId +
                '}';
    }
}
