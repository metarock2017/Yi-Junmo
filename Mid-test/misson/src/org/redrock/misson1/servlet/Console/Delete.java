package org.redrock.misson1.servlet.Console;

import org.redrock.misson1.jdbc.StudentDB;
import org.redrock.misson1.jdbc.resultset.Student;
import org.redrock.misson1.util.CheckUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete {
    public Delete(HttpServletRequest req, HttpServletResponse resp) {
        Student rstu =null;
        if(CheckUtil.CheckText(req.getParameter("stuId"))) {
            Student Student = new Student();
            Student.setStuId(req.getParameter("stuId"));
            StudentDB db = new StudentDB();
            rstu = db.DeleteStu(Student);
            //resp.getWriter().println(); output
        }
    }
}
