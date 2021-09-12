import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class MainPage extends JFrame{
	private static final int FRAME_WIDTH=1280;
	private final static int FRAME_HEIGHT=700;
	private static final int AREA_WIDTH=30;
	private static final int AREA_HEIGHT=10;
	private JPanel panel1,panel2,panel3;
	JButton hitBtn,standBtn,divideBtn,hit2Btn,stand2Btn,doubleBtn;
	private JTextArea resultArea;
	private JScrollPane scrollPane;
	private JLabel label,label1,betAmount,bankAmount;
	private JLabel scoreLabel=new JLabel();
	private JLabel scoreLabel2=new JLabel();
	private JCheckBox checkBox;
	private static Blackjack blackjack;
	private static MoneyFrame frame;
	private static CardGroupPanel dealerCardPanel = null, playerCardPanel = null,player2CardPanel = null;
	private boolean bust=false;
	public MainPage()
	{
		ImagePanel bgImagePanel = new ImagePanel("background.png");
		bgImagePanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		setContentPane(bgImagePanel);
		blackjack=new Blackjack(1);
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		createBtn();
		createResult();
		panel1.add(scrollPane);
		getContentPane().add(hitBtn);
		getContentPane().add(standBtn);
		getContentPane().add(doubleBtn);
		getContentPane().add(divideBtn);
		getContentPane().add(hit2Btn);
		getContentPane().add(stand2Btn);
		getContentPane().add(label);
		setResizable(false);
		add(panel1,BorderLayout.NORTH);
		add(panel2,BorderLayout.CENTER);
		add(panel3,BorderLayout.SOUTH);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
//		frame=new MoneyFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		frame.setAlwaysOnTop(true);
//		frame.setLocation(FRAME_WIDTH/2-245/2,FRAME_HEIGHT/2-245/2);
	}
	public MainPage(int a)
	{
		ImagePanel bgImagePanel = new ImagePanel("background.png");
		bgImagePanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		setContentPane(bgImagePanel);
		blackjack=new Blackjack();
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		createBtn();
		createResult();
		panel1.add(scrollPane);
		getContentPane().add(hitBtn);
		getContentPane().add(hitBtn);
		getContentPane().add(standBtn);
		getContentPane().add(doubleBtn);
		getContentPane().add(divideBtn);
		getContentPane().add(hit2Btn);
		getContentPane().add(stand2Btn);
//		getContentPane().add(label);
//		getContentPane().add(checkBox);
//		getContentPane().add(label1);
		setResizable(false);
		add(panel1,BorderLayout.NORTH);
		add(panel2,BorderLayout.CENTER);
		add(panel3,BorderLayout.SOUTH);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
	}
	public void createBtn()
	{
		hitBtn=new JButton("Hit");
		hitBtn.setBounds(FRAME_WIDTH/2-150, 505, 140, 35);
		class HitBtnActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				doubleBtn.setVisible(false);
				divideBtn.setVisible(false);
				label.setVisible(false);
//				label1.setVisible(false);
				blackjack.playerShuffle(false);
				resultArea.append(blackjack.playerCard(false)+"\n");
				if(!blackjack.secondPlayer())
				{
					getContentPane().remove(playerCardPanel);
					playerCardPanel = new CardGroupPanel(blackjack.playerCard(false), FRAME_WIDTH/2-(blackjack.playerCard(false).size() * 115-10)/2, 290, 105, 157, 10);
					getContentPane().add(playerCardPanel);
					getContentPane().remove(scoreLabel);
					scoreLabel.setText(blackjack.count(false)+"");
					scoreLabel.setForeground(Color.ORANGE);
					scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
					scoreLabel.setBounds(FRAME_WIDTH/2+(blackjack.playerCard(false).size() * 115-15)/2+10, 350, 272, 50);
					getContentPane().add(scoreLabel);
					repaint();
					blackjack.check(false);
				}
				else
				{
					getContentPane().remove(playerCardPanel);
					playerCardPanel = new CardGroupPanel(blackjack.playerCard(false), FRAME_WIDTH/4-(blackjack.playerCard(false).size() * 115-10)/2, 290, 105, 157, 10);
					getContentPane().add(playerCardPanel);
					getContentPane().remove(scoreLabel);
					scoreLabel.setText(blackjack.count(false)+"");
					scoreLabel.setForeground(Color.ORANGE);
					scoreLabel.setFont(new Font("Arial", Font.BOLD, 12));
					scoreLabel.setBounds(FRAME_WIDTH/4+(blackjack.playerCard(false).size() * 115-15)/2+10, 350, 272, 50);
					getContentPane().add(scoreLabel);
					repaint();
					if(blackjack.check(false))
					{
						bust=true;
					}
				}
//				resultArea.append(blackjack.count(false));
				
			}
		}
		hitBtn.addActionListener(new HitBtnActionListener());
		hitBtn.setEnabled(false);
		hitBtn.requestFocus();
		standBtn=new JButton("Stand");
		standBtn.setBounds(FRAME_WIDTH/2+10, 505, 140, 35);
		class StandBtnActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				doubleBtn.setVisible(false);
				label.setVisible(false);
				divideBtn.setVisible(false);
//				label1.setVisible(false);
				standBtn.setEnabled(false);
				hitBtn.setEnabled(false);
				blackjack.firstStand(true);
				blackjack.alreadyStand();
				if(!blackjack.secondPlayer())
				{
					blackjack.stand();
					getContentPane().remove(dealerCardPanel);
					dealerCardPanel = new CardGroupPanel(blackjack.dealerCard(), FRAME_WIDTH/2-(blackjack.dealerCard().size() * 115-10)/2, 50, 105, 157, 10);
					getContentPane().add(dealerCardPanel);
					repaint();
					blackjack.compare(1);
				}
				if(blackjack.bothStand())
				{
					blackjack.stand();
					getContentPane().remove(dealerCardPanel);
					dealerCardPanel = new CardGroupPanel(blackjack.dealerCard(), FRAME_WIDTH/2-(blackjack.dealerCard().size() * 115-10)/2, 50, 105, 157, 10);
					getContentPane().add(dealerCardPanel);
					repaint();
					blackjack.compare(0);
				}
				if(bust)
				{
					blackjack.stand();
					getContentPane().remove(dealerCardPanel);
					dealerCardPanel = new CardGroupPanel(blackjack.dealerCard(), FRAME_WIDTH/2-(blackjack.dealerCard().size() * 115-10)/2, 50, 105, 157, 10);
					getContentPane().add(dealerCardPanel);
					repaint();
					blackjack.compare(1);
				}
			}
		}
		standBtn.addActionListener(new StandBtnActionListener());
		standBtn.setEnabled(false);
//		standBtn.requestFocus();
		divideBtn=new JButton("Spilt");
		divideBtn.setBounds(FRAME_WIDTH/2-70,550,140,35);
		divideBtn.setVisible(false);
		class DivideActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				betAmount.setText("$"+blackjack.getMoney().getBet()*2);
				bankAmount.setText("Your Bank: "+(blackjack.getMoney().showTotal()-blackjack.getMoney().getBet()*2));
				doubleBtn.setVisible(false);
				divideBtn.setVisible(false);
				hit2Btn.setVisible(true);
				stand2Btn.setVisible(true);
//				label1.setVisible(false);
				blackjack.createPlayer2();
				resultArea.append(blackjack.playerCard(false)+"\n"+blackjack.playerCard(true)+"\n");
				getContentPane().remove(playerCardPanel);
				playerCardPanel = new CardGroupPanel(blackjack.playerCard(false), FRAME_WIDTH/4-(blackjack.playerCard(false).size() * 115-10)/2, 290, 105, 157, 10);
				getContentPane().add(playerCardPanel);
				getContentPane().remove(scoreLabel);
				scoreLabel.setText(blackjack.count(false)+"");
				scoreLabel.setForeground(Color.ORANGE);
				scoreLabel.setFont(new Font("Arial", Font.BOLD, 12));
				scoreLabel.setBounds(FRAME_WIDTH/4+(blackjack.playerCard(false).size() * 115-15)/2+10, 350, 272, 50);
				getContentPane().add(scoreLabel);
				player2CardPanel = new CardGroupPanel(blackjack.playerCard(true), FRAME_WIDTH*3/4-(blackjack.playerCard(true).size() * 115-10)/2, 290, 105, 157, 10);
				getContentPane().add(player2CardPanel);
				scoreLabel2.setText(blackjack.count(true)+"");
				scoreLabel2.setForeground(Color.ORANGE);
				scoreLabel2.setFont(new Font("Arial", Font.BOLD, 12));
				scoreLabel2.setBounds(FRAME_WIDTH*3/4+(blackjack.playerCard(true).size() * 115-15)/2+10, 350, 272, 50);
				getContentPane().add(scoreLabel2);
				repaint();
				
			}
		}
		divideBtn.addActionListener(new DivideActionListener());
		hit2Btn=new JButton("Hit");
		hit2Btn.setBounds(FRAME_WIDTH/2-150, 550, 140, 35);
		hit2Btn.setVisible(false);
		class Hit2BtnActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				blackjack.playerShuffle(true);
				resultArea.append(blackjack.playerCard(true)+"\n");
				getContentPane().remove(player2CardPanel);
				player2CardPanel = new CardGroupPanel(blackjack.playerCard(true), FRAME_WIDTH*3/4-(blackjack.playerCard(true).size() * 115-10)/2, 290, 105, 157, 10);
				getContentPane().add(player2CardPanel);
				getContentPane().remove(scoreLabel2);
				scoreLabel2.setText(blackjack.count(true)+"");
				scoreLabel2.setForeground(Color.ORANGE);
				scoreLabel2.setFont(new Font("Arial", Font.BOLD, 12));
				scoreLabel2.setBounds(FRAME_WIDTH*3/4+(blackjack.playerCard(true).size() * 115-15)/2+10, 350, 272, 50);
				getContentPane().add(scoreLabel2);
				repaint();
				resultArea.append(blackjack.count(true));
				if(blackjack.check(true))
				{
					bust=true;
				}
			}
		}
		hit2Btn.addActionListener(new Hit2BtnActionListener());
		stand2Btn=new JButton("Stand");
		stand2Btn.setBounds(FRAME_WIDTH/2+10, 550, 140, 35);
		stand2Btn.setVisible(false);
		class Stand2BtnActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{	
				hit2Btn.setEnabled(false);
				stand2Btn.setEnabled(false);
				blackjack.secondStand(true);
				blackjack.alreadyStand2();
				if(blackjack.bothStand())
				{
					resultArea.append(blackjack.stand());
					getContentPane().remove(dealerCardPanel);
					dealerCardPanel = new CardGroupPanel(blackjack.dealerCard(), FRAME_WIDTH/2-(blackjack.dealerCard().size() * 115-10)/2, 50, 105, 157, 10);
					getContentPane().add(dealerCardPanel);
					repaint();
					blackjack.compare(0);
				}
				if(bust)
				{
					resultArea.append(blackjack.stand());
					getContentPane().remove(dealerCardPanel);
					dealerCardPanel = new CardGroupPanel(blackjack.dealerCard(), FRAME_WIDTH/2-(blackjack.dealerCard().size() * 115-10)/2, 50, 105, 157, 10);
					getContentPane().add(dealerCardPanel);
					repaint();
					blackjack.compare(2);
				}
			}
		}
		stand2Btn.addActionListener(new Stand2BtnActionListener());
		label=new JLabel("");
		label.setFont(new java.awt.Font("Dialog",1,15));
		label.setVisible(false);
		doubleBtn=new JButton("Double");
		doubleBtn.setBounds(FRAME_WIDTH/2-70,460,140,35);
		doubleBtn.setVisible(false);
		class DoubleBtnActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				blackjack.getMoney().setDou();
				betAmount.setText("$"+blackjack.getMoney().getBet()*2);
				bankAmount.setText("Your Bank: "+(blackjack.getMoney().showTotal()-blackjack.getMoney().getBet()*2));
				doubleBtn.setVisible(false);
//				label1.setVisible(false);
				blackjack.playerShuffle(false);
//				resultArea.append(blackjack.playerCard(false)+"\n");
				getContentPane().remove(playerCardPanel);
				playerCardPanel = new CardGroupPanel(blackjack.playerCard(false), FRAME_WIDTH/2-(blackjack.playerCard(false).size() * 115-10)/2, 290, 105, 157, 10);
				getContentPane().add(playerCardPanel);
				scoreLabel.setText(blackjack.count(false)+"");
				scoreLabel.setForeground(Color.ORANGE);
				scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
				scoreLabel.setBounds(FRAME_WIDTH/2+(blackjack.playerCard(false).size() * 115-15)/2+10, 350, 272, 50);
				getContentPane().add(scoreLabel);
//				resultArea.append(blackjack.count(false));
				if(!blackjack.check(false))
				{	
					blackjack.stand();
					getContentPane().remove(dealerCardPanel);
					dealerCardPanel = new CardGroupPanel(blackjack.dealerCard(), FRAME_WIDTH/2-(blackjack.dealerCard().size() * 115-10)/2, 50, 105, 157, 10);
					getContentPane().add(dealerCardPanel);
					repaint();
					blackjack.compare(1);
				}
			}
		}
		doubleBtn.addActionListener(new DoubleBtnActionListener());
//		checkBox=new JCheckBox("");
//		checkBox.setBounds(70,505,75,25);
//		class CheckBoxActionListener implements ActionListener
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				if(checkBox.isSelected())
//				{
//					label1.setVisible(true);
//				}
//				else
//				{
//					label1.setVisible(false);
//				}
//			}
//		}
//		checkBox.addActionListener(new CheckBoxActionListener());
//		checkBox.setBounds(50,550,20,20);
//		getContentPane().add(checkBox);
//		label1=new JLabel("");
//		label1.setFont(new Font("Arial", Font.BOLD, 20));
//		label1.setForeground(Color.ORANGE);
//		label1.setHorizontalAlignment(SwingConstants.CENTER);
//		label1.setBounds(10, 570,275, 50);
//		getContentPane().add(label1);
//		if(checkBox.isSelected())
//		{
//			label1.setVisible(true);
//		}
//		else
//		{
//			label1.setVisible(false);
//		}
//		JLabel rookieLabel=new JLabel("新手教學");
//		rookieLabel.setFont(new Font(null, Font.BOLD, 20));
//		rookieLabel.setForeground(Color.WHITE);
//		rookieLabel.setBounds(75,545,100,30);
//		getContentPane().add(rookieLabel);
		JLabel betLabel=new JLabel("BET:");
		betLabel.setFont(new Font(null, Font.BOLD, 20));
		betLabel.setForeground(Color.WHITE);
		betLabel.setBounds(FRAME_WIDTH-120,500,100,30);
		getContentPane().add(betLabel);
		betAmount=new JLabel();
		betAmount.setText("$"+blackjack.getMoney().getBet());
		betAmount.setFont(new Font(null, Font.BOLD, 30));
		betAmount.setForeground(Color.ORANGE);
		betAmount.setBounds(FRAME_WIDTH-120,530,110,30);
		getContentPane().add(betAmount);
		bankAmount=new JLabel();
		bankAmount.setText("Your Bank: "+(blackjack.getMoney().showTotal()-blackjack.getMoney().getBet()));
		bankAmount.setFont(new Font(null, Font.BOLD, 30));
		bankAmount.setForeground(Color.ORANGE);
		bankAmount.setBounds(FRAME_WIDTH/2-120,600,400,30);
		getContentPane().add(bankAmount);
	}
	public void stand(int a)
	{
		if(a==1)
		{
//			resultArea.append(blackjack.stand());
			getContentPane().remove(dealerCardPanel);
			dealerCardPanel = new CardGroupPanel(blackjack.dealerCard(), FRAME_WIDTH/2-(blackjack.dealerCard().size() * 115-10)/2, 50, 105, 157, 10);
			getContentPane().add(dealerCardPanel);
			repaint();
			blackjack.compare(2);
		}
		else if(a==2)
		{
//			resultArea.append(blackjack.stand());
			getContentPane().remove(dealerCardPanel);
			dealerCardPanel = new CardGroupPanel(blackjack.dealerCard(), FRAME_WIDTH/2-(blackjack.dealerCard().size() * 115-10)/2, 50, 105, 157, 10);
			getContentPane().add(dealerCardPanel);
			repaint();
			blackjack.compare(3);
		}
	}
	public void createResult()
	{
		resultArea=new JTextArea(AREA_HEIGHT,AREA_WIDTH);
		resultArea.setEditable(false);
		scrollPane=new JScrollPane(resultArea);
		resultArea.setLineWrap(true);
		resultArea.setCaretPosition(resultArea.getDocument().getLength());
	}
	public void createFirst()
	{
		betAmount.setText("$"+blackjack.getMoney().getBet());
		bankAmount.setText("Your Bank: "+(blackjack.getMoney().showTotal()-blackjack.getMoney().getBet()));
		hitBtn.setEnabled(true);
		standBtn.setEnabled(true);
		blackjack.playerShuffle(false);
		blackjack.playerShuffle(false);
		resultArea.append("\nGame Start\n");
//		resultArea.append("\nYour card:"+blackjack.playerCard(false)+"\n"+blackjack.count(false));
		playerCardPanel = new CardGroupPanel(blackjack.playerCard(false), FRAME_WIDTH/2-(blackjack.playerCard(false).size() * 115-10)/2, 290, 105, 157, 10);
		getContentPane().add(playerCardPanel);
		scoreLabel.setText(blackjack.count(false)+"");
		scoreLabel.setForeground(Color.ORANGE);
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
		scoreLabel.setBounds(FRAME_WIDTH/2+(blackjack.playerCard(false).size() * 115-10)/2+15, 350, 272, 50);
		getContentPane().add(scoreLabel);
		repaint();
		blackjack.dealerShuffle();
		blackjack.dealerShuffle();
//		if(checkBox.isSelected())
//		{
//			label1.setVisible(true);
//		}
//		else
//		{
//			label1.setVisible(false);
//		}
		resultArea.append("Dealer card:"+blackjack.dealerFirstCard()+"\n\n");
		dealerCardPanel = new CardGroupPanel(blackjack.dealerCard(), FRAME_WIDTH/2 - (blackjack.dealerCard().size() * 115-10)/2, 50, 105, 157, 10,true);
		getContentPane().add(dealerCardPanel);
		repaint();
//		Odd odd=new Odd();
//		try 
//		{
//			String str=odd.getMove(blackjack.getPlayerCard()[0],blackjack.getPlayerCard()[1],blackjack.getDealerFirstCardValue());
//			label1.setText("Recommend:"+str);
//		} 
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//		}
		if(!blackjack.firstCompare().equals(""))
		{
			getContentPane().remove(dealerCardPanel);
			dealerCardPanel = new CardGroupPanel(blackjack.dealerCard(), FRAME_WIDTH/2-(blackjack.dealerCard().size() * 115-10)/2, 50, 105, 157, 10);
			getContentPane().add(dealerCardPanel);
			repaint();
			resultArea.append("Dealer card:"+blackjack.firstCompare()+"\n");
		}
		blackjack.firstCompareSign();
		if(blackjack.checkDouble())
		{
			if(blackjack.getMoney().showTotal()>=blackjack.getMoney().getBet()*2)
			{
				divideBtn.setVisible(true);
				doubleBtn.setVisible(true);
			}
//			else
//			{
//				label.setText("You don't have enough money to split and double");
//				label.setVisible(true);
//			}
		}
		else
		{
			if(blackjack.getMoney().showTotal()>=blackjack.getMoney().getBet()*2)
			{
				doubleBtn.setVisible(true);
			}
//			else
//			{
//				label.setText("You don't have enough money to double");
//				label.setVisible(true);
//			}
		}
	}
	public Blackjack getBlackjack()
	{
		return blackjack;
	}
	public void removeAll()
	{
		getContentPane().remove(playerCardPanel);
		if(blackjack.secondPlayer())
		{
			getContentPane().remove(player2CardPanel);
			getContentPane().remove(scoreLabel2);
		}
		betAmount.setText("$"+0);
		getContentPane().remove(dealerCardPanel);
		getContentPane().remove(scoreLabel);
		bankAmount.setText("Your Bank: "+blackjack.getMoney().showTotal());
		repaint();
		bust=false;
	}
}
