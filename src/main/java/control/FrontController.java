package control;

import sun.plugin.com.Dispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    private String DEFAULT_PAGE = "index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");

        if(page == null){
            resp.sendRedirect(DEFAULT_PAGE);
        }

        if(page.equals("service")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/service");
            requestDispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(DEFAULT_PAGE);
        }
    }
}
