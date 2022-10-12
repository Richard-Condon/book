package lcd.dao;

import lcd.pojo.Book;

import java.sql.Connection;
import java.util.List;

/**
 * @athor lcdstart
 * @create 2022-07-29 10:48
 */
public interface BookDao {

    public int addBook(Book book, Connection conn);
    public int deleteBook(Integer id,Connection conn);
    public int updataBook(Book book,Connection conn);
    public Book queryBookByeId(Integer id,Connection conn);
    public List queryBooks(Connection conn);

    public Integer queryForPageTotalCount(Connection conn);

    List<Book> queryForItems(int startFrom, int pageSize, Connection conn);

    List<Book> queryForItemsByPrice(int i, int pageSize, int min, int max, Connection conn);

    Integer queryForPageTotalCountByPrice(int min, int max, Connection conn);

}
