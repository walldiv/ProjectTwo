package com.ex.dao;

import com.ex.databaseUtils.HibernateUtil;
import com.ex.model.GeoCashe;
import com.ex.model.GeoCasheHistorys;
import com.ex.model.Item;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/***
 * This class contains methods to read and write item, geocache, and geocache history data to the database.
 *
 * @author Jordan Severance
 */
public class GpsDAOImpl_PGR implements GpsDAO {
    private HibernateUtil hibernateUtil;

    public GpsDAOImpl_PGR(){hibernateUtil = new HibernateUtil();}

    /***
     * This method adds/saves a geocache to the database. It does not add an item. The item id should be set to null or reference an existing item id.
     * @param geoCashe
     */
    @Override
    public void addCashe(GeoCashe geoCashe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            session.persist(geoCashe);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /***
     *
     * This method places an item in a geocache. It uses geocacheID to find the correct cache and then associates the
     * item with the cache by setting the geocashe's item id to the item's id passed as a parameter. This method then
     * adds the geocache id and email to geocacheHistorys.
     * @param item
     * @param casheHistorys
     */
    @Override
    public void placeItem(Item item, GeoCasheHistorys casheHistorys) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //casheHistorys gives us the cache (wrongly named getItemID)
        //we set the item to using setItemID
        GeoCasheHistorys history = casheHistorys;
        GeoCashe cashe = history.getItemID();
        cashe.setItemID(item);
        history.setItemID(cashe);

        try{
            session.saveOrUpdate(cashe);
            session.persist(history);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /***
     * This method updates the item id in the geocache table to a null value.
     * @param casheHistorys
     */
    @Override
    public void removeItem(GeoCasheHistorys casheHistorys) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //set the item to null, thereby making the cashe empty
        GeoCasheHistorys history = casheHistorys;
        GeoCashe cashe = history.getItemID();
        cashe.setItemID(null);
        history.setItemID(cashe);
        try{
            session.saveOrUpdate(cashe);
            session.persist(history);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

//    public void deleteItem(int id){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//
//
//        String hql = "delete from Item i where i.id = :itemID";
//        Query query = session.createQuery(hql);
//        query.setParameter("itemID",id);
//        query.executeUpdate();
//        session.getTransaction().commit();
//
//    }

    /**
     * This method adds/saves an item to the data base.
     * @param item
     */
    @Override
    public void addItem(Item item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            session.persist(item);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /***
     * This method returns and ArrayList of all the geocaches in the database.
     * @return A List of all geocaches in the system. this data will be filtered by the frontend
     */
    @Override
    public List<GeoCashe> getAllCashes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<GeoCashe> all = new ArrayList<>();
        try{
            String hql = "From GeoCashe";
            Query query = session.createQuery(hql);
            all = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return(all);
    }

    /***
     * This method retrieves/reads a geocache from the database that matches the id passed to it.
     * @param id
     * @return A single instance of a geocache, specified by the id
     */
    @Override
    public GeoCashe findCasheByID(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        GeoCashe tmp = new GeoCashe();
        try{
            String hql = "From GeoCashe g where g.id= :geoCacheID";
            Query query = session.createQuery(hql);
            query.setParameter("geoCacheID",id);
            List<GeoCashe> tmpList = query.getResultList();
            for(GeoCashe cashe : tmpList){
                tmp.setImageurl(cashe.getImageurl());
                tmp.setLat(cashe.getLat());
                tmp.setLng(cashe.getLng());
                tmp.setDifficultyLevel(cashe.getDifficultyLevel());
                tmp.setItemID(cashe.getItemID());
                tmp.setGeoCasheID(cashe.getGeoCasheID());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tmp;
    }

    /***
     * This method retrieves/reads an item from the database that matches the id passed to it.
     * @param id
     * @return A single instance of an item, specified by the id
     */
    @Override
    public Item findItemByID(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Item tmp = new Item();
        try{
            String hql = "From Item g where g.id= :itemID";
            Query query = session.createQuery(hql);
            query.setParameter("itemID",id);
            List<Item> tmpList = query.getResultList();
            for(Item item : tmpList){
                tmp.setImage(item.getImage());
                tmp.setItemID(item.getItemID());
                tmp.setName(item.getName());
                tmp.setDescription(item.getDescription());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tmp;
    }

}
