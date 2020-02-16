import java.util.*; 

public class Example 
{

	public static void main(String[] args)
	{
		Collection<String> c = new ArrayList<>(); 
		c.add("Pineapple"); 
		c.add("Banana"); 
		c.add("Orange"); 
		
		Iterator iter = c.iterator(); 
		while(iter.hasNext()) 
		{
			String s = iter.next().toString(); 
			if(s.contains("an"))
				System.out.printf("%s%n", s);
		}
		System.out.printf("- done%n"); 
		
		LinkedList<Integer> ll = new LinkedList(); 
		
	}
	
}
