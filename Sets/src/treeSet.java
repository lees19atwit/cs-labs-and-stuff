import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class treeSet 
{
	public static void p(Set<?> s)
	{
		for(Object o : s)
			System.out.printf(" %s", o);
		System.out.printf("%n");
	}
	
	public static void main(String[] args)
	{
		//putting negative in front of o1.compareTo(o2), it makes the order backwards
		//final Set<String> s = new TreeSet<>( (o1, o2) -> -o1.compareTo(o2) );
		
		final Set<String> s = new TreeSet<>( (o1, o2) -> o1.compareTo(o2) ); 
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
