package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="Main", urlPatterns="/main")
public class Main extends HttpServlet {

    public static final String ADMIN_PAGE = "Admin";
    public static final String INDEX_PAGE = "Index";
    private static final long serialVersionUID = -2892008845420545299L;



    private void proccessRequest(HttpServletRequest req, HttpServletResponse resp){

        String page = req.getServletPath();
        System.out.println("servlet Path " + page);
        System.out.println("in /* servlet");
        this.dispatch(req, resp, page);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, String page) {

        if (page.equalsIgnoreCase("/" + ADMIN_PAGE)) {
            page = ADMIN_PAGE;
        } else {
            page = INDEX_PAGE;
        }

        try {
            getServletContext().getNamedDispatcher(page).forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.proccessRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.proccessRequest(req, resp);
    }

}
