
public class MutableObject<T> 
{
	private T value; 
	
	public MutableObject(T v)
	{
		value = v; 
	}
	
	public MutableObject()
	{
		this(null); 
	}
	
	public String toSring()
	{
		return value.toString(); 
	}
	
	public T get()
	{
		return value; 
	}
	
	
	public void set(T v)
	{
		value = v; 
	}
	
}
