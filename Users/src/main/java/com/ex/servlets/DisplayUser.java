package com.ex.servlets;

import com.ex.model.User;
import com.ex.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/***
 * This servlet class processes the requests to display a user.
 *
 * @author Daniel Wallace
 */

public class DisplayUser extends HttpServlet {
    /***
     * @param req- HTTP request
     * @param resp - HTTP response: contains the user information
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);

        /*Get the HTTPSession variable for LoggedInUser & if not valid - redirect/response failure
            otherwise invoke DAO and return a User object of logged in user */
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("LoggedInUser");
        if(user == null) {
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(400);
            resp.getWriter().write("Please login to use this feature");
            System.out.println("DisplayUser::doGet() - User not logged in error");
        } else {
            UserService service = new UserService();
            User tmp = service.displayUser(user);
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(tmp);
            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write(json);
            System.out.println("DisplayUser::doGet() - JSON RESPONSE - " + json);
        }
    }
}
