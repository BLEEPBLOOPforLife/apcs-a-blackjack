package eggroll.apcsa.blackjack;

import java.util.Scanner;

public class Blackjack {
	Deck deck; // The deck for the game.
	Human ply; // The human for the game.
	Dealer dealer; // The dealer for the game.
	Scanner scanner = new Scanner( System.in );
	
	public Blackjack( ) { // Constructor
	}
	
	public boolean checkForPlayInput( ) { // Internal method to use the scanner to ask if the player wants to play.
		String play = null;
		
		do { // Check for a valid input and try again if invalid.
			if ( play != null ) { // Check if they're on the second time around.
				System.out.print( "Invalid input. Enter y or n. " );
			}
			
			play = scanner.nextLine( );
		} while ( !play.toLowerCase( ).equals( "y" ) && !play.toLowerCase( ).equals( "n" ) );
		
		if ( play.toLowerCase( ).equals( "y" ) ) { // Player said no.
			return true;
		} else {
			System.out.println( "Okay bye!" );
			
			return false;
		}
	}
	
	public void askForRoundAndStart( ) { // Ask for a round.
		System.out.print( "Would you like to play a round of blackjack? (y/n) " );
		boolean playInput = checkForPlayInput( );
		System.out.println( );
		
		if ( playInput ) {
			startRound( );
		}
	}
	
	public void askForAnotherRoundAndStart( ) { // At the end of a round, ask if the human wants to play another round.
		System.out.print( "Would you like to play another round? (y/n) " );
		boolean playInput = checkForPlayInput( );
		
		if ( playInput ) {
			startRound( );
		}
	}
	
	public boolean loopHumanDecision( ) { // Will return whether the player can continue or busted.
		String choice;
		
		do { // Loops while the human hits.
			choice = null;
			System.out.print( "Would you like to hit or stand? (hit/stand) " );
			
			do { // Check for a valid input and try again if invalid.
				if ( choice != null ) { // Second time around.
					System.out.print( "Invalid input. Enter hit or stand. " );
				}
				
				choice = scanner.nextLine( );
			} while ( !choice.toLowerCase( ).equals( "hit" ) && !choice.toLowerCase( ).equals( "stand" ) );
			
			if ( choice.toLowerCase( ).equals( "hit" ) ) { // If the player hits, tell them what they drew and their total hand value.
				String card = ply.hit( );
				System.out.println( "You drew a(n) " + card + ". Current hand value: " + ply.getTotalHandValue( ) );
				
				if ( ply.getTotalHandValue( ) > 21 ) {
					System.out.println( "Player bust. You lost." ); // The human lost because they bust. :(
					askForAnotherRoundAndStart( ); // Ask the human for another round.
					
					return false;
				}
			}
		} while ( choice.equals( "hit" ) );
		
		return true;
	}
	
	public boolean loopDealerDecision( ) { // Will return whether the dealer can continue or busted.
		String dealersChoice = null;
		
		do { // Loops while the dealer hits.
			dealersChoice = dealer.makeChoice( ); // Have the dealer calculate their choice. <17 card value = hit, >=17 card value = stand.
			System.out.println( "The dealer " + dealersChoice + ". Current hand value: " + dealer.getTotalHandValue( ) );
		} while ( dealersChoice.substring( 0, 3 ).equals( "hit" ) ); // Eek, the substring is b/c the dealer's choice includes the card type.
		
		if ( dealer.getTotalHandValue( ) > 21 ) {
			System.out.println( "Dealer bust. You win." ); // The dealer bust, so the human wins! :)
			askForAnotherRoundAndStart( ); // Ask the human for another round.
			
			return false;
		}
		
		return true;
	}
	
	public int checkWin( ) { // 1 = player, 2 = dealer, 3 = tie.
		int plyHand = ply.getTotalHandValue( );
		int dealerHand = dealer.getTotalHandValue( );
		
		if ( plyHand > dealerHand ) { // Check who wins if no one bust.
			return 1;
		} else if ( dealerHand > plyHand ) {
			return 2;
		} else {
			return 3;
		}
	}
	
	public void startRound( ) {
		// Initialize deck, player, and dealer.
		
		deck = new Deck( );
		ply = new Human( deck );
		dealer = new Dealer( deck );
		
		// Draw the initial cards.
		
		System.out.println( "The dealer drew a(n) " + dealer.getCard( 0 ) + ". Current hand value: " + dealer.getTotalHandValue( ) );
		System.out.println( "You drew a(n) " + ply.getCard( 0 ) + " and a(n) " + ply.getCard( 1 ) + ". Current hand value: " + ply.getTotalHandValue( ) );
		
		// Have the human make their decision.
		
		if ( !loopHumanDecision( ) ) {
			return; // Player busted.
		}
		
		// Reveal the dealer's second card.
		
		String card = dealer.revealSecondCard( ); // Draw the dealer's second card and reveal it.
		System.out.println( "The dealer reveals a(n) " + card + ". Current hand value: " + dealer.getTotalHandValue( ) );
		
		// Have the dealer make their decision.
		
		if ( !loopDealerDecision( ) ) {
			return; // Dealer busted.
		}
		
		// Check who won if no one busted.
		
		int checkWin = checkWin( );
		
		if ( checkWin == 1 ) { // Player win.
			System.out.println( "Player wins." );
		} else if ( checkWin == 2 ) { // Dealer win.
			System.out.println( "Dealer wins." );
		} else { // Player and Dealer tie.
			System.out.println( "Player and dealer tie." );
		}
		
		// Ask the human for another round and start.
		
		askForAnotherRoundAndStart( );
	}
	
	public void playGame( ) { // Called by the Main class.
		System.out.println( "Welcome to blackjack!" );
		System.out.println( );
		askForRoundAndStart( ); // Ask the human for a round and start.
	}
}
