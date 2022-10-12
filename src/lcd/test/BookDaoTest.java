package lcd.test;

import lcd.dao.BookDao;
import lcd.dao.iml.BookDaoImpl;
import lcd.pojo.Book;
import lcd.service.BookService;
import lcd.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @athor lcdstart
 * @create 2022-07-29 11:21
 */
class BookDaoTest {
private BookDao bookDao=new BookDaoImpl();
private Connection conn= JdbcUtils.getConnection();
    @Test
    void addBook() {
        bookDao.addBook(new Book(null,100,10,"如何三十天挖到对面墙角","Richard",null,new BigDecimal(100)),conn);
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }

    @Test
    void deleteBook() {
    }

    @Test
    void updataBook() {
    }

    @Test
    void queryBookByeId() {
    }

    @Test
    void queryBooks() {
    }    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount(conn));
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }


    @Test
    public void queryForItems() {
       for (Book book:bookDao.queryForItems(8,4,conn)){
           System.out.println(book);
       }
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }
}