
public class Hand extends CardCollection{
	public Hand() {
		super(5);
	}
	
	public int getValue() {
		int sum=0;
		for(int i=0;i<cards.size();i++) {
			Card c = cards.get(i);
			if(c.isFlipped()==false) {
				sum+=c.getValue();
			}
			//sum+=cards.get(i).getValue();
		}
		return sum;
	}
	
	@Override
	public String toString() {
		String output="";
		for(int i=0;i<cards.size();i++) {
			output+=String.format("%s ",cards.get(i));
		}
		output+=String.format("(%d)", getValue());
		return output;
	}
}



