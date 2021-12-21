package com.lt.crs.business;

import com.lt.crs.dao.LoginUserDAOImpl;
import com.lt.crs.exceptions.UserNotFoundException;

public class LoginUserOperation implements LoginUserInterface{
	com.lt.crs.dao.LoginUserDAOInterface user=null;
public void userLogin(String userId,String userPassword)  {
		user=new LoginUserDAOImpl();
		try {
			user.userLogin(userId, userPassword);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
	
		
		
	}

public void updatePassword(String userID, String oldpw, String newpw) {
	// TODO Auto-generated method stub
	user=new LoginUserDAOImpl();
	try {
		user.updatePassword(userID,oldpw,newpw);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e.getLocalizedMessage());
	}
	
}

}
