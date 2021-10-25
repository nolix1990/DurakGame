package View;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;
import javax.swing.*;
import Control.Type;
import Model.Card;
import Model.Player;
public class PlayerBoard extends JPanel implements ActionListener{
	private Game g;
	private Player p;
	private boolean isVisible = false;
	private boolean attack = false;
	private Vector<CardButton> cards;
	public PlayerBoard(Game g, Player p){
		setLayout(null);
		setOpaque(false);
		this.g= g;
		this.p = p;
		this.cards = new Vector<CardButton>();
	}
	public int getCardsSize(){
		return cards.size();
	}
	public void addCard(Card c){
		this.p.addCard(c);
		CardButton cb = new CardButton(c);
		cb.addActionListener(this);
		cards.add(cb);
		drawBoard();
	}
	public void setAttack(){
		attack=true;
	}
	public void addAll(Vector<Card> c){
		for(int i=0; i<c.size(); i++)
			 addCard(c.get(i));
	}
	public void drawBoard(){
		invalidate();
		setVisible(false);
		removeAll();
		for(int i=0; i<cards.size();i++){
			cards.get(i).setBounds(10+i*90, 10, 125, 182);
			this.add(cards.get(i));
		}
		repaint();
		setVisible(true);
		validate();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard();
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		CardButton c = (CardButton) e.getSource();
		if(checkLegal(c.getCard()) && g.getTurn().equals(Type.PLAYER)){
			try {
				p.removeCard(c.getCard());
				cards.remove(cards.indexOf(c));
				repaint();
				g.getBoard().addCard(c.getCard(),Type.PLAYER);
				
				if(cards.size()==0 && g.getDectSize()==0 && g.getKozer()==null)
					g.declareWinner(Type.PLAYER);
				if(g.getBoard().getCards().size()==12){ //if player added 12 card
					g.getBoard().clear(); //clear the board
					g.distribute(); //distribute cards
					g.setType(Type.PLAYER); //set player as attacker, comp as defender
					g.draw(); //redraw
				}
				else{
					waitForTurn(); //wait for comp move
					g.setTurn(Type.COMPUTER); //set player as defender, comp as attacker
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public void waitForTurn() {
		for(int i=0; i<cards.size();i++)
			cards.get(i).setEnabled(false);
	}
	public void getTurn() {
		for(int i=0; i<cards.size();i++)
			cards.get(i).setEnabled(true);
	}
	private boolean checkLegal (Card c){
		if(!g.getBoard().isEmpty()){ //if there are cards on table
			Vector<Card> onTable = g.getBoard().getCards(); //get cards
			if(attack){ //if player attack
				for(int i=0; i<onTable.size();i++){ //check if card to throw legal
					if(c.getNumber()==onTable.get(i).getNumber()) //if card number exist on table
						return true;
				}
				return false;
			}
			else{ //if player defend
				//case 1 - find same shape  bigger num
				if(c.getShape().equals(onTable.get(onTable.size()-1).getShape())  ) {//check if card the same shape and bigger
					if(c.getNumber() > onTable.get(onTable.size()-1).getNumber())
					return true;
					else return false;
					
				}
				//case 2 -if the card that computer attacked is kozer
				else{
					if(onTable.get(onTable.size()-1).getShape().equals(g.getKozerShape())){ //if kozer on table
						if(c.getShape().equals(g.getKozerShape()) && c.getNumber()>onTable.get(onTable.size()-1).getNumber()) //if card is kozer && bigger
							return true;
						else 
							return false;
					}
					else { //case 3 - if the card that computer attacked is not kozer
						if(c.getShape().equals(g.getKozerShape())) //if the player clicked on any kozer its fine
							return true;
						else
							return false;
					}
				}			
			}
		}
		return true; //case empty table
	}
	public boolean isAttack() {
		return attack;
	}
	public void setDefend() {
		attack = false;
	}
	
}
