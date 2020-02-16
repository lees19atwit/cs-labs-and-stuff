
public class Main {

	private static class isEven2 implements I1
	{

		@Override
		public boolean test(int x) {
			// TODO Auto-generated method stub
			return x%2 == 0; 
		}
		
	}
	
	public static void happyOrSad(I1 var)
	{
		for(int i = 0; i < 10; i ++)
		{
			System.out.printf("%d :", i);
			if(var.test(i))
				System.out.println(")");
			else 
				System.out.println("(");
			System.out.printf("%n");
		}
	}
	
	public static void main(String[] args) 
	{
		happyOrSad(p -> p%2 == 0); 
		happyOrSad(new isEven2()); 
		happyOrSad(new I1() 
		{

			@Override
			public boolean test(int x) {
				// TODO Auto-generated method stub
				return x%2 == 0;
			}
			
		}); 
		
		//happyOrSad( (p) -> {p%2 == 0});
		//(p) parameters 
		//-> basically returns
		//p%2 == 0 is the test 
		
		//happyOrSad(new isEven()); 
		//these are the same thing
	}
}
