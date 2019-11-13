package eggroll.apcsa.blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Blackjack {
	Deck deck; // The deck for the game.
	Human ply; // The human for the game.
	Dealer dealer; // The dealer for the game.
	Scanner scanner = new Scanner( System.in );
	
	public Blackjack( ) { // Constructor
	}
	
	public void askForAnotherRound( ) { // At the end of a round, ask if the human wants to play another round.
		System.out.print( "Would you like to play another round? (y/n) " );
		String play = null;
		
		do { // Check for a valid input and try again if invalid.
			try {
				if ( play != null ) { // Check if they're on the second time around.
					System.out.print( "Invalid input. Enter y or n. " );
				}
				
				play = scanner.nextLine( );
			} catch( InputMismatchException error ) {
				System.out.println( "Invalid input. Enter y or n." );
			}
		} while ( !play.toLowerCase( ).equals( "y" ) && !play.toLowerCase( ).equals( "n" ) );
		
		if ( play.toLowerCase( ).equals( "n" ) ) {
			System.out.println( "Okay bye!" );
			return;
		}
		
		startRound( ); // Start a new round.
	}
	
	public void startRound( ) {
		deck = new Deck( );
		ply = new Human( deck );
		dealer = new Dealer( deck );
		System.out.println( "The dealer drew a(n) " + dealer.getCard( 0 ) + ". Current hand value: " + dealer.getTotalHandValue( ) );
		System.out.println( "You drew a(n) " + ply.getCard( 0 ) + " and a(n) " + ply.getCard( 1 ) + ". Current hand value: " + ply.getTotalHandValue( ) );
		String choice = null;
		
		do { // Loops while the human hits.
			System.out.print( "Would you like to hit or stand? (hit/stand) " );
			
			do { // Check for a valid input and try again if invalid.
				try {
					if ( choice != null && !choice.equals( "hit" ) && !choice.equals( "stand" ) ) { // Check if they made an invalid input.
						System.out.print( "Invalid input. Enter hit or stand. " );
					}
					
					choice = scanner.nextLine( );
				} catch( InputMismatchException error ) {
					System.out.println( "Invalid input. Enter hit or stand." );
				}
			} while ( !choice.toLowerCase( ).equals( "hit" ) && !choice.toLowerCase( ).equals( "stand" ) );
			
			if ( choice.toLowerCase( ).equals( "hit" ) ) { // If the player hits, tell them what they drew and their total hand value.
				String card = ply.hit( );
				System.out.println( "You drew a(n) " + card + ". Current hand value: " + ply.getTotalHandValue( ) );
				
				if ( ply.getTotalHandValue( ) > 21 ) {
					System.out.println( "Player bust. You lost." ); // The human lost because they bust. :(
					askForAnotherRound( ); // Ask the human for another round.
					
					return;
				}
			}
		} while ( choice.equals( "hit" ) );
		
		// Onto the dealer's turn.
		
		String card = dealer.revealSecondCard( ); // Draw the second card and reveal it.
		System.out.println( "The dealer reveals a(n) " + card + ". Current hand value: " + dealer.getTotalHandValue( ) );
		String dealersChoice = null;
		
		do { // Loops while the dealer hits.
			dealersChoice = dealer.makeChoice( ); // Have the dealer calculate their choice. <17 card value = hit, >=17 card value = stand.
			System.out.println( "The dealer " + dealersChoice + ". Current hand value: " + dealer.getTotalHandValue( ) );
		} while ( dealersChoice.substring( 0, 3 ).equals( "hit" ) ); // Eek, the substring is b/c the dealer's choice includes the card type.
		
		if ( dealer.getTotalHandValue( ) > 21 ) {
			System.out.println( "Dealer bust. You win." ); // The dealer bust, so the human wins! :)
			askForAnotherRound( ); // Ask the human for another round.
			
			return;
		}
		
		if ( ply.getTotalHandValue( ) > dealer.getTotalHandValue( ) ) { // Check who wins if no one bust.
			System.out.println( "Player wins." );
		} else if ( dealer.getTotalHandValue( ) > ply.getTotalHandValue( ) ) {
			System.out.println( "Dealer wins." );
		} else {
			System.out.println( "Player and dealer tie." );
		}
		
		askForAnotherRound( ); // Ask the human for another round.
	}
	
	public void playGame( ) { // Called by the Main class.
		System.out.println( "Welcome to blackjack!" );
		System.out.println( );
		System.out.print( "Would you like to play a round of blackjack? (y/n) " );
		String play = null;
		
		do { // Check for a valid input and try again if invalid.
			try {
				if ( play != null ) { // Check if they're on the second time around.
					System.out.print( "Invalid input. Enter y or n. " );
				}
				
				play = scanner.nextLine( );
			} catch( InputMismatchException error ) {
				System.out.println( "Invalid input. Enter y or n." );
			}
		} while ( !play.toLowerCase( ).equals( "y" ) && !play.toLowerCase( ).equals( "n" ) );
		
		if ( play.toLowerCase( ).equals( "n" ) ) { // Aww, the player said no. :(
			System.out.println( "Okay bye!" );
			return;
		}
		
		System.out.println( );
		startRound( ); // Starts the round.
	}
}
