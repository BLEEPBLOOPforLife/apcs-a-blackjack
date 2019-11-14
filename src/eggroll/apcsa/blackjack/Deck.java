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
		for ( int i = 0; i < 52; i++ ) { // 0-12 = hearts, 13-25 = diamonds, 26-38 = clubs, 39-51 = spades
			if ( i == 0 ) { // Ace of hearts
				MakeCard( "Heart", 1 );
			} else if ( i >= 1 && i <= 9 ) { // Number cards of hearts
				MakeCard( "Heart", i + 1 ); // i + 1 = 2 through 10
			} else if ( i >= 10 && i <= 12 ) { // Face cards of hearts
				MakeCard( "Heart", 11 );
			} else if ( i == 13 ) { // Ace of diamonds
				MakeCard( "Diamond", 1 );
			} else if ( i >= 14 && i <= 22 ) { // Number cards of diamonds
				MakeCard( "Diamond", i - 12 );// i - 12 = 2 through 10
			} else if ( i >= 23 && i <= 25 ) { // Face cards of diamonds
				MakeCard( "Diamond", 11 );
			} else if ( i == 26 ) { // Ace of clubs
				MakeCard( "Club", 1 );
			} else if ( i >= 27 && i <= 35 ) { // Number cards of clubs
				MakeCard( "Club", i - 25 ); // i - 25 = 2 through 10
			} else if ( i >= 36 && i <= 38 ) { // Face cards of clubs
				MakeCard( "Club", 11 );
			} else if ( i == 39 ) { // Ace of spades
				MakeCard( "Spade", 1 );
			} else if ( i >= 40 && i <= 48 ) { // Number cards of spades
				MakeCard( "Spade", i - 38 ); // i - 38 = 2 through 10
			} else if ( i >= 49 && i <= 51 ) { // Face cards of spades
				MakeCard( "Spade", 11 );
			}
		}
	}
	
	public Card drawRandom( ) { // Drawing over the deck limit will result in an error because there are no more cards left. Draws a random card from the deck and removes it from the deck. Returns a Card - the card it drew.
		Card card;
		
		int randomIndex = rand.nextInt( deck.size( ) );
		card = deck.get( randomIndex );
		deck.remove( randomIndex );
		
		return card;
	}
}
