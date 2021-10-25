package Model;

public class ScoreForStatistics {
	
	private static int win=0,lose=0,numofgames=0;

	public static int getWin() {
		return win;
	}

	public static void setWin(int win) {
		ScoreForStatistics.win = win;
	}

	public static int getLose() {
		return lose;
	}

	public static void setLose(int lose) {
		ScoreForStatistics.lose = lose;
	}

	public static int getNumofgames() {
		return numofgames;
	}

	public static void setNumofgames(int numofgames) {
		ScoreForStatistics.numofgames = numofgames;
	}
	
	

}
