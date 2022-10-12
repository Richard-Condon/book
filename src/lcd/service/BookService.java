package lcd.service;

import lcd.pojo.Book;
import lcd.pojo.Page;

import java.sql.Connection;
import java.util.List;

/**
 * @athor lcdstart
 * @create 2022-07-29 15:01
 */
public interface BookService {

    public void addBook(Book book, Connection conn);
    public void deleteBook(Integer id,Connection conn);
    public void updataBook(Book book,Connection conn);
    public Book queryBookById(Integer id, Connection conn);
    public List<Book> queryBooks(Connection conn);

    public Page<Book> page(int pageNo, int pageSize, Connection conn);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max, Connection conn);

}
