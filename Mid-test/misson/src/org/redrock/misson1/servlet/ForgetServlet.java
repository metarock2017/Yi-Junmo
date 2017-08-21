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


@WebServlet(name = "ForgetServlet" , value = "/forget")
public class ForgetServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String answer = req.getParameter("answer");
        String passwd = req.getParameter("newpasswd");
        if(CheckUtil.CheckText(username)&&CheckUtil.CheckText(answer)) {

            if(CheckUtil.CheckLength(passwd)) {
                Connect conn = new Connect();
                User user = new User();
                User ruser = null;
                user.setUsername(username);
                try {
                    user.setPasswd(passwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                user.setAnswer(answer);
                ruser = conn.SelectUser(user);
                if(user.getAnswer().equals(ruser.getAnswer())) {
                    conn.UpdateUser(user);
                    //success
                } else {
                    String res = "密保问题错误";
                    resp.getWriter().println(res);
                }


            } else {
                String res = "新密码长度不符合要求 (6-18位)";
                resp.getWriter().println(res);
            }


        } else {
            String res = "格式错误 请不要输入任何敏感词汇(^ ' @ # & * | ? + ( ) [ ] { } -以及空白字符)";
            resp.getWriter().println(res);
        }

    }
}
