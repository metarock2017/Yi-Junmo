package org.redrock.misson1.servlet.Console;

import org.redrock.misson1.jdbc.StudentDB;
import org.redrock.misson1.jdbc.resultset.Student;
import org.redrock.misson1.util.CheckUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Get {
    private Student rstu = null;
    public Get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(CheckUtil.CheckText(req.getParameter("stuId"))) {
            Student Student = new Student();
            Student.setStuId(req.getParameter("stuId"));
            StudentDB db = new StudentDB();
            rstu = db.GetaStu(Student);
            resp.getWriter().println(rstu.getName());
        }
    }
}
