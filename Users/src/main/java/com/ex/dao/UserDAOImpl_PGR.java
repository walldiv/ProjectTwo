package com.ex.dao;

import com.ex.databaseUtils.HibernateUtil;
import com.ex.model.Address;
import com.ex.model.PhoneCarrier;
import com.ex.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
/***
 * This class contains methods to read and write user data to the database, as well as a way to log a user into the system.
 *
 * @author Daniel Wallace
 */

public class UserDAOImpl_PGR implements UserDAO {

    /***
     * This method logs a user into the system.
     * @param user
     * @throws Exception
     */

    @Override
    public User loginUser(User user) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String hql = "FROM User E WHERE E.email = :email AND E.password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("email",user.getEmail());
        query.setParameter("password", user.getPassword());
        List<User> results = query.list();

//        System.out.println(query.list().size());
//
//        for(User e: results){
//            System.out.println(e.getFirstname());
//        }
        //Close session & shutdown Hibernate
        //If the results is > 1 or 0 - throw exception and cancel update.  Otherwise proceed
        if(results.size() > 1 ) {
            System.out.println("UserDAOImpl_PGR::updateUser() - ERROR - results are more than expected (1)");
//            throw new Exception("UserDAOImpl_PGR::updateUser() - ERROR - results are more than expected (1)");
            return user;
        } else if (results.size() <= 0 ) {
            System.out.println("UserDAOImpl_PGR::updateUser() - ERROR - results list is empty");
//            throw new Exception("UserDAOImpl_PGR::updateUser() - ERROR - results are more than expected (1)");
            return user;
        } else {
            System.out.println("DAOIMPL - USER: " + user.toString());
            closeHibernateSession(session);
            return results.get(0);
        }
    }

    /***
     * This method adds a user to the system.
     * @param user
     * @throws Exception
     */

    @Override
    public void addUser(User user) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

//        System.out.println("PHONECARRIERID: " + user.getPhonecarrierid().getPhoneCarrierID());
        final int carrierId = user.getPhonecarrierid().getPhoneCarrierID();
        PhoneCarrier carrier = session.get(PhoneCarrier.class, carrierId);

        //Hibernate - persist & add User & Address to the database
        User tempUser = user;
        tempUser.setPhonecarrierid(carrier);     //This was needed to make PhoneCarrier object transient - DAN WALLACE
        Address tempAddress = user.getAddressid();
        session.saveOrUpdate(carrier);
        session.persist(tempAddress);
        session.save(tempUser);

        //Close session & shutdown Hibernate
        closeHibernateSession(session);
    }
    /***
     * This method fetches a user from the system.
     * @param user
     * @throws Exception
     */

    @Override
    public User displayUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String hql = "FROM User u WHERE u.email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", user.getEmail());
        List<User> results = query.list();

        //DEBUG INFO
//        System.out.println(query.list().size());
//        for(User e: results){
//            System.out.println(e.getFirstname());
//        }
        //Close session & shutdown Hibernate
        closeHibernateSession(session);
        if(results.size() > 1) {
            System.out.println("UserDAOImpl::displayUser() - ERROR - USERS RETURNED IS MORE THAN EXPECTED");
            return user;
        } else if(results.size() <= 0) {
            System.out.println("UserDAOImpl::displayUser() - ERROR - USERS RETURNED IS NULL/EMPTY");
            return user;
        } else {
            User thisUser = results.get(0);
            System.out.println(thisUser.toString());
            return thisUser;
        }
    }
    /***
     * This method updates a user's information in the system.
     * @param targetUser - the user to be updated
     * @param newUserInformation - the user incorporating the updated information
     * @throws Exception
     */

    @Override
    public void updateUser(User targetUser, User newUserInformation) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        System.out.println("TARGET USER ID: " + targetUser.getEmail());

        //Get the current user information from DBase & update to newUserInformation
        String hql = "FROM User U WHERE U.email = :userEmail";
        Query query = session.createQuery(hql);
        query.setParameter("userEmail", targetUser.getEmail());
        List<User> results = query.list();

        //If the results is > 1 or 0 - throw exception and cancel update.  Otherwise proceed
        if(results.size() > 1 ) {
            System.out.println("UserDAOImpl_PGR::updateUser() - ERROR - results are more than expected (1)");
            throw new Exception("UserDAOImpl_PGR::updateUser() - ERROR - results are more than expected (1)");
        } else if (results.size() <= 0 ) {
            System.out.println("UserDAOImpl_PGR::updateUser() - ERROR - results list is empty");
            throw new Exception("UserDAOImpl_PGR::updateUser() - ERROR - results are more than expected (1)");
        } else {
            User tmp = results.get(0);
            tmp = newUserInformation;
            System.out.println(tmp.toString());

            final int carrierId = tmp.getPhonecarrierid().getPhoneCarrierID();
            PhoneCarrier carrier = session.get(PhoneCarrier.class, carrierId);
            if(carrier == null) {
                throw new Exception ("UserDAOImpl_PGR::updateUser() - ERROR - PhoneCarrier read from DBase is null");
            }
            tmp.setPhonecarrierid(carrier);
//            session.saveOrUpdate(tmp.getPhonecarrierid());   //This isnt needed for some reason but is in other places.... HIBERNATE FTW
            session.update(tmp);
        }

        //Close session & shutdown Hibernate
        closeHibernateSession(session);
    }

     /***
     * This method disables a user, .
     * @param user - the user to disable
     * @param bIsDisabled - boolean, describing the activation status of user
     * @throws Exception
     */

    @Override
    public boolean disableUser(User user, boolean bIsDisabled) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

//        user.setInactiveUser(bIsDisabled);        /* this really means nothing here.  we are always setting user's inactiveUser
//         variable to true.... which defeats the purpose of the passed boolean bIsDisabled */

        /* This following code isnt writing to the database - though i would EXPECT it to ... were gonna
        try a new hibernate functionaliity with session.update.
//         */
//        String hql = "UPDATE User set inactive_user = :inactive_user "  +
//                "WHERE email = :email";
//        Query query = session.createQuery(hql);
//        query.setParameter("inactive_user", bIsDisabled);
//        query.setParameter("email", user.getEmail());
//        int result = query.executeUpdate();
////        System.out.println("Rows affected: " + result);
//

        String hql = "FROM User U WHERE U.email = :userEmail";
        Query query = session.createQuery(hql);
        query.setParameter("userEmail", user.getEmail());
        List<User> results = query.list();

        //If the results is > 1 or 0 - throw exception and cancel update.  Otherwise proceed
        if(results.size() > 1 ) {
            System.out.println("UserDAOImpl_PGR::updateUser() - ERROR - results are more than expected (1)");
            throw new Exception("UserDAOImpl_PGR::updateUser() - ERROR - results are more than expected (1)");
        } else if (results.size() <= 0 ) {
            System.out.println("UserDAOImpl_PGR::updateUser() - ERROR - results list is empty");
            throw new Exception("UserDAOImpl_PGR::updateUser() - ERROR - results list is empty");
        } else {
            User tmp = results.get(0);
            tmp.setInactiveUser(bIsDisabled);
            session.update(tmp);
            session.getTransaction().commit();
            HibernateUtil.shutdown();
            return true;
        }
    }
    /***
     * This method closes the current hibernate session
     * @param session
     */

    public void closeHibernateSession(Session session) {
        //Close session & shutdown Hibernate
        session.getTransaction().commit();
//        HibernateUtil.shutdown();
    }
}
