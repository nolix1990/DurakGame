package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;
import Control.ButtonHandler;
import Control.PlayerHandler;
import Control.Type;
import Model.Card;
import Model.Computer;
import Model.Deck;
import Model.Player;
import Model.ScoreForStatistics;
import Model.Shape;
public class Game extends JPanel implements ActionListener{
	private Application app; //the app in which the game run
	private JLabel backround; //game background
	private Deck deck= new Deck(); //game deck
	private JLabel shapelbl,lblNum, lblDeck = new JLabel(new ImageIcon("Cards/backside.png")); //deck gui
	private Card kozer = deck.getcard(); //game kozer
	private Shape kozerShape = kozer.getShape();
	private JLabel lblKozer; //kozer gui
	private Player player; //the player
	private PlayerBoard play ; //the player gui
	private ComputerBoard computer; //the computer player and gui
	private GameBoard board; //the onTable gui
	private Type turn; //who turn is it
	private JButton pBitta, pTake,exittomain; //gui for take and bita for player
	private boolean canDistribute = true; //flag to check if can distribute cards to players
	public Game(Application app) {
		this.app = app;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1400, 900)); //game size
		shapelbl = new JLabel(new ImageIcon("Gamepics/" + kozerShape + ".png"));
		shapelbl.setBounds(1295,410,75,67);
		add(shapelbl);
		shapelbl.setVisible(false);
		lblNum = new JLabel((deck.getSize()+1)-12+""); //creates the label for deck size 
		lblNum.setBounds(1315, 425, 35, 30);// sets game deck size 
		Font f = new Font("Garamond",Font.BOLD,36); // the font of deck size
		lblNum.setBackground(Color.WHITE); //backround for game deck size
		lblNum.setOpaque(true);
		lblNum.setFont(f); 
		add(lblNum);
		lblDeck.setBounds(1270, 350, 125, 182);
		add(lblDeck);	
		lblKozer = new JLabel(kozer.getSideImage());
		lblKozer.setBounds(1180, 380,182, 125);
		add(lblKozer);
		pBitta  = ButtonHandler.button(pBitta, "Gamepics/bita.png", "Gamepics/bitanot.png", 1260,200,140,140,this);
		pTake  = ButtonHandler.button(pTake, "Gamepics/take.png", "Gamepics/takenot.png", 1260,530,140,140,this);
		exittomain = ButtonHandler.button(pTake, "Gamepics/exittomain.png", "Gamepics/exittomain.png", 1320,0,82,91,this);
		add(pBitta);
		add(pTake);
		add(exittomain);
		player = new Player("YOSI");
		play = new PlayerBoard(this, player);
		Card c;
		for(int i=0; i<6; i++){ //distribute start cards to player
			c = deck.getcard();
			play.addCard(c);
		}
		play.setBounds(0, 700, 1390, 200);
		add(play);
		computer = new ComputerBoard(this);
		for(int i=0; i<6; i++){ //distribute start cards to computer
			computer.addCard(deck.getcard());
		}
		computer.setBounds(0, 10, 1320, 200);
		add(computer);	
		turn = PlayerHandler.getFirst(player, computer.getComp(), kozer); //get start type
		board = new GameBoard(turn);
		board.setBounds(0, 280, 920, 350);
		add(board);
		if(turn.equals(Type.COMPUTER)){ //set turn based on start type
			computer.setTurn(true);
			computer.setAttack();
			play.setDefend();
			play.waitForTurn();
			computer.startPlay();
		}
		else{
			computer.setDefend();
			play.setAttack();
			computer.startPlay();
		}
		
	}
	/**
	 * @return the game board
	 */
	public GameBoard getBoard() {
		return board;
	}	
	/**
	 * @return the kozer card
	 */
	public Card getKozer(){
		return kozer;
	}
	/**
	 * @return the kozer shape
	 */
	public Shape getKozerShape(){
		return kozerShape;
	}
	/**
	 * @param t - the turn belongs to type t
	 */
	public void setTurn(Type t){
		turn = t; //change turn
		if(t.equals(Type.COMPUTER)){ //if computer
			computer.setTurn(true);
			play.waitForTurn();
		}
		else{ //if player
			play.getTurn();
			computer.setTurn(false);
		}
	}
	
	public Type getTurn(){
		return this.turn;
	}
	
	/** 
	 * @param type - which type is attacker
	 */
	public void setType(Type type) {
		board.setType(type); //set attacker type
		if(type.equals(type.PLAYER)){ //if player
				play.setAttack();
				computer.setDefend();
		}
		else{ //if computer
			computer.setAttack();
			play.setDefend();
		}
	}
	/**
	 * set player as defender
	 */
	public void setPlayerDefend(){
			play.setDefend();
	}
	
	/**
	 * @param graphic 
	 * repain the backround and add to panel
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		backround = new JLabel(new ImageIcon("Gamepics/greenbord1.jpg"));
		backround.setSize(1400,900);
		add(backround);
	}
	/**
	 * draw game gui
	 * add all components and add the bakcround
	 */
	public void draw(){
		invalidate();
		//setVisible(false);
		removeAll();
		add(shapelbl);
		add(lblNum);
		add(lblDeck);
		add(lblKozer);
		add(pBitta);
		add(pTake);
		add(exittomain);
		add(play);
		add(computer);
		add(board);
		repaint();
		//setVisible(true);
		validate();
	}
	/**
	 * @return the deck size
	 */
	public int getDectSize(){ 
		return deck.getSize();
	}
	
	/**
	 * 
	 * @param actioevent e
	 * action perform function - for clicking on pbita or ptake
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Vector<Card> onTable = this.board.getCards(); // gets card vecton from gameborad
		if(e.getSource().equals(pBitta) && onTable.size()!=0){ // check if player clicked on bita and game board size not zero then can do bita
			if(onTable.size()%2==0 && play.isAttack()){ // if game board size is even and player on attack mode it means that can do bita
				board.clear(); // remove all cards form gameboard deck
				if(deck.getSize()>0 || kozer!=null)  //if deck size not zero can distrubute
					distribute(); // distrbute cards to 2 players
				draw(); // add all components to pannel and repaint
				turn=Type.COMPUTER; // player make bita , so its comp turn now
				computer.setTurn(true); //set comp turn true
				computer.setAttack(); // comp was diffenct before bita,so now he attacker
				play.setDefend(); // player was attacker before bita,so now he defence
				board.setType(Type.COMPUTER);//if bita comp attacks now
			}
		}
		else if(e.getSource().equals(pTake) && onTable.size()!=0){ // if player need to take cards and cliked on take and there are cards on game board
			if(onTable.size()%2!=0 && play.isAttack()==false){ //if game board size is not even and player in defence mode only, can take
				play.addAll(getBoard().getCards()); //add all game borad cards to player deck 
				getBoard().clear(); // remove all cards from gameborad deck
				if(deck.getSize()>0 || kozer!=null)  // if can distribute , game deck >0 and kozer not null
					distributeComputer(); // distribute only to comp because player take cards
				draw(); // repain the panel
				computer.setTurn(true); // if player take the turn stays in computer
				computer.setAttack();// the comp still attacks beacuse player take
				play.setDefend();//player stay in defence mode
				board.setType(Type.COMPUTER);//comp stays attacker
			}
		}
		
		else if(e.getSource().equals(exittomain)){
			int ans =  JOptionPane.showConfirmDialog (null, "Are you sure?","EXIT_WINDOW", JOptionPane.YES_NO_OPTION);
            if(ans == JOptionPane.YES_OPTION) {
            	computer.stopComputer();
                app.ChangePanel(new MainMenu(app));
             }
		}
	}
	/**
	 * distribute cards to player
	 */
	public void distributePlayer(){
		Card c=null;
		if(deck.getSize()+1!=0 && play.getCardsSize()< 6 ){ // game deck must be > 0 , and his deck need to be less then 6
			int psize=play.getCardsSize(); // gets player deck size
			while(psize < 6 && (deck.getSize()!=0 || kozer!=null) )	{ //while player deck not 6 distribute to player
				if(canDistribute){
					if(deck.getSize()==0 && kozer!=null){//if deck size equal to 0 , and kozer not null,for hide the pics of lblnum and decklbl
						c = kozer;//take the kozer card to add to player gamedeck
						kozer = null;//set the kozer null
						lblKozer.setVisible(false);//hide the kozer
						shapelbl.setVisible(true);
						canDistribute = false; // set flase,becuse decksize is 0
					}
					else {// if deck size not zero add to player deck while psize<6
						c = deck.getcard();
					if(deck.getSize()==0){
						lblNum.setVisible(false); //hide both
						lblDeck.setVisible(false);
					}
					}
					play.addCard(c);
					psize++;
				}
			}
		}
		lblNum.setText((deck.getSize()+1)+""); // set the size of card deck
		draw(); //repaint
	}
	/**
	 * distribute cards to computer player
	 */
	public void distributeComputer(){
		Card c=null;
		if(deck.getSize()+1!=0 && computer.getComp().getCards().size() < 6 ){
			int psize=computer.getComp().getCards().size();
			while(psize < 6 && (deck.getSize()!=0 || kozer!=null) )	{
				if(canDistribute){
					if(deck.getSize()==0 && kozer!=null){
						c = kozer;
						kozer = null;
						lblKozer.setVisible(false);
						shapelbl.setVisible(true);
						canDistribute = false;
					}
					else{
						c = deck.getcard();
					if(deck.getSize()==0){
						lblNum.setVisible(false);
						lblDeck.setVisible(false);
					}
					}
					computer.addCard(c);
					psize++;
				}
			}
		}
		lblNum.setText((deck.getSize()+1)+"");
		draw();
	}
	/**
	 * distribute cards to game players
	 */
	public void distribute(){
		if(board.getType().equals(Type.PLAYER)){
			distributePlayer();
			distributeComputer();
		}
		else{
			distributeComputer();
			distributePlayer();
		}
		lblNum.setText((deck.getSize()+1)+"");
		draw();
	}
	public void declareWinner(Type t){
		if(t.equals(Type.COMPUTER)){
			JOptionPane.showMessageDialog(null, "The computer won you are a Durak");//
			ScoreForStatistics.setLose((ScoreForStatistics.getLose()+1));
		}
		else{
			JOptionPane.showMessageDialog(null, "The player won computer is a Durak");//
			ScoreForStatistics.setWin((ScoreForStatistics.getWin()+1));
		}
		ScoreForStatistics.setNumofgames((ScoreForStatistics.getNumofgames()+1));
		board.clear();
		computer.stopComputer();
		app.ChangePanel(new MainMenu(app));
	}
}