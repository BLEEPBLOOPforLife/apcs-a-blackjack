package eggroll.apcsa.blackjack;

import java.util.ArrayList;

public abstract class Player {
	protected ArrayList< Card > hand; // ArrayList for cards in hand.
	protected Deck deck; // Deck for the game.
	
	public Player( Deck deck ) { // Constructor
		this.deck = deck; // Sets the deck instance to be what was supplied as the argument.
		hand = new ArrayList< Card >( ); // Creates the array of cards for the hand.
	}
	
	public String cardTypeToHumanReadable( int cardType ) { // Turns card type to a human readable format. Returns a String - ace, 2-10, face card. (int - 1 -> ace, 2 -> "2"... 10 -> "10", 11 -> "face card")
		if ( cardType == 1 ) { // Ace - 1
			return "ace";
		} else if ( cardType >= 2 && cardType <= 10 ) { // Number card - 2-10
			return Integer.toString( cardType );
		} else { // Face card - cardType == 11
			return "face card";
		}
	}
	
	public String getCard( int index ) {
		int cardType = hand.get( index ).getType( );
		
		return cardTypeToHumanReadable( cardType );
	}
	
	public int getTotalHandValue( ) { // Adds up total hand value. Includes ace conversions. Returns an int - total hand value.
		int totalValue = 0; // Holds the total value. This is what is being added to and is what is being returned.
		int aces = 0; // Holds the number of aces the human has. Number of aces will be calculated later.
		
		for ( int i = 0; i < hand.size( ); i++ ) { // Adds up number/face cards and puts aces in a separate variable.
			int cardType = hand.get( i ).getType( );
			
			if ( cardType == 1 ) { // Ace
				aces += 1;
			} else if ( cardType >= 2 && cardType <= 10 ) { // Number card
				totalValue += cardType;
			} else if ( cardType == 11 ) { // Face card
				totalValue += 10;
			}
		}
		
		// Below code adds up aces separately.
		
		if ( totalValue + aces >= 21 ) { // Checks if the aces with them being worth one and the current value combined add up to 21.
			totalValue += aces;
		} else { // Could be room for one or more aces to be worth 11.
			for ( int i = aces; i >= 0; i-- ) { // Tries aces starting with all of them being worth 11.
				int tryValue = totalValue + i * 11 + aces - i; // Calculates attempted total value with the number of aces being worth 11 specified by i.
				
				if ( tryValue <= 21 ) { // Checks if the attempted total value is less than or equal to 21, and if so, sets the value of totalValue to the attempted total value and breaks from the loop.
					totalValue = tryValue;
					break;
				}
			}
		}
		
		return totalValue;
	}
}
