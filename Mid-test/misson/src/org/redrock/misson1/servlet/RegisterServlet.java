package org.redrock.misson1.servlet;

import org.redrock.misson1.jdbc.Connect;
import org.redrock.misson1.jdbc.resultset.User;
import org.redrock.misson1.util.CheckUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name="RegisterServlet",value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String passwd = req.getParameter("passwd");
        String answer = req.getParameter("answer");
        Connect conn = new Connect();
        if(CheckUtil.CheckLength(passwd)) {
            if (CheckUtil.CheckText(username) && CheckUtil.CheckText(passwd) && CheckUtil.CheckText(answer)) {
                User user = new User();
                user.setUsername(username);
                try {
                    user.setPasswd(passwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                user.setAnswer(answer);
                conn.InsertUser(user);
                //success
                String context = req.getServletContext().getRealPath("/");
                resp.sendRedirect(context);
            }else{
                String res = "格式错误 请不要输入任何敏感词汇(^ ' @ # & * | ? + ( ) [ ] { } -以及空白字符)";
                resp.getWriter().println(res);
            }
        }else{
            String res = "密码长度不符合要求 (6-18位)";
            resp.getWriter().println(res);
        }
    }
}
