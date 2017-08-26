package org.redrock.servlet;
import org.redrock.servlet.core.DoGet;
import org.redrock.servlet.core.DoPost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "IndexServlet", value = "/")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        DoPost.DoPost(request,response);
    }

    //get请求处理
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoGet.DoGet(request,response);
    }
}
