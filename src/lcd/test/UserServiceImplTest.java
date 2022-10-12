package lcd.test;

import lcd.pojo.User;
import lcd.service.UserService;
import lcd.service.impl.UserServiceImpl;
import lcd.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @athor lcdstart
 * @create 2022-07-25 11:21
 */
class UserServiceImplTest {

    @Test
    void registUser() {
        UserService userService=new UserServiceImpl();
        Connection conn = JdbcUtils.getConnection();
        User user=new User("adm1in",123,"123456","asdsa@QQ.com");
        userService.registUser(user,conn);
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }

    @Test
    void login() {
        UserService userService=new UserServiceImpl();
        Connection conn = JdbcUtils.getConnection();
        User user=new User("adm1in",123,"123456","asdsa@QQ.com");
        System.out.println(userService.login(user, conn));
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }

    @Test
    void existUsername() {
        UserService userService=new UserServiceImpl();
        Connection conn = JdbcUtils.getConnection();
        User user=new User("admn",123,"123456","asdsa@QQ.com");
        System.out.println(userService.existUsername(user.getUsername(), conn));
        JdbcUtils.CloseConnrctionAndCommit(conn);
    }
}