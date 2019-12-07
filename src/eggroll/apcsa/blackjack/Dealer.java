package eggroll.apcsa.blackjack;

public class Dealer extends Player {
	public Dealer( Deck deck ) { // Constructor
		super( deck ); // Call the Player class' constructor.
		hand.add( deck.drawRandom( ) ); // Drawing/adding first card to hand. Deferring second draw.
	}
	
	public String revealSecondCard( ) { // Draws the second card, adds it to the hand, and returns the human readable form of it. Returns a String - ace, 2-10, face card.
		Card card = deck.drawRandom( );
		hand.add( card );
		int cardType = card.getType( );
		
		return cardTypeToHumanReadable( cardType );
	}
	
	public String makeChoice( ) { // Calculates whether the dealer should hit or stand.
		int totalHandValue = getTotalHandValue( );
		
		if ( totalHandValue < 17 ) { // If the total hand value is less than 17, the dealer must hit.
			Card card = deck.drawRandom( );
			hand.add( card );
			int cardType = card.getType( );
			
			return "hits and draws a(n) " + cardTypeToHumanReadable( cardType );
		} else { // If the total hand value is more than or equal to 17, the dealer must stand.
			return "stands";
		}
	}
}
