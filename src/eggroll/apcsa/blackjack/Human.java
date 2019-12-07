package eggroll.apcsa.blackjack;

public class Human extends Player {
	public Human( Deck deck ) { // Constructor
		super( deck ); // Call the Player class' constructor.
		hand.add( deck.drawRandom( ) ); // Drawing/adding first card to hand.
		hand.add( deck.drawRandom( ) ); // Drawing/adding second card to hand.
	}
	
	public String hit( ) { // Executes when the human hits. Returns a String - ace, 2-10, face card.
		Card card = deck.drawRandom( );
		hand.add( card ); // Add the randomly drawn card to their hand.
		int cardType = card.getType( );
		
		return cardTypeToHumanReadable( cardType );
	}
}
