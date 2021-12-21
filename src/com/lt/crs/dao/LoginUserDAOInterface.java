package com.lt.crs.dao;

import com.lt.crs.exceptions.UserNotFoundException;

public interface LoginUserDAOInterface {
public void userLogin(String userId,String userPassword) throws UserNotFoundException, Exception;

public void updatePassword(String userID, String oldpw, String newpw) throws Exception;
}