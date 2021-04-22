package com.lh.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、接收前端请求(获取前端参数)
        String method = req.getParameter("method");
        if(method.equals("add")){
            req.getSession().setAttribute("msg", "执行了add方法");
        }
        if(method.equals("delete")){
            req.getSession().setAttribute("msg", "执行了delete方法");
        }

        // 2、调用业务层

        // 3、视图转发或重定向
        req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req, resp);
//        req.getRequestDispatcher(); 转发
//        resp.sendRedirect(); 重定向

        // 再在 web.xml 中配置 servlet
        // 再配置tomcat

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用上边的doGet 复用
        doGet(req, resp);
    }
}
