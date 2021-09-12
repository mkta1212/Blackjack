import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinorLose extends JFrame{
	private static final int FRAME_WIDTH=65;
	private final static int FRAME_HEIGHT=130;
	private JPanel panel1,panel2,panel3;
	private JButton yesBtn,noBtn;
	private JLabel label,label2;
	private static boolean valid=false;
	public WinorLose()
	{
		
	}
	public WinorLose(int a)
	{
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		createBtn();
		createLabel(a);
		panel1.add(label);
		panel2.add(label2);
		panel3.add(yesBtn);
		panel3.add(noBtn);
		add(panel1,BorderLayout.NORTH);
		add(panel2,BorderLayout.CENTER);
		add(panel3,BorderLayout.SOUTH);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
	}
	private void createBtn()
	{
		yesBtn=new JButton("Yes");
		class YesBtnActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				FrontPage frontPage=new FrontPage();
				frontPage.getMainPage().removeAll();
				MainPage mainPage=new MainPage(1);
				mainPage.getBlackjack().removeAll();
				frontPage.getMainPage().hitBtn.setEnabled(false);
				frontPage.getMainPage().standBtn.setEnabled(false);
				frontPage.getMainPage().hit2Btn.setEnabled(true);
				frontPage.getMainPage().stand2Btn.setEnabled(true);
				frontPage.getMainPage().hit2Btn.setVisible(false);
				frontPage.getMainPage().stand2Btn.setVisible(false);
				frontPage.getMainPage().doubleBtn.setVisible(false);
				frontPage.getMainPage().divideBtn.setVisible(false);
				frontPage.getMainPage().getBlackjack().getMoney().resetDou();
//				private Register getRegisterFromRegisterFrame() {
//					for(Frame frame: JFrame.getFrames()) {
//					if(frame.getTitle().equals("Course Register")) {
//					RegisterFrame registerFrame = (RegisterFrame) frame;
//					return registerFrame.getRegister();
//					}
//					}
//					return null;
//					}
//				valid=true;
				WinorLose.this.dispose();
				if(new Blackjack().getMoney().showTotal()<=0)
				{
					BrokeFrame sign=new BrokeFrame();
					sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					sign.setVisible(true);
					sign.setAlwaysOnTop(true);
					sign.setLocation(550,280);
				}
				else
				{
					MoneyFrame frame=new MoneyFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
//					frame.setAlwaysOnTop(true);
//					frame.setLocation(1280/2-245/2,455/2);
				}
			}
		}
		yesBtn.addActionListener(new YesBtnActionListener());
		noBtn=new JButton("No");
		class NoBtnActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
		noBtn.addActionListener(new NoBtnActionListener());
	}
	public void createLabel(int a)
	{
		if(a==1)
		{
			label=new JLabel("You Win");
		}
		else if(a==2)
		{
			label=new JLabel("You Lose");
		}
		else if(a==3)
		{
			label=new JLabel("It's a tie game");
		}
		else if(a==4)
		{
			label=new JLabel("You both win and lose");
		}
		else if(a==5)
		{
			label=new JLabel("One tie game and one lose");
		}
		else if(a==6)
		{
			label=new JLabel("One tie game and one win");
		}
		label2=new JLabel("Another Round?");
	}
	public boolean startAgain()
	{
		return valid;
	}
}
