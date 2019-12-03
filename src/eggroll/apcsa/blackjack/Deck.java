package eggroll.apcsa.blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList< Card > deck; // Holds all 52 cards of a standard deck.
	Random rand = new Random( );
	
	public Deck( ) { // Constructor
		deck = new ArrayList< Card >( ); // Creates the array of cards for the deck.
		InitDeck( );
	}
	
	public void MakeCard( String suit, int type ) { // Creates a new instance of a card based on the args provided. (String - Heart, Diamond, Club, Spade, int - 1 -> ace, 2 -> "2"... 10 -> "10", 11 -> "face card")
		Card card = new Card( );
		card.setSuit( suit );
		card.setType( type );
		deck.add( card ); // Adds card to the deck.
	}
	
	public void InitDeck( ) { // Creates the instances of each of the 52 cards and stores them into the deck.
		String[ ] suits = { "Heart", "Diamond", "Club", "Spade" }; // Four suits in a standard 52 card deck.
		int[ ] types = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 11, 11 }; // 1 ace, 9 number cards, 3 other face cards per suit in a standard 52 card deck.
		
		for ( String suit : suits ) { // Iterate through each suit.
			for ( int type : types ) { // Iterate through each card type.
				MakeCard( suit, type ); // Add the card generated to the deck.
			}
		}
	}
	
	public Card drawRandom( ) { // Drawing over the deck limit will result in an error because there are no more cards left. Draws a random card from the deck and removes it from the deck. Returns a Card - the card it drew.
		int randomIndex = rand.nextInt( deck.size( ) );
		return deck.remove( randomIndex );
	}
}
