package lcd.service.impl;

import lcd.dao.BookDao;
import lcd.dao.iml.BookDaoImpl;
import lcd.pojo.Book;
import lcd.pojo.Page;
import lcd.service.BookService;

import java.sql.Connection;
import java.util.List;

/**
 * @athor lcdstart
 * @create 2022-07-29 15:07
 */
public class BookServiceImpl implements BookService {
    BookDaoImpl bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book, Connection conn) {
        int i = bookDao.addBook(book, conn);
    }

    @Override
    public void deleteBook(Integer id, Connection conn) {
        bookDao.deleteBook(id,conn);
    }

    @Override
    public void updataBook(Book book, Connection conn) {
        bookDao.updataBook(book,conn);

    }

    @Override
    public Book queryBookById(Integer id, Connection conn) {
        Book book = bookDao.queryBookByeId(id, conn);
        return book;
    }

    @Override
    public List<Book> queryBooks(Connection conn) {
        return bookDao.queryBooks(conn);
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize, Connection conn) {
        Page<Book> page=new Page<>();

        page.setPageNo(pageNo);

        page.setPagesize(pageSize);

        Integer pageTotalCount = bookDao.queryForPageTotalCount(conn);
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal=pageTotalCount/pageSize;
        if (pageTotalCount%pageSize!=0){
            pageTotal=pageTotal+1;
        }
        page.setPageTotal(pageTotal);

        if (pageNo<1){
            pageNo=1;
        }
        if (pageNo>pageTotal){
            pageNo=pageTotal;
        }
        page.setPageNo(pageNo);

        List<Book> items=bookDao.queryForItems((page.getPageNo()-1)*page.getPagesize(),pageSize,conn);
        page.setItems(items);



        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max, Connection conn) {

        Page<Book> page=new Page<>();

        page.setPageNo(pageNo);

        page.setPagesize(pageSize);

        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max,conn);
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal=pageTotalCount/pageSize;
        if (pageTotalCount%pageSize!=0){
            pageTotal=pageTotal+1;
        }
        page.setPageTotal(pageTotal);

        if (pageNo<1){
            pageNo=1;
        }
        if (pageNo>pageTotal){
            pageNo=pageTotal;
        }
        page.setPageNo(pageNo);

        List<Book> items=bookDao.queryForItemsByPrice((page.getPageNo()-1)*page.getPagesize(),pageSize,min,max,conn);
        page.setItems(items);

        return page;
    }
}
