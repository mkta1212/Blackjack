import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
public class MoneyFrame extends JFrame{
	private static final int FRAME_WIDTH=345;
	private final static int FRAME_HEIGHT=245;
	private JLabel label1,label2;
	private JButton yesButton,noButton,button1,button2,button3,button4,button5,button6,button7;
	private int stake=0;
	public MoneyFrame()
	{
		label1=new JLabel("Now you have "+new Blackjack().getMoney().showTotal()+" dollars",JLabel.CENTER);
		label2=new JLabel("",JLabel.CENTER);
		label1.setFont(new java.awt.Font("Dialog",1,15));
		label2.setFont(new java.awt.Font("Dialog",1,15));
		createYNBtn();
		createButton();
//		JSlider slider=new JSlider(0,new Blackjack().getMoney().showTotal());
//		class SliderListener implements ChangeListener
//		{
//			public void stateChanged(ChangeEvent e) {
//				stake=slider.getValue();
//				label2.setText("You want to bet "+stake);
//			}
//		}
//		slider.addChangeListener(new SliderListener());
		label1.setBounds(50,10,240,20);
		getContentPane().add(label1);
		label2.setBounds(50,38,240,20);
		getContentPane().add(label2);
		JLabel label3=new JLabel("");
		label3.setBounds(20,30,200,20);
		getContentPane().add(label3);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setLocation(1280/2-345/2,700/2-245/2);
	}
	public void createButton()
	{
		button1=new JButton("10");
		if(new Blackjack().getMoney().showTotal()-stake<10)
		{
			button1.setEnabled(false);
		}
		class Button1ActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				stake+=10;
				label2.setText("You want to bet "+stake);
				setEnable();
			}
		}
		button1.addActionListener(new Button1ActionListener());
		button1.setBounds(10,70,70,35);
		getContentPane().add(button1);
		button2=new JButton("50");
		if(new Blackjack().getMoney().showTotal()-stake<50)
		{
			button2.setEnabled(false);
		}
		class Button2ActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				stake+=50;
				label2.setText("You want to bet "+stake);
				setEnable();
			}
		}
		button2.addActionListener(new Button2ActionListener());
		button2.setBounds(90,70,70,35);
		getContentPane().add(button2);
		button3=new JButton("100");
		if(new Blackjack().getMoney().showTotal()-stake<100)
		{
			button3.setEnabled(false);
		}
		class Button3ActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				stake+=100;
				label2.setText("You want to bet "+stake);
				setEnable();
				}
		}
		button3.addActionListener(new Button3ActionListener());
		button3.setBounds(170,70,70,35);
		getContentPane().add(button3);
		button4=new JButton("500");
		if(new Blackjack().getMoney().showTotal()-stake<500)
		{
			button4.setEnabled(false);
		}
		class Button4ActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				stake+=500;
				label2.setText("You want to bet "+stake);
				setEnable();
				}
		}
		button4.addActionListener(new Button4ActionListener());
		button4.setBounds(250,70,70,35);
		getContentPane().add(button4);
		button5=new JButton("1000");
		if(new Blackjack().getMoney().showTotal()-stake<1000)
		{
			button5.setEnabled(false);
		}
		class Button5ActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				stake+=1000;
				label2.setText("You want to bet "+stake);
				setEnable();
				}
		}
		button5.addActionListener(new Button5ActionListener());
		button5.setBounds(10,115,70,35);
		getContentPane().add(button5);
		button6=new JButton("5000");
		if(new Blackjack().getMoney().showTotal()-stake<5000)
		{
			button6.setEnabled(false);
		}
		class Button6ActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				stake+=5000;
				label2.setText("You want to bet "+stake);
				setEnable();
				}
		}
		button6.addActionListener(new Button6ActionListener());
		button6.setBounds(90,115,70,35);
		getContentPane().add(button6);
		button7=new JButton("10000");
		if(new Blackjack().getMoney().showTotal()-stake<10000)
		{
			button7.setEnabled(false);
		}
		class Button7ActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) {
				stake+=10000;
				label2.setText("You want to bet "+stake);
				setEnable();
				}
		}
		button7.addActionListener(new Button7ActionListener());
		button7.setBounds(170,115,70,35);
		getContentPane().add(button7);
		JButton button=new JButton("All in");
		class AllinButton implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				stake=new Blackjack().getMoney().showTotal();
				label2.setText("You want to bet "+stake);
				setEnable();
			}
		}
		button.addActionListener(new AllinButton());
		button.setBounds(250,115,70,35);
		getContentPane().add(button);
	}
	public void createYNBtn()
	{
		yesButton=new JButton("Yes");
		class YesBtn implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				if(stake==0)
				{
					label2.setText("Please choose one");
				}
				else
				{
					new Blackjack().getMoney().setBet(stake);
					new FrontPage().getMainPage().createFirst();
					MoneyFrame.this.dispose();
				}
			}
		}
		yesButton.addActionListener(new YesBtn());
		yesButton.setBounds(65,160,80,35);
		getContentPane().add(yesButton);
		noButton=new JButton("reset");
		class NoBtn implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				label2.setText("");
				stake=0;
				if(new Blackjack().getMoney().showTotal()-stake>10)
				{
					button1.setEnabled(true);
				}
				if(new Blackjack().getMoney().showTotal()-stake>50)
				{
					button2.setEnabled(true);
				}
				if(new Blackjack().getMoney().showTotal()-stake>100)
				{
					button3.setEnabled(true);
				}
				if(new Blackjack().getMoney().showTotal()-stake>500)
				{
					button4.setEnabled(true);
				}
				if(new Blackjack().getMoney().showTotal()-stake>1000)
				{
					button5.setEnabled(true);
				}
				if(new Blackjack().getMoney().showTotal()-stake>5000)
				{
					button6.setEnabled(true);
				}
			}
		}
		noButton.addActionListener(new NoBtn());
		noButton.setBounds(185,160,80,35);
		getContentPane().add(noButton);
	}
	public void setEnable()
	{
		if(new Blackjack().getMoney().showTotal()-stake<10)
		{
			button1.setEnabled(false);
		}
		if(new Blackjack().getMoney().showTotal()-stake<50)
		{
			button2.setEnabled(false);
		}
		if(new Blackjack().getMoney().showTotal()-stake<100)
		{
			button3.setEnabled(false);
		}
		if(new Blackjack().getMoney().showTotal()-stake<500)
		{
			button4.setEnabled(false);
		}
		if(new Blackjack().getMoney().showTotal()-stake<1000)
		{
			button5.setEnabled(false);
		}
		if(new Blackjack().getMoney().showTotal()-stake<5000)
		{
			button6.setEnabled(false);
		}
		if(new Blackjack().getMoney().showTotal()-stake<10000)
		{
			button7.setEnabled(false);
		}
	}
}
