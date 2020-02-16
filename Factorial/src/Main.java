import java.util.*; 

public class Main {

	public static void main(String[] args)
	{
		int n = 0; 
		Scanner in = new Scanner(System.in); 
		
		System.out.println("Enter number: ");
		n = in.nextInt(); 
		System.out.println(power(2, 5));
		
	}
	
	public static void printNTimes(int n)
	{
		if(n > 0){
			System.out.println("Mind Blown BEFORE!");
			printNTimes(n-1); 
			System.out.println("Mind Blown AFTER!");
		}
	}
	
	public static int factorial(int n)
	{
		if(n <= 0)
			return 1; 
		else 
			return n * factorial(n-1); 
	}
	
	public static int power(int base, int exponent)
	{
		if(exponent <= 1)
			return base; 
		else 
			return base * power(base, exponent-1); 
	}
	
	public static int fib(int n)
	{
		if(n == 0)
			return 0; 
		else if (n == 1)
			return 1; 
		else 
			return fib(n-1) + fib(n-2); 
	}
}
