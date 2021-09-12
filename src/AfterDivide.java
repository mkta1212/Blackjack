import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class AfterDivide extends JFrame{
	private static final int FRAME_WIDTH=50;
	private final static int FRAME_HEIGHT=80;
	private JPanel panel;
	private JButton okBtn;
	private JLabel label;
	private WinorLose sign;
	public AfterDivide(int a)
	{
		createBtn();
		createLabel(a);
		panel=new JPanel();
		panel.add(label);
		panel.add(okBtn);
		add(panel);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
	}
	public void createBtn()
	{
		okBtn=new JButton("OK");
		class OkActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				AfterDivide.this.dispose();
			}
		}
		okBtn.addActionListener(new OkActionListener());
	}
	public void createLabel(int a)
	{
		if(a==1)
		{
			label=new JLabel("You Win");
			
		}
		else if(a==2)
		{
			{
				label=new JLabel("You Lose");
			}
		}
	}
}
