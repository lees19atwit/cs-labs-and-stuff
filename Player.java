import java.util.Scanner;

public class Player extends GenericPlayer{

	public Player(String name) {
		super(name);
	}

	@Override
	public boolean isHitting(Scanner input) {
		do {
			System.out.printf("%s: Do you want to hit? (y/n)", name);
			String res = input.next();
			//TODO:check for valid input from next();
			char c=res.toLowerCase().charAt(0);
			if(c=='y') {
				return true;
			}else if(c=='n') {
				return false;
			} else {
				System.out.printf("Please enter y or n%n");
			}
		} while(true);
	}

	
	
	
}
