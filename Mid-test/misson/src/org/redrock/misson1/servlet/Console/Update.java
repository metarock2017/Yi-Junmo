package org.redrock.misson1.servlet.Console;

import org.redrock.misson1.jdbc.StudentDB;
import org.redrock.misson1.jdbc.resultset.Student;
import org.redrock.misson1.util.CheckUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Update {
    public Update(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(CheckUtil.CheckText(req.getParameter("stuId"))&&CheckUtil.CheckText(req.getParameter("name"))&& (Integer.parseInt(req.getParameter("gender"))== 0 || Integer.parseInt(req.getParameter("gender")) == 1) && (Integer.parseInt(req.getParameter("grade"))< 10000 && Integer.parseInt(req.getParameter("grade"))>1000) && CheckUtil.CheckText(req.getParameter("college"))&&CheckUtil.CheckText(req.getParameter("major"))&& CheckUtil.CheckText(req.getParameter("class"))) {
            Student Student = new Student();
            Student.setStuId(req.getParameter("stuId"));
            Student.setName(req.getParameter("name"));
            Student.setGender(Integer.parseInt(req.getParameter("gender")));
            Student.setGrade(Integer.parseInt(req.getParameter("grade")));
            Student.setCollege(req.getParameter("college"));
            Student.setMajor(req.getParameter("major"));
            Student.setClasses(req.getParameter("class"));
            StudentDB db = new StudentDB();
            db.UpdateStu(Student);
        } else {
            String res = "输入格式错误";
            resp.getWriter().println(res);
        }

    }
}
