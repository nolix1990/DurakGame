package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.swing.*;

import Control.ButtonHandler;
import Model.ScoreForStatistics;


public class Statistics extends JPanel implements ActionListener {
	
	private Application App;
	private JLabel backround;
	private JButton tomain;
	private JLabel staticDetails,datelb,wins,loses,statis;
	private float win,lose;
	private float getwin,getlose;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
	private String date = sdf.format(new java.util.Date() );
	

	public Statistics(Application app)  {
		App = app;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1440, 900));
		staticDetails =new JLabel();
		datelb = new JLabel();
		wins = new JLabel();
		loses = new JLabel();
		statis = new JLabel();
		Font f = new Font("Jokerman",Font.PLAIN,70);
		Font f2 = new Font("Jokerman",Font.ITALIC,100);
		staticDetails.setFont(f);
		datelb.setFont(f);
		wins.setFont(f);
		loses.setFont(f);
		statis.setFont(f2);
		load();
		staticDetails.setBounds(10, 250, 1000, 100);
		datelb.setBounds(1000, 15, 400, 100);
		wins.setBounds(10, 400, 1000, 100);
		loses.setBounds(10, 550, 1000, 100);
		statis.setBounds(10, 50, 510, 100);
		add(staticDetails);
		add(datelb);
		add(wins);
		add(loses);
		add(statis);
		tomain = ButtonHandler.button(tomain, "Rulespics/MENU.png", "Rulespics/menunot.png", 635, 750,140,140,this);
		this.add(tomain);
		backround = new JLabel(new ImageIcon("Rulespics/greenbord1.jpg"));
		backround.setSize(1440,900);
		this.add(backround);
		
	}
	

	
	public void load() {
		
		
		statis.setText("Statistics :");
			if(ScoreForStatistics.getNumofgames()!=0){
				getwin = ((float)ScoreForStatistics.getWin());
				getlose = ((float)ScoreForStatistics.getLose());
				win = ((getwin/ScoreForStatistics.getNumofgames())*100);
				lose = ((getlose/ScoreForStatistics.getNumofgames())*100);
				staticDetails.setText("Played" + "-  " + ScoreForStatistics.getNumofgames() + "   Games");
				wins.setText("Wins " + "-  " +  ScoreForStatistics.getWin() + "  " + (int)win +"%");
				loses.setText("Lost " + "-  " +  ScoreForStatistics.getLose() + "  " + (int)lose +"%");
				datelb.setText(date);
			}
			
			
			else{
				staticDetails.setText("Played" + " -  " + ScoreForStatistics.getNumofgames() + "   Games");
				wins.setText("Wins " + " -  " +  ScoreForStatistics.getWin() + "   0%");
				loses.setText("Lost " + " -  " +  ScoreForStatistics.getLose() + "   0%" );
				datelb.setText(date);
			}
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(tomain))
		{
			App.ChangePanel(new MainMenu(App));
		}
		
	}

}
