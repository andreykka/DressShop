package controllers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by gandy on 30.10.14.
 *
 */

@WebServlet(name = "Index", urlPatterns = "/index")
public class Index extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("in init() method Index Servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in Index servlet");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>in index.jsp</h1>");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("in destroy() method Index Servlet");
    }
}
