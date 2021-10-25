package View;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Control.Type;
import Model.Card;

public class GameBoard extends JPanel{
	private Vector<Card> pCards; //for player cards -> for drawing
	private Vector<Card> cCards; //for computer cards -> for drawing
	private Vector<Card> cards; //for cards on table
	private Type type;
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType(){
		return this.type;
	}
	public Vector<Card> getCards() {
		return cards;
	}
	public boolean isEmpty(){
		if(cards.size()==0)
			return true;
		else return false;
	}
	public boolean isEven(){
		if ((pCards.size()+cCards.size())%2==0)
			return true;
		return false;
	}
	public GameBoard(Type type) {
		this.type = type;
		pCards = new Vector<Card>();
		cCards = new Vector<Card>();
		cards = new Vector<Card>();
		setOpaque(false);
	}
	
	public void addCard(Card c, Type t) throws Exception{
		if((pCards.size()+cCards.size())==13)
			throw new Exception("can't add card to board");
		if(t.equals(Type.PLAYER))
			pCards.add(c);
		else
			cCards.add(c);
		cards.add(c);
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(type.equals(Type.PLAYER)){
			for(int i=0; i<pCards.size(); i++){
				g.drawImage(pCards.get(i).getImageIcon().getImage(), 10+i*155, 40, 125, 182, null);
			}
			for(int i=0; i<cCards.size();i++)
				g.drawImage(cCards.get(i).getImageIcon().getImage(), 10+i*155, 100, 125, 182, null);
		}
		else{
			for(int i=0; i<cCards.size();i++)
				g.drawImage(cCards.get(i).getImageIcon().getImage(), 10+i*155, 40, 125, 182, null);
			for(int i=0; i<pCards.size(); i++)
				g.drawImage(pCards.get(i).getImageIcon().getImage(), 10+i*155, 100, 125, 182, null);
		}
	}
	public void clear() {
		pCards.clear();
		cCards.clear();	
		cards.clear();
	}
}
