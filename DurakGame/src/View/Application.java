package View;
import javax.security.auth.login.LoginContext;
import javax.swing.*;

public class Application extends JFrame {
	
	JPanel p;
	//Constructor 
	public Application()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		p=new MainMenu(this);
		add(p);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void ChangePanel(JPanel p)
	{
		invalidate();
		setVisible(false);
		getContentPane().removeAll();
		this.p=p;
		add(p);
		pack();
		setLocationRelativeTo(null);
		validate();
		setVisible(true);
	}
	

	public static void main(String[] args) {
		
	SwingUtilities.invokeLater(new Runnable() {
		
		@Override
		public void run() {
			new Application();
			
		}
	});

	}

}
