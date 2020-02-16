import java.util.ArrayList;

abstract public class CardCollection {
	protected ArrayList<Card> cards;
	
	protected CardCollection(int num) {
		cards=new ArrayList<>(num);
	}
	
	public void addCard(Card c) {
		cards.add(c);
	}
	
	public void removeCard(Card c) {
		cards.remove(c);
	}
}



