package eggroll.apcsa.blackjack;

public class Card {
	private String suit; // Heart, Diamond, Club, Spade
	private int type; // 1 for ace, 2-10, 11 for face card
	
	public Card( ) { // Constructor
	}
	
	public String getSuit( ) { // Gets the suit of the card. Returns a String - Heart, Diamond, Club, Spade.
		return suit;
	}
	
	public void setSuit( String suit ) { // Sets the suit of the card. (String - Heart, Diamond, Club, Spade)
		this.suit = suit;
	}
	
	public int getType( ) { // Gets the type of the card. Returns an int - 1 for ace, 2-10, 11 for face card.
		return type;
	}
	
	public void setType( int type ) { // Sets the type of the card. (int - 1 for ace, 2-10, 11 for face card)
		this.type = type;
	}
}
