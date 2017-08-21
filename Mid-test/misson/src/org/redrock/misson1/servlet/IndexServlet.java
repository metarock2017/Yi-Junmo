package org.redrock.misson1.servlet;

import org.redrock.misson1.jdbc.resultset.Student;
import org.redrock.misson1.servlet.Console.Delete;
import org.redrock.misson1.servlet.Console.Get;
import org.redrock.misson1.servlet.Console.Select;
import org.redrock.misson1.servlet.Console.Update;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "IndexServlet", value = "/home")
public class IndexServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if(method.equals("Update")) {
            new Update(req,resp);
        } else if(method.equals("Delete")) {
            new Delete(req,resp);
        } else if(method.equals("Get")) {
            Get student = new Get(req,resp);
        } else if(method.equals("Select")) {
            new Select(req,resp);
        } else {
            String res = "无法识别命令";
            resp.getWriter().println(res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
    }
}
