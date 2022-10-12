package lcd.dao.iml;

import lcd.dao.UserDao;
import lcd.pojo.User;

import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-07-25 9:36
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserbyName(String name, Connection conn) {
        String sql="select * from bookuser where username=?";
        return select(User.class,conn,sql,name);
    }



    @Override
    public int SaveUser(User user,Connection conn) {
        String sql="insert into bookuser(username,password,email)values(?,?,?)";
        return  updatauser(conn,sql,user.getUsername(),user.getPassword(),user.getEmail());

    }

    @Override
    public User queryUserbyNameAndPassword(String name, String password,Connection conn) {
        String sql="Select id,username,password,email from bookuser where username=? And password=?";
        return select(User.class,conn,sql,name,password);

    }
}
