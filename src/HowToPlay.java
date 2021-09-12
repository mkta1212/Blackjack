import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class HowToPlay extends JFrame{
	private static final int FRAME_WIDTH=64*10;
	private final static int FRAME_HEIGHT=38*10;
	public HowToPlay()
	{
		ImagePanel bgImagePanel = new ImagePanel("blackjack(1).jpg");
		bgImagePanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		setContentPane(bgImagePanel);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(320, 130);
		JButton button=new JButton("←");
		class ButtonActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				FrontPage frontPage=new FrontPage();
				frontPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frontPage.setVisible(true);
				frontPage.setLocation(320, 130);
				HowToPlay.this.dispose();
			}
		}
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setBounds(5,5,50,20);
		button.addActionListener(new ButtonActionListener());
		getContentPane().add(button);
	}
}
