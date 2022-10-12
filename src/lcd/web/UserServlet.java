package lcd.web;

import com.google.gson.Gson;
import lcd.pojo.User;
import lcd.service.impl.UserServiceImpl;
import lcd.utils.JdbcUtils;
import lcd.utils.WebUtils;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @athor lcdstart
 * @create 2022-07-28 16:34
 */
public class UserServlet extends BaseServlet {
    private UserServiceImpl userService=new UserServiceImpl();


    protected void login(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        Connection conn = null;
        try {
            conn=JdbcUtils.getConnection();
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            User user = new User(username, null, password, null);


            if (userService.login(user,conn)!=null) {
                //保存用户的登录之后的信息到Session域中
                User user1=userService.login(user,conn);
                req.getSession().setAttribute("user",user1);
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
            }else {
                req.setAttribute("msg","账号密码错误,请重新输入");
                req.setAttribute("username",username);
                req.setAttribute("password",password);

                req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }

    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{

        String code = req.getParameter("code");
        User user = WebUtils.copyParamBean(req.getParameterMap(), new User());
        //获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //马上删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //检查验证码是否正确 暂时写死
        if (token.equalsIgnoreCase(code)){
            Connection conn = null;
            try {
                conn=JdbcUtils.getConnection();
                if (userService.existUsername(user.getUsername(),conn)){
                    req.setAttribute("msg","用户名不可用，请重新输入");
                    req.setAttribute("username",user.getUsername());
                    req.setAttribute("password",user.getPassword());
                    req.setAttribute("repwd",user.getPassword());
                    req.setAttribute("email",user.getEmail());
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                }else {
                    userService.registUser(user,conn);
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                }

            } catch (ServletException e) {
                JdbcUtils.CloseConnrctionAndRollback(conn);
            } finally {
                JdbcUtils.CloseConnrctionAndCommit(conn);
            }
        }else{
            req.setAttribute("username",user.getUsername());
            req.setAttribute("password",user.getPassword());
            req.setAttribute("repwd",user.getPassword());
            req.setAttribute("email",user.getEmail());
            req.setAttribute("msg","验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);

        }
    }


    //注销操作
    protected void logout(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
     //销毁Session中的用户信息（或销毁Session）
      req.getSession().invalidate();
      resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        Connection conn = JdbcUtils.getConnection();
        String json = null;
        try {
            String username = req.getParameter("username");
            boolean b = userService.existUsername(username, conn);
            Map<String,Object> resultMap=new HashMap<>();
            resultMap.put("existsUsername",b);
            Gson gson = new Gson();
            json = gson.toJson(resultMap);
        } catch (Exception e) {
            JdbcUtils.CloseConnrctionAndRollback(conn);
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.CloseConnrctionAndCommit(conn);
        }

        resp.getWriter().write(json);

    }




}
