package lcd.service;

import lcd.pojo.User;

import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-07-25 11:01
 */
public interface UserService {
    public void registUser(User user, Connection conn);
    public User login(User user, Connection conn);
    public boolean existUsername(String username, Connection conn);


}
