import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for Blackjack game
 * @author schuster
 *
 */
public class Game {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		Deck d = new Deck();
		
		System.out.printf("%nWelcome to BlackJack!%n%n");
		
		System.out.printf("Enter number of players: ");
		//TODO: sanitize input to only be a number.
		int numPlayers=input.nextInt();
		ArrayList<GenericPlayer> players = new ArrayList<>(numPlayers+1);
		
		for(int i=0;i<numPlayers;i++) {
			System.out.printf("Enter player #%d name: ", i+1);
			String name = input.next();
			Player p = new Player(name);
			players.add(p);
		}
		House house = new House("House");
		players.add(house);
		
		
		//deal two cards to all players
		for(int i=0;i<players.size();i++) {
			GenericPlayer p = players.get(i);
			d.deal(p);
			d.deal(p);
			if(p instanceof House) {
				House h = (House)p;
				h.flipFirstCard();
			}
			System.out.printf("%s%n", p);
		}
		
		
		//ask players if they want to hit
		for(int i=0;i<players.size();i++) {
			GenericPlayer p= players.get(i);
			if(p instanceof House) {
				House h= (House)p;
				h.flipFirstCard();
			}
			
			while(p.isBusted()==false) {
				if(p.isHitting(input)) {
					d.deal(p);
					System.out.printf("%s%n", p);
				} else {
					break;
				}
				if(p.getValue()>21) {
					p.busted();
				}
			}//end of hitting while for each player			
		} //ending player hitting for loop
		
		System.out.printf("%n%nFinal Hands:%n");
		for(int i=0;i<players.size();i++) {
			System.out.printf("%s%n", players.get(i));
		}
		System.out.printf("%n%n");
		
		
		//display winners and losers
		int houseValue=house.getValue(); //FIXME do this better
		for(int i=0;i<players.size();i++) {
			GenericPlayer p = players.get(i);
			int playerValue=p.getValue();
			
			if(p instanceof House) {
				continue;
			}
			
			if(p.isBusted()==false) {
				if(houseValue>21) {
					//player wins
					p.win();
				} else if(houseValue==playerValue) {
					//player pushed
					p.push();
				} else if(playerValue>houseValue) {
					//player wins
					p.win();
				} else {
					//player lost
					p.lost();
				}	
			} else {
				//player lost
				p.lost();
			}
			
			
		}
		
	}
}




