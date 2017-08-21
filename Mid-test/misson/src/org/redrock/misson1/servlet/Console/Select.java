package org.redrock.misson1.servlet.Console;

import org.redrock.misson1.jdbc.StudentDB;
import org.redrock.misson1.jdbc.resultset.Student;
import org.redrock.misson1.util.CheckUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Select {
    public Select(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String,String> method = new HashMap<>();
        if(req.getParameter("page").matches("[0-9]+")&&req.getParameter("per_page").matches("[0-9]+")&& CheckUtil.CheckText(req.getParameter("sortby")) && CheckUtil.CheckText(req.getParameter("sort"))) {
            Integer page = Integer.valueOf(req.getParameter("page"));
            Integer per_page = Integer.valueOf(req.getParameter("per_page"));
            method.put("sortby",req.getParameter("sortby"));
            method.put("sort",req.getParameter("sort")+'/');
            if(req.getParameter("name")!=null && CheckUtil.CheckText(req.getParameter("name"))) {
                method.put("name",req.getParameter("name")+"%");
            }else{
                method.put("name","%");
            }
            if(req.getParameter("stuId")!=null && CheckUtil.CheckText(req.getParameter("stuId"))){
                method.put("stuId",req.getParameter("stuId")+"%");
            }else{
                method.put("stuId","%");
            }

            List<Student> AllStu = new ArrayList<>();
            StudentDB db = new StudentDB();
            AllStu = db.SelectAllStu(method);
            if(page*per_page>AllStu.size()){
                resp.getWriter().println("页码太大了！！");
            }else{
                System.out.println(AllStu.subList(page*(per_page-1),page*per_page));
            }
            //System.out.println(AllStu.subList(page*(per_page-1),page*per_page));
            //输出
        } else {
            String res = "输入的数据错误";
            resp.getWriter().println(res);
        }

    }
}
