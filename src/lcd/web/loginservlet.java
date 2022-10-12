package lcd.web;

import lcd.pojo.User;
import lcd.service.impl.UserServiceImpl;
import lcd.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @athor lcdstart
 * @create 2022-07-25 16:47
 */
public class loginservlet extends HttpServlet {
    private UserServiceImpl userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=null;
        try {
             conn = JdbcUtils.getConnection();
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            User user = new User(username, null, password, null);
            if (userService.login(user,conn)!=null) {
                //保存用户的登录之后的信息到Session域中
                req.getSession().setAttribute("user",user);
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
            }else {
                req.setAttribute("msg","账号密码错误,请重新输入");
                req.setAttribute("username",username);
                req.setAttribute("password",password);

                req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            }
        } catch (ServletException e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }
    }
}
