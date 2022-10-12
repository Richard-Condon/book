package lcd.service.impl;

import lcd.dao.iml.UserDaoImpl;
import lcd.pojo.User;
import lcd.service.UserService;

import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-07-25 11:06
 */
public class UserServiceImpl implements UserService {
    private UserDaoImpl userDao=new UserDaoImpl();
    @Override
    public void registUser(User user, Connection conn) {
        userDao.SaveUser(user,conn);
    }

    @Override
    public User login(User user, Connection conn) {
        return userDao.queryUserbyNameAndPassword(user.getUsername(),user.getPassword(),conn);

    }

    @Override
    public boolean existUsername(String username, Connection conn) {
        if (userDao.queryUserbyName(username,conn)==null){
            return false;
        }
        return true;
    }
}
