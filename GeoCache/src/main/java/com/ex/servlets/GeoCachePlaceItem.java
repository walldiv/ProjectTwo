package com.ex.servlets;

import com.ex.model.GeoCashe;
import com.ex.model.GeoCasheHistorys;
import com.ex.model.Item;
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
 * This servlet class processes the requests to place an item in a geocache.
 *
 * @author Jordan Severance
 */
//@WebServlet("/GeoCachePlaceItem")
//SERVLET MAPPING URL= /placeitem
public class GeoCachePlaceItem extends HttpServlet {
    /***
     * @param req- HTTP request: this should contain an item name, item description, imageurl, as well as
     *           the geocacheID, user email, comment, and rating
     * @param resp - HTTP response: this will be a boolean describing the success of the transaction
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GpsService service = new GpsService();

        //info from request
        //info for the item
        String itemName = req.getParameter("itemName");
        String description = req.getParameter("description");
        String image = req.getParameter("imageurl");
        //info for the transaction
        int geocacheID = Integer.parseInt(req.getParameter("geocacheID"));
        GeoCashe cashe = service.findCasheByID(geocacheID);
        String email = req.getParameter("email");
        String comment = req.getParameter("comment");
        int rating = Integer.parseInt(req.getParameter("rating"));


        //create new Item
        Item item = new Item(itemName,description,image);
        boolean itemSuccess = service.createNewItem(item);

        //put item in geocache
        GeoCasheHistorys history = new GeoCasheHistorys(email,cashe, LocalDateTime.now(),comment,rating);
        boolean success = service.placeItem(item,history);
        int status = success ? 200 : 400;
        resp.setStatus(status);
        resp.getOutputStream().print(new Gson().toJson(success));
        System.out.println("SUCCESS FOR TRANSACTION: " + success);
    }
}
