package lcd.dao;

import lcd.pojo.User;

import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-07-25 9:29
 */
public interface UserDao {
    public User queryUserbyName(String name, Connection conn);
    public int SaveUser(User user,Connection conn);
    public User queryUserbyNameAndPassword(String name,String password,Connection conn);
}
