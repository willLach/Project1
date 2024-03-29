package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Request;
import dao.RequestDAO;
import dbDAOImpl.RequestDAOImpl;
public class RequestServlet extends HttpServlet{

	private RequestDAO requestTable = new RequestDAOImpl();
	@Override
	public void init() throws ServletException
	{
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//System.out.println("help");
		List<Request> requestList = requestTable.getAllRequests();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(requestList));
		response.getWriter().write(mapper.writeValueAsString(requestList));
		/*
		* Code for getting resolved requests 
		*List<Request> resolvedReqList = requestTable.getResolvedRequests();
		*ObjectMapper mapper = new ObjectMapper();
		*response.getWriter().print(mapper.writeValueAsString(resolvedRequestList));
		* 
		* Code for getting unresolved requests
		*List<Request> unresolvedRequestList = requestTable.getUnresolvedRequests();
		*ObjectMapper mapper = new ObjectMapper();
		*response.getWriter().print(mapper.writeValueAsString(unresolvedRequestList)):
		*/
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		Request req = new Request();
		req = (Request)mapper.readValue(request.getInputStream(), Request.class);
		requestTable.createReimbursement(req);
		response.setStatus(201);
	}
}
