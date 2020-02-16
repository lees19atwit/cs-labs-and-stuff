
public class Main {

	public static void main(String[] args)
	{
		Manager m = Manager.instance(); 
		Manager m1 = Manager.instance(); 
		System.out.println(m1 == m);
	}
}
