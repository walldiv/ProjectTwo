package com.ex.servlets;

import com.ex.model.GeoCashe;
import com.ex.model.GeoCasheHistorys;
import com.ex.services.GpsService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/***
 * This servlet class is to process requests to remove items from geocaches.
 *
 * @author Jordan Severance
 */
//@WebServlet("/GeoCacheRemoveItem")
//SERVLET MAPPING URL= /removeitem
public class GeoCacheRemoveItem extends HttpServlet {
    /***
     * @param req- HTTP request: this should contain the geocacheID, user email, comment, and rating
     * @param resp - HTTP response: this will be a boolean describing the success of the transaction
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GpsService service = new GpsService();

        //info from request
        int geocacheID = Integer.parseInt(req.getParameter("geocacheID"));
        String email = req.getParameter("email");
        String comment = req.getParameter("comment");
        int rating = Integer.parseInt(req.getParameter("rating"));
        GeoCashe cashe = service.findCasheByID(geocacheID);

        //make a GeoCasheHistory object to log this
        GeoCasheHistorys history = new GeoCasheHistorys(email,cashe, LocalDateTime.now(),comment,rating);
        boolean success = service.removeItem(history);

        resp.setStatus(success ? 200 : 400);
        resp.getOutputStream().print(new Gson().toJson(success));
    }
}
