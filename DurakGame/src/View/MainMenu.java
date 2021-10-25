package View;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import Control.ButtonHandler;

public class MainMenu extends JPanel implements ActionListener {
	private JLabel backround;
	private JButton playgame,exit,rules,staticstics;
	private Application App;
	
	public MainMenu(Application app){
		this.App=app;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1600, 900));
		backround = new JLabel(new ImageIcon("Mainmenuimage/main.png"));
		backround.setSize(1600,900);
		playgame = ButtonHandler.button(playgame, "Mainmenuimage/Play Game Clicked.png", "Mainmenuimage/playgame1.png", 100, 300,258,79,this);
		rules = ButtonHandler.button(rules, "Mainmenuimage/Rules.png",  "Mainmenuimage/rules1.png", 100, 450,258,79, this);
		staticstics = ButtonHandler.button(staticstics, "Mainmenuimage/Statistics.png","Mainmenuimage/staticstics1.png", 100, 600,258,79, this);
		exit = ButtonHandler.button(exit, "Mainmenuimage/Exit.png", "Mainmenuimage/þþExit1.png", 1300, 730,258,79,this);
		this.add(playgame);
		this.add(rules);
		this.add(staticstics);
		this.add(exit);
		this.add(backround);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource().equals(playgame))
			App.ChangePanel(new Game(App));
		
		else if(e.getSource().equals(rules))
			App.ChangePanel(new Rules(App));
		
		else if(e.getSource().equals(staticstics)){
				App.ChangePanel(new Statistics(App));
			}
		else if(e.getSource().equals(exit))
			System.exit(0);
	}

}
