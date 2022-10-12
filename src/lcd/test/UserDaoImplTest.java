package lcd.test;

import lcd.dao.UserDao;
import lcd.dao.iml.UserDaoImpl;
import lcd.pojo.User;
import lcd.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @athor lcdstart
 * @create 2022-07-25 9:54
 */
class UserDaoImplTest {

    @Test
    void queryUserbyName() {
        UserDao userDao = new UserDaoImpl();
        Connection conn = JdbcUtils.getConnection();
        System.out.println(userDao.queryUserbyName("admin", conn));
        JdbcUtils.CloseConnrctionAndCommit(conn);

    }

    @Test
    void saveUser() throws SQLException {
        UserDaoImpl userDao = new UserDaoImpl();
        Connection conn = JdbcUtils.getConnection();
        User user=new User("admin1", null, "123456", "asuk@QQ.com");
        System.out.println(userDao.SaveUser(user,conn));
        JdbcUtils.CloseConnrctionAndCommit(conn);

    }

    @Test
    void queryUserbyNameAndPassword() {
        UserDaoImpl userDao = new UserDaoImpl();
        Connection conn = JdbcUtils.getConnection();
        System.out.println(userDao.queryUserbyNameAndPassword("admin", "admin", conn));
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }
}