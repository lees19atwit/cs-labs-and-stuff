
public class Main {

	public static void main(String[] args)
	{
		MutableObject<String> o1 = new MutableObject("Hello"); 
		System.out.printf("%s%n", o1);
		o1.set("hi");
		System.out.printf("%s%n", o1);
		
		MutableObject<Integer> o2 = new MutableObject(5); 
		System.out.printf("%s%n", o2);
		o2.set(10);
		System.out.printf("%s%n", o2);
		
	}
}
