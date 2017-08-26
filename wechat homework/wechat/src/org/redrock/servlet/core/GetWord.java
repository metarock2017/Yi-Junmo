package org.redrock.servlet.core;


import org.redrock.util.CurlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetWord" , value = "/getname")
public class GetWord  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String method = req.getParameter("momo");
        if(method.equals("momo.123")) {
            String url = "http://zhuoyouwx.weapp.me/game/ajax_words";
            CurlUtil Url = new CurlUtil();
            String res = Url.getContent(url, null, "GET");
            resp.getWriter().println(res);
        }else{
            String res = "you can got anything";
            resp.getWriter().println(res);
        }
    }
}
