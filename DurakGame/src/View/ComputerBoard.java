package View;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Card;
import Model.Computer;
import Model.Player;

public class ComputerBoard extends JPanel{
	private Computer comp;
	private Game g;
	private Thread t;
	//constructor
	public ComputerBoard(Game g) {
		this.g = g;
		this.comp = new Computer(g, this); //create new computer player with g and board
		t = new Thread(comp);//creating the thared
		//t.start();//starts the thread
		setOpaque(false);
	}
	public void startPlay(){
		t.start();//starts the thread
	}
	/**
	 * function that stop the thread
	 */
	public void stopComputer(){
		comp.close();
	}
	
	/**
	 * function that gets compuer
	 * @return comp
	 */
	public Computer getComp() {
		return comp;
	}
	
	/**
	 * function that set turn to comp
	 * @param turn
	 */
	public void setTurn(Boolean turn){
		comp.setMyturn(turn);
	}
	
	/**
	 * function that sets comp as attacker
	 */
	public void setAttack(){
		comp.setAttack();
	}

	/**
	 * function that checks if comp in defence mode
	 * @return true or flase
	 */
	public boolean isAttack(){
		return comp.isAttack();
	}
	
	/**
	 * function that set comp in defence mode
	 */
	public void setDefend(){
		comp.setDefend();
	}
	
	/**
	 * function that adds card to comp deck/board
	 * @param c - card
	 */
	public void addCard(Card c){
		comp.addCard(c);
		repaint();//repaints comp board
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0; i<comp.getCards().size();i++){
			g.drawImage(comp.getCards().get(i).getBackImage().getImage(), 10+i*65, 10, 125,182,null);
		}
	}
}
