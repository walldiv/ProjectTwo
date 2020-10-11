package com.ex.services;


import com.ex.dao.UserDAO;
import com.ex.dao.UserDAOImpl_PGR;
import com.ex.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * The UserServices class is a form of data persistence.
 * methods (CRUD methods) are to be called to change the data tables inside our AWS RDB.
 * It will create, read, update or delete a user to the users table in our RDB.
 * Create user, update user, display user information, delete user, as well as login user.
 * Storing the changes for later use inside the AWS RDB.
 *
 * @author That-Team
 */

public class UserService {
    private UserDAO userDao;

    public UserService() {
        this.userDao = new UserDAOImpl_PGR();
    }

    public UserService(UserDAO dao) {
        this.userDao = dao;
    }

    /**
     * This function allows for immediate hashing of a password input by the user - should
     * be used IMMEDIATELY with no storage of the raw password input from the user.
     * @param password
     * @return - Hash password to be used for login or changing password
     */
    public String hashPassword(String password) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(password.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for(int itr = 0; itr < hashedBytes.length; itr++) {
                byte b = hashedBytes[itr];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }
    /**
     * This function allows a user to log into the system.
     * @param user - the user being logged in
     * @return - null if user is inactive; user data if login is successful
     */

//**************** Check user credentials for login ************************//
    public User loginUser(User user) {

        System.out.println(user.toString());
        User userReturned = user;
        try {
            userReturned = userDao.loginUser(user);
            if(user.isInactiveUser()) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return userReturned;
        }
    }
    /**
     * This function allows a user to be added to the system.
     * @param user
     * @return - true or false, describing the success of adding the user
     */

//**************************** Add a new User ******************************//
    public boolean addUser(User user){
        try{
            userDao.addUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * This function fetches a user.
     * @param user
     * @return - null if the user doesn't exist; user if successful
     */

//*************************** Display current user **************************//
    public User displayUser(User user) {
        System.out.println(user.toString());
        User dUser = user;
        try {
            dUser = userDao.displayUser(user);
            return dUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * This function allows for the updating of a user
     * @param targetUser - the user to be updated
     * @param newUserInformation - contains the information that will be updated
     * @return - true or false, describing the success of updating the user
     */

//************************** Update User Information ***************************//
    public boolean updateUser(User targetUser, User newUserInformation) {
        try{
            userDao.updateUser(targetUser, newUserInformation);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * This function allows for the updating of a user
     * @param user - the user to be disabled
     * @param bIsDisabled - boolean describing the activation status of the user
     * @return - true or false, describing the success of disabling the user
     */

//************************* Disable current user *********************************//
    public boolean disableUser(User user, boolean bIsDisabled) {
        try{
            if (userDao.disableUser(user, bIsDisabled)){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
