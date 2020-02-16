import java.util.Scanner;

public class House extends GenericPlayer{

	public House(String name) {
		super(name);
	}

	@Override
	public boolean isHitting(Scanner input) {
		int value=getValue();
		if(value>16 || isBusted()) {
			return false;
		} else {
			return true;
		}
	}
	
	public void flipFirstCard() {
		cards.get(0).flip();
	}
	
	
	
}
