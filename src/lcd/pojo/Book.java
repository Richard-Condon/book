package lcd.pojo;

import java.math.BigDecimal;

/**
 * @athor lcdstart
 * @create 2022-07-29 10:40
 */
public class Book {
    private Integer id;
    private Integer stock;
    private Integer sales;
    private String name;
    private String author;
    private String imgPath="pages/static/img/default.jpg";
    private BigDecimal price;

    public Book() {
    }

    public Book(Integer id, Integer stock, Integer sales, String name, String author, String imgPath, BigDecimal price) {
        this.id = id;
        this.stock = stock;
        this.sales = sales;
        this.name = name;
        this.author = author;

        if (imgPath!=null&&"".equals(imgPath)){
            this.imgPath = imgPath;
        }

        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getstock() {
        return stock;
    }

    public void setstock(Integer sostock) {
        this.stock = sostock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgPath() {

        return imgPath;
    }

    public void setImgPath(String imgPath) {
        if (imgPath!=null&&"".equals(imgPath)){
            this.imgPath = imgPath;
        }

    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", sostock=" + stock +
                ", sales=" + sales +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", price=" + price +
                '}';
    }
}
