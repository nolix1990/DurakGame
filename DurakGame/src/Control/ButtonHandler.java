package Control;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * class that have function jbutton type - makes the button and returns
 * @author Nolix
 *  @returns button
 */
public class ButtonHandler {
	public static JButton button(JButton b,String notclicked, String clicked,int x, int y,int widht,int hight, ActionListener a){	
		b = new JButton(new ImageIcon(notclicked));
		b.setSize(widht,hight);
	    b.setLocation(x,y);
	    b.setBorder(null);
	 	b.setFocusPainted(false);
	 	b.setContentAreaFilled(false);
	    b.setPressedIcon(new ImageIcon(clicked));
	    b.addActionListener(a);
	    return b;	
	}
}
