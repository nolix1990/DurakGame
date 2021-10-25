package Model;

import java.util.Vector;
import Control.Type;
import View.ComputerBoard;
import View.Game;

public class Computer extends Player implements Runnable{
	private Boolean myturn;
	private ComputerBoard b;
	private boolean attack = false;
	private boolean isRunning = true;
	Game g;
	public Computer(Game g, ComputerBoard b) {
		super();
		this.myturn = false;
		this.b = b;
		this.g = g;
		
	}
	/**
	 * ends the game,close thread
	 */
	public void close(){
		isRunning = false;
	}
	/**
	 * set the turn - true or false
	 * @param myturn - get 
	 */
	public void setMyturn(Boolean myturn) {
		this.myturn = myturn;
	}
	/**
	 * set computer as attack
	 */
	public void setAttack(){
		attack = true;
	}
	/**
	 * set computer as defend
	 */
	public void setDefend(){
		attack = false;
	}
	/**
	 * function to attack based on player move
	 * @return index of card to put on table
	 */
	public int attack(){
		Card c=null; //for the card to attack with
		int i=0, j=0, indexToRemove=0; //index for loop
		//for first attack - if table empty / can put any card from deck if gameboard it empty
		if(g.getBoard().isEmpty()){
			//find minimum card that not kozer / for attack
			for(i=0;i<cards.size();i++){
				if((c==null||c.getNumber()>cards.get(i).getNumber()) && !cards.get(i).getShape().equals(g.getKozerShape())){
					c = cards.get(i);
					indexToRemove = i;
				}
			}
			//if found card to throw return the index of card to remove
			if(c!=null)
				return indexToRemove;
			
			//otherwise  all card in computer deck is kozer , try to find min kozer index to return
			c = cards.get(indexToRemove); //gets the first card of comp deck
			//
			for(i=1; i<cards.size();i++){
				if(cards.get(i).getNumber() < c.getNumber()){
					c=cards.get(i);
					indexToRemove = i;
				}
			}
			
			return indexToRemove; // return the index to remove from comp deck - allways return somthing because comp have cards and gameboard empty
		}
		
		//if game board not empty , must find card that exist on game board
		else{
			boolean found = false; //check if found card not kozer to throw
			Vector<Card> onTable = g.getBoard().getCards(); // gets the cards on gameboard
			//nested loop - try to find the first card that exist on gameboard that not kozer , if find break the loop ,and continue to next loop
			//if found chage found to true   
			for(i=0 ;i<onTable.size();i++){ 
				for(j=0;j<cards.size();j++){
					if(cards.get(j).getNumber() == onTable.get(i).getNumber() && !cards.get(j).getShape().equals(g.getKozerShape())){
						indexToRemove=j;
						found = true;
						break;
					}	
				}
			}	
			
			//nested loop - if didnt found card that exitst on gameboatd that his shape not kozershape it means that all computer cards are kozers , try to find card that exist and is shape is kozershape
			//if found break the loop and ,set found true 
			if(!found){
				Card c1=null;
				for(i=0 ;i<onTable.size();i++){
					for(j=0;j<cards.size();j++){
						if(cards.get(j).getNumber() == onTable.get(i).getNumber() && cards.get(j).getShape().equals(g.getKozerShape())){
							indexToRemove=j;
							c1 = cards.get(j);
							found = true;
							break;
						}	
					}
				}
				
				//if found some kozer that exist on table,try to find smaller kozer that exist on table
				if(c1!=null){
					for(i++ ;i<onTable.size();i++){
						for( j++;j<cards.size();j++){
							if(cards.get(j).getNumber() == onTable.get(i).getNumber() && cards.get(j).getShape().equals(g.getKozerShape()) && c1.getNumber()>cards.get(j).getNumber()){
								indexToRemove=j;
								c1 = cards.get(j);
								found = true;
								break;
							}	
						}
					}
				}
				//if not found anything returns -1
				else{
					return -1;
				}		
			}
		}
		
		//if fonund return index to remove
		return indexToRemove;	
	}
	/**
	 * function to defend based on player move
	 * @return index of card to put on table
	 */
	public int defend(){
		Card low=null;
		int i,indextoremove=0;
		Vector<Card> onTable = g.getBoard().getCards();
			
		//option one if card on table size-1 -> is kozer try to find bigger kozer to defend with
		if(onTable.get(onTable.size()-1).getShape().equals(g.getKozerShape()))
		{
			
			for(i=0;i<cards.size();i++){
				if(low == null && cards.get(i).getShape().equals(g.getKozerShape()) && cards.get(i).getNumber() > onTable.get(onTable.size()-1).getNumber()){
					low = cards.get(i);
					indextoremove=i;
				}
				
				else{
					
					if(low!=null && low.getNumber()> cards.get(i).getNumber() && cards.get(i).getShape().equals(g.getKozerShape()) && cards.get(i).getNumber() > onTable.get(onTable.size()-1).getNumber() ){
						low = cards.get(i);
						indextoremove=i;
					}
				}
			}
			
			if(low!=null) return indextoremove;
			else return -1;
			
		}
		
		//try to find the smallest card that bigger then the card on table and minimum in Compdeck , and his shape equal to card on table shape
		for(i=0;i<cards.size();i++){
			if(cards.get(i).getNumber() > onTable.get(onTable.size()-1).getNumber() && cards.get(i).getShape().equals(onTable.get(onTable.size()-1).getShape())){
				if(low==null || low.getNumber()>cards.get(i).getNumber()){
					low = cards.get(i);
					indextoremove=i;
				}
			}
		}
		//check if find and return
		if(low!=null)
			return indextoremove;
		// if not try to find the minumum kozer from CompDeck to defence
		else{
			for(i=0; i<cards.size(); i++){
				if(cards.get(i).getShape().equals(g.getKozerShape()))
					if(low==null || low.getNumber()>cards.get(i).getNumber()){
						low = cards.get(i);	
						indextoremove=i;
					}
			}
			if(low!=null) //if not null have some kozer to defence with
				return indextoremove;
			else // didnt found reurns -1
				return -1;
		}
	}
	
	/**
	 * function that clear all the cards from game board if bita is true
	 */
	public void bita()
	{
		Vector<Card> onTable = g.getBoard().getCards(); // get gameboard card vector
		if(onTable.size()%2==0) // if game borad card number is even it means that the defence respond to all attack cards
			g.getBoard().clear(); // clear all the cards from game borad
	}
	
	/**
	 * 
	 *  function that add all cards to compdec if comp cant respond to all attacker cards
	 */
	public void take(){
		Vector<Card> onTable = g.getBoard().getCards();// get gameboard card vector
		if(onTable.size()%2!=0){ // if game borad card number is not even it means that the defence not respond to all attack cards
			cards.addAll(g.getBoard().getCards()); // add all cards to comp deck from game boardeck
			g.getBoard().clear(); // clear all cards from game board deck
		}
			
			
			
	}
	
	/**
	 * function that draw compboard and gameboard
	 */ 
	private void redraw(){
		b.repaint(); //paintcomponent from compboard
		g.draw(); //draw from g class
	}
	
	/**
	 * the thread , runs while in runing equal true
	 */
	@Override
	public void run() {
		int index; // index to remove
		while(isRunning){
			if(myturn){ // if its computer turn
			//if(cards.size()!=0){
					if(attack){ // check if attack call to attack function
						index = attack();
						if(index!=-1){ //if found somthing attack with
							try {
								g.getBoard().addCard(cards.get(index),Type.COMPUTER); //add the card from comp to game board
								cards.remove(index); // remove card from computer board
								if(cards.size()==0 && g.getDectSize()==0 && g.getKozer()==null) // if comp is the winner 
								{
									redraw();
									g.declareWinner(Type.COMPUTER); //declare the winner
								}
								redraw(); // repain comp borad and game borad
								myturn=false;
								g.setTurn(Type.PLAYER);//change turn to player
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						else{ // if didnt found card to remove bita!
							bita();
							if(g.getDectSize()>0 || g.getKozer()!=null)
								g.distribute(); //distribute cards
							attack=false; //define computer in defend mode
							g.setType(Type.PLAYER); //change attacker to player
							myturn=false; //define not comp turn
							g.setTurn(Type.PLAYER);//change turn to player
							redraw(); //repain compboarad and gameboard
						}
					}
					
					else{ //if comp in defence mode - can only defence or take cards to hand
						index=defend(); //try to find card to defence with
						if(index!=-1){ //if found card
							try {
								g.getBoard().addCard(cards.get(index),Type.COMPUTER); //add to game board the card that comp defence with
								cards.remove(index); //remove form comp deck the card
								if(cards.size()==0 && g.getKozer()==null && g.getDectSize()==0){ //if comp in def mode and dont have card in hand ,the comp winner
									//TODO comp win
									redraw();
									g.declareWinner(Type.COMPUTER); //declare comp as winner
								}
								if(g.getBoard().getCards().size()==12){ //if comp put the 12 card on table its bita allways
									bita(); //bita
									g.distribute(); //distribute cards
									attack = true; //set comp as attacker
									g.setType(Type.COMPUTER); //notify game comp is attacker
									g.setPlayerDefend(); //set player as defend
									redraw(); //repaint from game and comppl class
								}
								//if bita and game board card size < 12
								redraw();
								myturn=false; //if found card to defence with its player turn
								g.setTurn(Type.PLAYER); //set player turn
							} catch (Exception e) { //exception for card size < 13
								e.printStackTrace();
							}
						}
						else{ // if comp dont have card to defence with ,need to take all cards from game board to hand
							take(); //function that take all cards from game board to comp deck
							if(g.getDectSize() > 0 || g.getKozer()!=null) // if game deck stiil have card didnt zero
								g.distributePlayer(); // if comp take cards from game board only player get cards from game deck
							attack=false; //define computer in defend mode
							g.setType(Type.PLAYER); //change attacker to player
							myturn=false;  // its player turn still because take cards
							g.setTurn(Type.PLAYER);
							redraw(); //drwa
						}
					}
				}
				try {
					Thread.sleep(3000); //delay 3 seconds beetwen moves
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	
	/**
	 * function that checks if comp is attacker
	 * @return true or flase if attacker or not
	 */
	public boolean isAttack() { 
		return attack;
	}
	
}
