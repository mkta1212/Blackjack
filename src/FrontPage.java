import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class FrontPage extends JFrame{
	private static final int FRAME_WIDTH=64*10;
	private final static int FRAME_HEIGHT=44*10;
	private JButton startBtn,exitBtn,instuctionBtn;
	private static MainPage mainPage;
	public FrontPage()
	{
		ImagePanel bgImagePanel = new ImagePanel("blackjack.jpg");
		bgImagePanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		setContentPane(bgImagePanel);
		createBtn();
		startBtn.setBounds(100,150,130,30);
		getContentPane().add(startBtn);
		instuctionBtn.setBounds(100,210,130,30);
		getContentPane().add(instuctionBtn);
		exitBtn.setBounds(100,270,130,30);
		getContentPane().add(exitBtn);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(320, 130);
	}
	public void createBtn()
	{
		startBtn=new JButton("Start");
		class StartBtnActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				FrontPage.this.dispose();
				mainPage=new MainPage();
				mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainPage.setVisible(true);
				MoneyFrame frame=new MoneyFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		}
		startBtn.addActionListener(new StartBtnActionListener());
		
		exitBtn=new JButton("Exit");
		class ExitBtnActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
		exitBtn.addActionListener(new ExitBtnActionListener());
		instuctionBtn=new JButton("How to Play");
		class InstuctionBtnActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				FrontPage.this.dispose();
				HowToPlay frame=new HowToPlay();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		}
		instuctionBtn.addActionListener(new InstuctionBtnActionListener());
	}
	public MainPage getMainPage()
	{
		return mainPage;
	}
}
