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
 * @create 2022-07-25 15:07
 */
public class RegistServlet extends HttpServlet {
    private UserServiceImpl userService=new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取请求的的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //检查验证码是否正确 暂时写死
        if ("asd".equalsIgnoreCase(code)){
            Connection conn=null;
            try {
                 conn = JdbcUtils.getConnection();
                if (userService.existUsername(username,conn)){
                    req.setAttribute("msg","用户名不可用，请重新输入");
                    req.setAttribute("username",username);
                    req.setAttribute("password",password);
                    req.setAttribute("repwd",password);
                    req.setAttribute("email",email);
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                }else {
                    userService.registUser(new User(username,null,password,email),conn);
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                }

            } catch (ServletException e) {
                JdbcUtils.CloseConnrctionAndRollback(conn);
            } finally {
                JdbcUtils.CloseConnrctionAndCommit(conn);
            }


        }else{
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("repwd",password);
            req.setAttribute("email",email);
            System.out.println("验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);

        }
    }
}
