package servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.LoginAuth;
public class LoginServlet extends HttpServlet{

	public LoginAuth lAuth = new LoginAuth();
	@Override
	public void init() throws ServletException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//System.out.println("we in there bois");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		//System.out.println(username);
		String password = request.getParameter("password");
		//System.out.println(password);
		if(lAuth.authUser(username)) {
			session.setAttribute("username", username);
			session.setAttribute("problem", null);
			if(lAuth.isManager(username))
			{
				response.sendRedirect("/Project1/manager.html");
			}
			else
			{
				response.sendRedirect("/Project1/employee.html");
			}
		}
		else{
			//System.out.println("invalid user");
			session.setAttribute("problem", "incorrect login info");
			response.sendRedirect("../login.html");
		}
	}
}
