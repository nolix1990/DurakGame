package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Player {
	private String name;
	protected Vector<Card> cards;
	
	//Constructor 
	public Player(){
		this.cards = new Vector<Card>();
	}
	//Constructor
	public Player(String name){
		this.name = name;
		this.cards = new Vector<Card>(); 
	}
	

	


	
	/**
	 * function that adds card to player hand
	 * @param c
	 */
	public void addCard(Card c){
		cards.add(c);
	}
	
	/**
	 * function that removes cards from player deck
	 * @param c
	 */
	public void removeCard(Card c){
		cards.add(c);
	}
	
	/**
	 * function that returns all player cards
	 * @return player cards vector
	 */
	public Vector<Card> getCards() {
		return cards;
	}
	
	
}