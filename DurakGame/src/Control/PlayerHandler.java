package Control;

import Model.Card;
import Model.Computer;
import Model.Player;

public class PlayerHandler {
	/**
	 * @param p - the player
	 * @param c - the computer
	 * @param kozer - the kozer
	 * @return who starts player or computer
	 */
	public static Type getFirst(Player p, Computer c, Card kozer){
		Card lowplayer=null,lowcomp=null ;
		for(int i=0; i<p.getCards().size(); i++){
			if(p.getCards().get(i).getShape() == kozer.getShape())
				if(lowplayer==null || lowplayer.getNumber()>p.getCards().get(i).getNumber()) //trying to finds min kozer in player deck
					lowplayer = p.getCards().get(i);
		}
		
		for(int i=0; i<c.getCards().size(); i++){
			if(c.getCards().get(i).getShape() == kozer.getShape())//trying to finds min kozer in comp deck
				if(lowcomp==null || lowcomp.getNumber()>c.getCards().get(i).getNumber())
					lowcomp = c.getCards().get(i);
		}
		if(lowcomp == null) //if didnt found kozer in comp deck the player starts auto
			return Type.PLAYER;
		if(lowplayer==null || lowcomp.getNumber()<lowplayer.getNumber()){ // if didnt found in player hand kozer or player kozer bigger then comp kozer comp starts
			c.setMyturn(true);
			return Type.COMPUTER;
		}
		return Type.PLAYER; //if didnt found kozer in player and comp deck player starts auto
	}
}
