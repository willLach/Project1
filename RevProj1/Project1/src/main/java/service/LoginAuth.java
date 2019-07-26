package service;

import beans.User;
import dao.UserDAO;
import dbDAOImpl.UserDAOImpl;

public class LoginAuth {

	public UserDAO userimpl = new UserDAOImpl();
	public boolean authUser(String u)
	{
		if(userimpl.getUserByUsername(u) != null)
			{
				return true;
			}
		return false;
	}
	public boolean isManager(String u)
	{
		System.out.println(userimpl.getUserByUsername(u).getEmpType());
		if(userimpl.getUserByUsername(u).getEmpType().equals("manager"))
		{
			System.out.println("it worked");
			return true;
		}
		return false;
	}
}
