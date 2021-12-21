package com.lt.crs.business;

public interface LoginUserInterface {
public void userLogin(String userId,String userPassword) ;

public void updatePassword(String userID, String oldpw, String newpw);
}
