package com.ex.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/***
 * This servlet class processes the requests to logout a user.
 *
 * @author Daniel Wallace
 */

public class UserLogout extends HttpServlet {

    /***
     * @param req- HTTP request:
     * @param resp - HTTP response:
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        HttpSession session = req.getSession();
        session.invalidate();
        //resp.sendRedirect("");


        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("Logout Successful");
    }
}
