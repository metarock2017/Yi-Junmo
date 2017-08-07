package org.redrock.SendGet;


import org.redrock.util.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(name = "AccessTokenServlet")

public class AccessTokenServlet extends HttpServlet {
    public void init() throws ServletException {
        new Thread(new TokenThread()).start(); //启动进程
    }
//来自 http://www.cnblogs.com/fengzheng/p/5027630.html  学习了！


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}