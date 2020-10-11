package com.ex.dao;


import com.ex.model.User;

import java.util.List;

/**
 * This is the UserDAO interface to be implemented amongst DAO classes.
 *
 * @author Daniel Wallace
 */
public interface UserDAO {
    public User loginUser(User user) throws Exception;
    public void addUser(User user) throws Exception;
    public User displayUser(User user);
    public void updateUser(User targetUser, User newUserInformation) throws Exception;
    public boolean disableUser(User user, boolean bIsDisabled) throws Exception;
}
