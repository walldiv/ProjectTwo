package com.ex.servlets;

import com.ex.model.DifficultyLevel;
import com.ex.model.GeoCashe;
import com.ex.model.Item;
import com.ex.services.GpsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/***
 * This servlet class processes the requests to read and write geocaches.
 *
 * @author Shawyn Kane
 */
//@WebServlet("/GeoCacheReadAndWriteServlet")
//SERVLET MAPPING URL= /readandwrite
public class GeoCacheReadAndWriteServlet extends HttpServlet {
    private GpsService gpsService;

    @Override
    public void init() throws ServletException {
        super.init();
        gpsService = new GpsService();
    }

    /***
     * This method processes the request to read all geocaches currently in persistent storage.
     * @param req - HTTP request
     * @param resp - HTTP response, sends back all the geocaches in the DBase
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(gpsService.getAllCashes()));

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(gpsService.getAllCashes());
        System.out.println(json);
    }

    /***
     * This method processes the request to write/create a new geocache in persistent storage.
     * @param req - HTTP request: contains an imageurl, lat and lng coordinates, and difficulty level
     * @param resp - HTTP response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GeoCashe geoCashe = new GeoCashe(null, req.getParameter("imageurl"),
                Float.parseFloat(req.getParameter("lat")), Float.parseFloat(req.getParameter("lng")),
                new DifficultyLevel(Integer.parseInt(req.getParameter("difficultylevelid"))));
        if(gpsService.createNewGeoCashe(geoCashe)) resp.setStatus(200);
        else resp.setStatus(206);
    }
}
