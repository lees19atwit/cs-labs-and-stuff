
public class Manager {
	
	static private Manager _instance = null; 
	private int x; 
	
	private Manager()
	{
		
	}

	static public Manager instance()
	{
		if(_instance == null)
		{
			_instance = new Manager(); 
		}
		
		return _instance; 
	}
	
	public void setData(int x)
	{
		this.x = x; 
	}
	
}
