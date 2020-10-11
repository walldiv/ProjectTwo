package com.ex.servlets;

import com.ex.services.GpsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetCacheByLocation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);

        float lat = Float.parseFloat(req.getParameter("lat"));
        float lng = Float.parseFloat(req.getParameter("lng"));

//        GpsService service = new GpsService();
//        service.
    }
}
