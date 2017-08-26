package org.redrock.servlet;


import freemarker.template.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.redrock.Jedis.Controller;
import org.redrock.util.Support;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="CroomServlet",value="/creat")
public class CroomServlet extends CommonServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//TODO:更新房间信息忘记写了!!
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<String, String> UserRoom = new HashMap<>();
        UserRoom.put("username" , req.getParameter("username"));
        UserRoom.put("people" , req.getParameter("people"));
        UserRoom.put("wodi" , req.getParameter("wodi"));
        UserRoom.put("pingmin",req.getParameter("pingmin"));
        int people = Integer.parseInt(UserRoom.get("people"));
        if(people >3 && people <= 6) {
            UserRoom.put("wodinum", "1");
            UserRoom.put("pingminnum", String.valueOf(people-1));
        } else if (people > 6 && people <= 11) {
            UserRoom.put("wodinum", "2");
            UserRoom.put("pingminnum", String.valueOf(people-2));
        } else if (people > 11 && people <= 15) {
            UserRoom.put("wodinum", "3");
            UserRoom.put("pingminnum", String.valueOf(people-3));
        } else {
            UserRoom.put("wodinum", "4");
            UserRoom.put("pingminnum", String.valueOf(people-4));
        }
        Map<String,String> res = Controller.PutRoomJedis(UserRoom);
        System.out.println(res);
        this.assign("roomid",res.get("roomid"));
        this.assign("wodinum",res.get("wodinum"));
        this.assign("pingminnum",res.get("pingminnum"));
        this.assign("wodi",res.get("wodi"));
        this.assign("pingmin",res.get("pingmin"));

        String wodiis;
        int people1 = Integer.parseInt(res.get("people"));
        if(people1 >3 && people1 <= 6) {
            wodiis = res.get("wrand")+"号";
        } else if (people1 > 6 && people1 <= 11) {
            wodiis = res.get("wrand1")+"号 , "+res.get("wrand2")+"号";
        } else if (people1 > 11 && people1 <= 15) {
            wodiis = res.get("wrand1")+"号  ,"+res.get("wrand2")+"号 , "+res.get("wrand3")+"号";
        } else {
            wodiis = res.get("wrand1")+"号 , "+res.get("wrand2")+"号 , "+res.get("wrand3")+"号 , "+res.get("wrand4")+"号";
        }
        this.assign("wodi_is",wodiis);
        this.display(resp,"/res.ftl");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("id");
        if(username.equals("isme")){
            String res = Controller.isme();
            resp.getWriter().println(res);
        }else {
            this.assign("name", username);
            this.display(resp, "/test.ftl");
        }

    }

}
