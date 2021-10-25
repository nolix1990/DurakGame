package View;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Control.ButtonHandler;

public class Rules extends JPanel implements ActionListener {
	
	private Application App;
	private JButton tomain,back,forward;
	private JLabel page,backround;
	private int cnt=1;
	
	public Rules(Application App) {
		this.App = App;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1440, 900));
		backround = new JLabel(new ImageIcon("Rulespics/greenbord1.jpg"));
		tomain = ButtonHandler.button(tomain, "Rulespics/MENU.png", "Rulespics/menunot.png", 635, 750,140,140,this);
		page = new JLabel(new ImageIcon("Rulespics/page1.gif"));
		back = ButtonHandler.button(back, "Rulespics/back.png", "Rulespics/backnot.png", 1250,750,140,140,this);
		back.setVisible(false);
		forward = ButtonHandler.button(forward, "Rulespics/NEXT.png", "Rulespics/nextnot.png", 40,750,140,140,this);
		backround.setSize(1440,900);
		page.setSize(1440,900);
		this.add(forward);
		this.add(back);
		this.add(tomain);
		this.add(page);
		this.add(backround);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(back)){
			if(cnt>1){
				cnt--;
				page.setIcon(new ImageIcon("Rulespics/page" + cnt + ".gif" ));
				forward.setVisible(true);
			}
			if(cnt==1)			
				back.setVisible(false);	
		}
		
		if(e.getSource().equals(forward)){
			if(cnt<4){
				cnt++;
				page.setIcon(new ImageIcon("Rulespics/page"+ cnt + ".gif" ));
				back.setVisible(true);	
			}		
			if(cnt==4)
				forward.setVisible(false);	
		}
		if(e.getSource().equals(tomain))
			App.ChangePanel(new MainMenu(App));
	}
	
}
