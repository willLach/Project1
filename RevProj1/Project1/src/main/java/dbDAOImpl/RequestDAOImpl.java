package dbDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import beans.Request;
import beans.User;
import dao.RequestDAO;
import service.ConnectionFactory;

public class RequestDAOImpl implements RequestDAO{
	public Request createReimbursement(Request re)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "INSERT INTO requests(user_id, resolved, time_stamps, amount) " + "VALUES(?, ?, ?, ?)";
			String[] primaryKeyValues = {"req_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, primaryKeyValues);
			stmt.setInt(1, re.getUID());
			stmt.setInt(2, re.getResolved());
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    Date date = new Date();  
		    System.out.println(formatter.format(date));
			stmt.setString(3, formatter.format(date));
			stmt.setInt(4, re.getAmount());
			
			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			while(keys.next())
			{
				//int reim_id = keys.getInt("req_id");
				//re.setRID(reim_id);
			}
			return re;
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public Request getRequest(int reim_id)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT * FROM reimbursements WHERE reim_id = ?";
			String[] primaryKeyValues = {"req_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, primaryKeyValues);
			stmt.setInt(1, reim_id);
			
			ResultSet results = stmt.executeQuery();
			Request r = null;
			while(results.next())
			{
				int re_id = results.getInt("req_id");
				int u_id = results.getInt("user_id");
				int res = results.getInt("resolved");
				r = new Request(re_id, u_id, res, null);
			}
			return r;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public List<Request> getAllRequestsByUser(User u)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT * FROM reimbursements " + "WHERE user_id = ? ";
			String[] primaryKeyValues = {"req_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, primaryKeyValues);
			stmt.setInt(1, u.getUserId());
			ResultSet results = stmt.executeQuery();
			List<Request> reimList = new ArrayList<Request>();
			while(results.next())
			{
				int reim_id = results.getInt("req_id");
				int u_id = results.getInt("user_id");
				int res = results.getInt("resolved");
				int amount = results.getInt("amount");
				reimList.add(new Request(reim_id, u_id, res, null, amount));
			}
			return reimList;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public List<Request> getAllUnresolvedRequests()
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT * FROM reimbursements " + "WHERE resolved = 0";
			String[] primaryKeyValues = {"req_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, primaryKeyValues);
			ResultSet results = stmt.executeQuery();
			List<Request> unresolvedReimbursementList = new LinkedList<Request>();
			while(results.next())
			{
				int reim_id = results.getInt("req_id");
				int u_id = results.getInt("user_id");
				int res = results.getInt("resolved");
				unresolvedReimbursementList.add(new Request(reim_id, u_id, res, null));
			}
			return unresolvedReimbursementList;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public List<Request> getAllResolvedRequests()
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT * FROM reimbursements WHERE resolved = 1 " + "ORDER BY time_stamps";
			String[] primaryKeyValues = {"req_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, primaryKeyValues);
			ResultSet results = stmt.executeQuery();
			List<Request> resolvedReimbursements = new LinkedList<Request>();
			while(results.next())
			{
				int reim_id = results.getInt("req_id");
				int u_id = results.getInt("user_id");
				int res = results.getInt("resolved");
				int amount = results.getInt("amount");
				resolvedReimbursements.add(new Request(reim_id, u_id, res, null, amount));
			}
			return resolvedReimbursements;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public Request resolveReimbursement(Request r)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "UPDATE reimbursements " + "SET resolved = 1 " + "WHERE req_id = ?";
			String[] primaryKeyValues = {"req_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, primaryKeyValues);
			stmt.setInt(1, r.getRID());
			stmt.executeUpdate();
			return r;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public List<Request> getAllRequests()
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT * " + "FROM requests";
			String[] primaryKeyValues = {"req_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, primaryKeyValues);
			
			ResultSet results = stmt.executeQuery();
			List<Request> requests = new LinkedList<Request>();
			while(results.next())
			{
				int req_id = results.getInt("req_id");
				int u_id = results.getInt("user_id");
				int res = results.getInt("resolved");
				int amount = results.getInt("amount");
				requests.add(new Request(req_id, u_id, res, null, amount));
			}
			return requests;
		}catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
