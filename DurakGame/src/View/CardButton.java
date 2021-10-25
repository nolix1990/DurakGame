package View;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.*;
import Model.Card;

public class CardButton extends JButton {
	private Card card;
	//Constructor 
	public CardButton(Card c) {
		this.card = c;
		setIcon(card.getImageIcon());
		setPreferredSize(new Dimension(125,182));
	}
	public Card getCard() {
		return card;
	}
	
	/**
	 * function that set locatin by point
	 * @param p
	 */
	public void setPoint(Point p) {
		this.setLocation(p);
	}
	@Override
	public String toString() {
		return "CardButton [card=" + card + "]";
	}
	
}
