package lcd.dao.iml;

import lcd.dao.BookDao;
import lcd.pojo.Book;
import lcd.pojo.Page;

import java.sql.Connection;
import java.util.List;

/**
 * @athor lcdstart
 * @create 2022-07-29 10:52
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book, Connection conn) {
        String sql="insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`)"+
                "values(?,?,?,?,?,?)";
        return updatauser(conn,sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getstock(),book.getImgPath());
    }



    @Override
    public int deleteBook(Integer id,Connection conn) {
        String sql="delete from t_book where id=?";

        return updatauser(conn,sql,id);
    }

    @Override
    public int updataBook(Book book,Connection conn) {
        System.out.println("BookDaoImpl程序在【" + Thread.currentThread().getName() + "】中");
        String sql="update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return updatauser(conn,sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getstock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookByeId(Integer id,Connection conn) {
        String sql="select id,name,author,price,sales,stock,img_path imgPath from t_book where id=? ";
        return select(Book.class,conn,sql,id);
    }

    @Override
    public List<Book> queryBooks(Connection conn) {
        String sql="select id,name,author,price,sales,stock,img_path imgPath from t_book  ";
        return selectlist(Book.class,conn,sql);
    }

    @Override
    public Integer queryForPageTotalCount(Connection conn) {
        String sql="select count(*)  from t_book";
        Number count = (Number) select1(conn, sql);
        return count.intValue();
    }


    @Override
    public List<Book> queryForItems(int startFrom, int pageSize, Connection conn) {
        String sql="select id,name,author,price,sales,stock,img_path imgPath from t_book limit ?,?";

        return selectlist(Book.class,conn,sql,startFrom,pageSize);
    }

    @Override
    public List<Book> queryForItemsByPrice(int i, int pageSize, int min, int max, Connection conn) {
        String sql="select id,name,author,price,sales,stock,img_path imgPath from t_book where price>=? and price<=? order by price limit ?,? ";

        return selectlist(Book.class,conn,sql,min,max,i,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max, Connection conn) {
        String sql="select count(*)  from t_book where price>=? and price<=? ";
        Number count = (Number) select1(conn, sql,min,max);
        return count.intValue();
    }

}
