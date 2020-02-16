import java.util.HashSet;
import java.util.Set;

public class Main 
{
	public static void p(Set<?> s)
	{
		for(Object o : s)
			System.out.printf(" %s", o);
		System.out.printf("%n");
	}
	
	public static void main(String[] args)
	{
		final Set<String> s = new HashSet<>(); 
		s.add("alpha"); 
		p(s); 
		s.add("beta"); 
		p(s); 
		s.add("gamma"); 
		p(s); 
		s.add("delta"); 
		p(s); 
		s.add("alpha"); 
		p(s); 
	}
}
