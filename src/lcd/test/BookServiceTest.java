package lcd.test;

import lcd.pojo.Book;
import lcd.pojo.Page;
import lcd.service.BookService;
import lcd.service.impl.BookServiceImpl;
import lcd.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @athor lcdstart
 * @create 2022-07-29 15:14
 */
class BookServiceTest {
private BookServiceImpl bookService=new BookServiceImpl();
    @Test
    void addBook() {
        Connection conn = JdbcUtils.getConnection();
        bookService.addBook(new Book(null,100,10,"嫁给白富美的1000种秘诀","路易斯",null,new BigDecimal(1000.98)),conn);
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }

    @Test
    void deleteBook() {
        Connection conn = JdbcUtils.getConnection();
        bookService.deleteBook(1,conn);
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }

    @Test
    void updataBook() {
        Connection conn = JdbcUtils.getConnection();
        bookService.updataBook(new Book(18,100,10,"嫁给白富美的1000种秘诀","路易斯",null,new BigDecimal(1998.98)),conn);
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }

    @Test
    void queryBookById() {
        Connection conn = JdbcUtils.getConnection();
        System.out.println(bookService.queryBookById(18, conn));
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }

    @Test
    void queryBooks() {
        Connection conn = JdbcUtils.getConnection();
        List<Book> books = bookService.queryBooks(conn);
        for (Book book:books){
            System.out.println(book);
        }
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }
    @Test
    public void page( ) {
        Connection conn = JdbcUtils.getConnection();
        System.out.println(bookService.page(4, 4, conn));
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }
}