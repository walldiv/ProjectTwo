package com.ex.servlets;

import com.ex.model.User;
import com.ex.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
/***
 * This servlet class processes the requests to login a user.
 *
 * @author Daniel Wallace
 */

public class UserLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);


    }
    /***
     * @param req- HTTP request: contains email and password
     * @param resp - HTTP response: contains the user information
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        HttpSession session = req.getSession();
        User user = new User();
        UserService uService = new UserService();
        String email = req.getParameter("email");
        String hashedPass = uService.hashPassword(req.getParameter("password"));
        user.setEmail(email);
        user.setPassword(hashedPass);

        user = uService.loginUser(user);
        session.setAttribute("LoggedInUser", user);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        //cookies for the client side rendering
        Cookie c1 = new Cookie("userName", user.getFirstname());
        c1.setMaxAge(60*60);
        Cookie c2 = new Cookie("userEmail", user.getEmail());
        c2.setMaxAge(60*60);

        resp.setContentType("application/json");
        resp.setStatus(200);
        resp.addCookie(c1);
        resp.addCookie(c2);
        resp.getWriter().write(json);
        System.out.println("UserLogin::COOKIES - " + c1.toString() + c2.toString());
        System.out.println("USER EMAIL: " + user.getEmail());

        resp.sendRedirect(req.getContextPath() + "/ThatTeam_GeoCache/myhome.html");

    }

}
