package beans;

public class Request {
	private int reim_id;
	private int user_id;
	private int resolved;
	private String timeStamp;
	private int amount;
	
	public Request()
	{
		
	}
	public Request(int reim, int user, int res, String ts, int amt)
	{
		super();
		this.reim_id = reim;
		this.user_id = user;
		this.resolved = res;
		this.timeStamp = ts;
		this.amount = amt;
	}
	public Request(int reim, int user, int res, String ts)
	{
		super();
		this.reim_id = reim;
		this.user_id = user;
		this.resolved = res;
		this.timeStamp = ts;
	}
	
	public int getRID()
	{
		return this.reim_id;
	}
	
	public void setRID(int rid)
	{
		this.reim_id = rid;
	}
	
	public int getUID()
	{
		return this.user_id;
	}
	
	public void setUID(int uid)
	{
		this.user_id = uid;
	}
	
	public int getResolved()
	{
		return this.resolved;
	}
	
	public void setResolved(int res)
	{
		this.resolved = res;
	}
	
	public String getTimeStamp()
	{
		return this.timeStamp;
	}
	
	public void setTimeStamp(String ts)
	{
		this.timeStamp = ts;
	}
	
	public int getAmount()
	{
		return this.amount;
	}
	
	public void setAmount(int amt)
	{
		this.amount = amt;
	}
}
