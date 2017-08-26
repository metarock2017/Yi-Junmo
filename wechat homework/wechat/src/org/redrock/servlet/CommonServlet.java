package org.redrock.servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.redrock.util.Container;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
/**
 * 整合freemarker视图引擎的父类
 */
public class CommonServlet extends HttpServlet {
    /**
     * freemarker配置类
     * 常驻内存
     */
    protected Configuration configuration;

    /**
     * 为不同线程维护一个Map对象 用于充当数据模型 容器
     * 不同的线程具有一个不同的ThreadLocal实例
     */
    protected ThreadLocal<Map> template_data;

    /**
     * servlet初始化方法，servlet生命周期中只调用一次
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        configuration = new Configuration();
        template_data = new ThreadLocal<>();
        //设置模板文件夹
        configuration.setServletContextForTemplateLoading(getServletContext(), "/WEB-INF/view");
        //设置字符编码
        configuration.setDefaultEncoding("UTF-8");
        //设置模板解析异常处理器
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    /**
     * servlet每收到一次请求就会分配一个线程并执行service方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        template_data.set(new HashMap());
        //父类service方法中会根据Http请求去执行相应的Http请求处理方法（doPost 或 doGet）
        super.service(req, resp);
    }

    /**
     * 设置模板参数
     * @param paramName
     * @param paramValue
     */
    protected void assign(String paramName, Object paramValue) {
        template_data.get().put(paramName, paramValue);
    }

    /**
     * 设置自定义的模板函数类
     * 这里freemarker只能传递类的实例对象，
     * 比如一个简单的具有获取当前时间戳的方法的类，
     * 如果在不同的servlet中多次用到，
     * 最好的方式就是使用单例模式。
     * 但是如果相似的类还有很多，
     * 那么DIY一个通过 饿汉式方式实例化对象 的常驻内存容器就能完美解决这个问题
     * @param paramName
     * @param methodClass
     * @param <T>
     */
    protected <T> void assignMethod(String paramName, Class<T> methodClass) {
        template_data.get().put(paramName, Container.getInstance(methodClass));
    }

    /**
     * 模板 + 数据模型 = 输出
     * 合并模板和数据模型
     * @param response
     * @param templatePath
     * @throws IOException
     */
    public void display(HttpServletResponse response, String templatePath) throws IOException {
//        response.setCharacterEncoding("UTF-8");
        //获取输出流
        PrintWriter writer = response.getWriter();
        //获取模板
        Template template = configuration.getTemplate(templatePath,"utf-8");
        try {
            //模板 + 数据模型 = 输出
            template.process(template_data.get(), writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}