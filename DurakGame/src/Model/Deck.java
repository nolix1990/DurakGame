package Model;

import java.util.*;

public class Deck {
	public Vector<Card> deck; //game deck
	public Deck(){
		deck = new Vector<>(); 
		//creating game deck 9*4 from any number and shape
		for(int i=6 ; i<=14 ; i++){
			deck.add(new Card(i, Shape.HEART));
			deck.add(new Card(i,Shape.DIAMOND));
			deck.add(new Card(i, Shape.CLUB));
			deck.add(new Card(i, Shape.SPADE));
		}	
		Collections.shuffle(deck); //shuffle game deck
	}
	
	/**
	 * function that returns game deck size
	 * @return game deck size
	 */
	public int getSize(){
		return deck.size();
	}
	
	/**
	 * function that gets card from game deck to 1 of players and remove the card from game deck
	 * @return card from game deck
	 */
	public Card getcard(){
		if (getSize()>0){
			Card c = deck.get(0);
			deck.remove(0);
			return c;
		}
		else //if game deck is zero
			return null;
	}
	
	/**
	 * function that add card to game deck
	 * @param card
	 */
	public void addCard(Card card) {
		deck.add(card);
	}	   
}
