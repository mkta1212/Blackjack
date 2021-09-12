import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
public class BrokeFrame extends JFrame{
	private static final int FRAME_WIDTH=20;
	private final static int FRAME_HEIGHT=100;
	private JLabel label;
	private JButton button;
	public BrokeFrame()
	{
		JPanel panel=new JPanel();
		createElement();
		panel.add(label);
		panel.add(button);
		add(panel);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
	}
	public void createElement()
	{
		label=new JLabel("YOU ARE BROKE!",JLabel.CENTER);
		label.setFont(new java.awt.Font("Dialog",1,15));
		button=new JButton("OK");
		class BtnActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				new FrontPage().getMainPage().dispose();
				BrokeFrame.this.dispose();
				FrontPage frontPage=new FrontPage();
				frontPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frontPage.setVisible(true);
				frontPage.setLocation(320, 130);
			}
		}
		button.addActionListener(new BtnActionListener());
	}
}
