package com.ex.dao;

import com.ex.model.GeoCashe;
import com.ex.model.GeoCasheHistorys;
import com.ex.model.Item;

import java.util.List;

/***
 * This interface contains methods to read and write item, geocache, and geocache history data to the database.
 *
 * @author Jordan Severance
 */
public interface GpsDAO {
    public void addCashe(GeoCashe geoCashe);
    public void placeItem(Item item, GeoCasheHistorys casheHistorys);
    public void removeItem(GeoCasheHistorys casheHistorys);
    public void addItem(Item item);
    public List<GeoCashe> getAllCashes();
    public GeoCashe findCasheByID(int id);
    public Item findItemByID(int id);
}
