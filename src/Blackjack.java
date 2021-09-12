import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
public class Blackjack {
	private Deck deck;
	private Player player,player2;
	private Dealer dealer;
	private WinorLose sign;
	private AfterDivide sign2;
	private boolean divided=false;
	private boolean firstStand=false;
	private boolean secondStand=false;
	private boolean alreadyStand=false;
	private boolean alreadyStand2=false;
	private boolean firstFinish=false;
	private boolean secondFinish=false;
	private static Money money;
	public Blackjack(int a)
	{
		deck=new Deck();
		player=new Player();
		dealer=new Dealer();
		money=new Money();
	}
	public Blackjack()
	{
		deck=new Deck();
		player=new Player();
		dealer=new Dealer();
	}
	public void playerShuffle(boolean divide)
	{
//		player.addPlayerCard(0,2); 
		if(divide)
		{
			player2.addPlayerCard(deck.randomCardSuit(),deck.randomCardValue());
		}
		else
		{
//			player.addPlayerCard(0,1);
//			player.addPlayerCard(0,10);
			player.addPlayerCard(deck.randomCardSuit(),deck.randomCardValue());
		}
	}
	public void dealerShuffle()
	{
//		dealer.addDealerCard(0,1);
//		dealer.addDealerCard(0,10);
		dealer.addDealerCard(deck.randomCardSuit(),deck.randomCardValue());
	}
	public ArrayList<Card> playerCard(boolean divide)
	{
		if(divide)
		{
			return player2.getPlayerCard();
		}
		else
		{
			return player.getPlayerCard();
		}
	}
	public int[] getPlayerCard()
	{
		int[] a=new int[2];
		a=player.getPlayerCardValue();
		return a;
	}
	public String dealerFirstCard()
	{
		return dealer.currentCard();
	}
	public int getDealerFirstCardValue()
	{
		return dealer.currentCardValue();
	}
	public ArrayList<Card> dealerCard()
	{
		return dealer.getDealerCard();
	}
	public int playerSum()
	{
		return player.getPlayerSum();
	}
	public int dealerSum()
	{
		return dealer.getDealerSum();
	}
	public void createPlayer2()
	{
		player2=new Player();
		divided=true;
		player2.addPlayerCard(player.getPlayerCard().get(1).getSuit(),player.getPlayerCard().get(1).getValue());
		player.remove();
	}
	public boolean secondPlayer()
	{
		if(divided)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean checkDouble()
	{
		ArrayList<Card> cardList=player.getPlayerCard();
		Card card1=cardList.get(0);
		Card card2=cardList.get(1);
		if(card1.getValue()==card2.getValue())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public String firstCompare()
	{
		if(dealer.getDealerAces()==1&&dealer.getDealerTen()==1)
		{
			return dealerCard()+"\nthe dealer's total is 21";
		}
		else
		{
			return "";
		}
	}
	public void firstCompareSign()
	{
		if(player.getPlayerAces()==1&&player.getPlayerTen()==1)
		{
			if(dealer.getDealerAces()==1&&dealer.getDealerTen()==1)
			{
				money.loseB();
				sign=new WinorLose(2);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setLocation(640-32-60,285);
				sign.setVisible(true);
			}
			else
			{
				money.winB();
				sign=new WinorLose(1);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
		}
		else if(dealer.getDealerAces()==1&&dealer.getDealerTen()==1)
		{
			money.loseB();
			sign=new WinorLose(2);
			sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			sign.setAlwaysOnTop(true);
			sign.setVisible(true);
			sign.setLocation(640-32-60,285);
		}
	}
	public void alreadyStand()
	{
		alreadyStand=true;
	}
	public void alreadyStand2()
	{
		alreadyStand2=true;
	}
	public boolean check(boolean divide)
	{
		if(divide)
		{
			if(player2.getPlayerSum()>21)
			{
				if(alreadyStand)
				{
					new FrontPage().getMainPage().stand(1);
				}
				else
				{
					FrontPage frontPage=new FrontPage();
					frontPage.getMainPage().hit2Btn.setEnabled(false);
					frontPage.getMainPage().stand2Btn.setEnabled(false);
					firstFinish(true);
					if(bothFinish())
					{
						money.loseB();
						sign=new WinorLose(2);
						sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						sign.setAlwaysOnTop(true);
						sign.setVisible(true);
						sign.setLocation(640-32-60,285);
						return true;
					}
					else
					{
						sign2=new AfterDivide(2);
						sign2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						sign2.setAlwaysOnTop(true);
						sign2.setVisible(true);
						sign2.setLocation(640-32-60,285);
						return true;
					}
				}
			}
		}
		else
		{
			if(player.getPlayerSum()>21)
			{
				if(alreadyStand2)
				{
					new FrontPage().getMainPage().stand(2);
				}
				else
				{
					FrontPage frontPage=new FrontPage();
					frontPage.getMainPage().hitBtn.setEnabled(false);
					frontPage.getMainPage().standBtn.setEnabled(false);
					secondFinish(true);
					if(secondPlayer())
					{
						if(bothFinish())
						{
							money.loseB();
							sign=new WinorLose(2);
							sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							sign.setAlwaysOnTop(true);
							sign.setVisible(true);
							sign.setLocation(640-32-60,285);
							return true;
						}
						else
						{
							sign2=new AfterDivide(2);
							sign2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							sign2.setAlwaysOnTop(true);
							sign2.setVisible(true);
							sign2.setLocation(640-32-60,285);
							return true;
						}
					}
					else
					{
						money.lose();
						sign=new WinorLose(2);
						sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						sign.setAlwaysOnTop(true);
						sign.setVisible(true);
						sign.setLocation(640-32-60,285);
						return true;
					}
				}
			}
		}
		return false;
	}
	public void firstFinish(boolean valid)
	{
		if(valid) 
		{
			firstFinish=true;
		}
	}
	public void secondFinish(boolean valid)
	{
		if(valid) 
		{
			secondFinish=true;
		}
	}
	public boolean bothFinish()
	{
		if(firstFinish&&secondFinish)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public String count(boolean divide)
	{
		int playersum=player.getPlayerSum();
		if(divide)
		{
			int playerSum2=player2.getPlayerSum();
			String str="";
			if(player2.getPlayerAces()==1)
			{
				if(player2.getPlayerSum()+10<=21)
				{
					playerSum2+=10;
					
				}
			}
			if(player2.getPlayerAces()==2)
			{
				int b=playerSum2+10;
				if(b<=21)
				{
					str+=playerSum2+" or "+b+"\n";
				}
				else
				{
					str+=playerSum2+"\n";
				}
			}
			else
			{		
				str+=playerSum2+"\n";
			}
			return str;
		}
		else
		{
			if(player.getPlayerAces()==1&&player.getPlayerTen()==1&&player.onlyTwo())
			{
				return 21+"";
			}
			if(player.getPlayerAces()==1)
			{
				if(player.getPlayerSum()+10<=21)
				{
					playersum+=10;
					return (playersum-10)+" or "+playersum+"\n";
				}
			}
			if(player.getPlayerAces()==2)
			{
				int b=playersum+10;
				if(b<=21)
				{
					return playersum+" or "+b+"\n";
				}
				else
				{
					return playersum+"\n";
				}
			}
			else
			{		
				return playersum+"\n";
			}
		}
	}
	public void compare(int a)
	{		
		int dealerSum=dealer.getDealerSum();
		int playerSum=player.getPlayerSum();
		if(dealer.getDealerAces()==0)
		{
			while(dealerSum<17)
			{
				dealer.addDealerCard(deck.randomCardSuit(),deck.randomCardValue());
				dealerSum=dealer.getDealerSum();
			}
		}
		else
		{
			if(dealer.getDealerSum()+10<=21)
			{
				dealerSum+=10;
				while(dealerSum<17)
				{
					dealer.addDealerCard(deck.randomCardSuit(),deck.randomCardValue());
					dealerSum=dealer.getDealerSum();
				}
			}		
		}
		if(a==0)
		{
			int playerSum2=player2.getPlayerSum();
			if(player.getPlayerAces()>=1)
			{
				if(playerSum+10<=21)
				{
					playerSum+=10;
				}
			}
			if(player2.getPlayerAces()>=1)
			{
				if(playerSum2+10<=21)
				{
					playerSum2+=10;
				}
			}
			if(playerSum>dealerSum&&playerSum2>dealerSum)
			{	
				money.winB();
				sign=new WinorLose(1);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
			else if(dealerSum>21)
			{	
				money.winB();
				sign=new WinorLose(1);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);					
			}
			else if(playerSum>dealerSum&&playerSum2<dealerSum)
			{
				sign=new WinorLose(4);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
			else if(playerSum<dealerSum&&playerSum2>dealerSum)
			{
				sign=new WinorLose(4);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
			else if(dealerSum>playerSum&&dealerSum>playerSum2)
			{
				money.loseB();
				sign=new WinorLose(2);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);	
			}
			else if(dealerSum==playerSum&&dealerSum==playerSum2)
			{
				sign=new WinorLose(3);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
			else if(dealerSum==playerSum&&dealerSum>playerSum2)
			{
				money.lose();
				sign=new WinorLose(5);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
			else if(dealerSum>playerSum&&dealerSum==playerSum2)
			{
				money.lose();
				sign=new WinorLose(5);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
			else if(dealerSum==playerSum&&dealerSum<playerSum2)
			{
				money.win();
				sign=new WinorLose(6);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
			else if(dealerSum<playerSum&&dealerSum==playerSum2)
			{
				money.win();
				sign=new WinorLose(6);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
		}
		else if(a==1)
		{
			if(player.getPlayerAces()>=1)
			{
				if(playerSum+10<=21)
				{
					playerSum+=10;
				}
			}
			if(playerSum>dealerSum)
			{	
				money.win();
				sign=new WinorLose(1);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
			else if(dealerSum>playerSum)
			{	
				if(dealerSum>21)
				{
					money.win();
					sign=new WinorLose(1);
					sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					sign.setAlwaysOnTop(true);
					sign.setVisible(true);
					sign.setLocation(640-32-60,285);
				}
				else
				{	
					money.lose();
					sign=new WinorLose(2);
					sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					sign.setAlwaysOnTop(true);
					sign.setVisible(true);
					sign.setLocation(640-32-60,285);
				}					
			}
			else
			{
				sign=new WinorLose(3);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
		}
		else if(a==2)
		{
			if(player.getPlayerAces()>=1)
			{
				if(playerSum+10<=21)
				{
					playerSum+=10;
				}
			}
			if(playerSum>dealerSum)
			{			
				sign=new WinorLose(4);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
			else if(dealerSum>playerSum)
			{	
				if(dealerSum>21)
				{
					sign=new WinorLose(4);
					sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					sign.setAlwaysOnTop(true);
					sign.setVisible(true);
					sign.setLocation(640-32-60,285);
				}
				else
				{	
					money.loseB();
					sign=new WinorLose(2);
					sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					sign.setAlwaysOnTop(true);
					sign.setVisible(true);
					sign.setLocation(640-32-60,285);
				}					
			}
			else
			{
				money.lose();
				sign=new WinorLose(5);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
		}
		else if(a==3)
		{
			int playerSum2=player2.getPlayerSum();
			if(player2.getPlayerAces()>=1)
			{
				if(playerSum2+10<=21)
				{
					playerSum2+=10;
				}
			}
			if(playerSum2>dealerSum)
			{			
				sign=new WinorLose(4);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
			else if(dealerSum>playerSum2)
			{	
				if(dealerSum>21)
				{
					sign=new WinorLose(4);
					sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					sign.setAlwaysOnTop(true);
					sign.setVisible(true);
					sign.setLocation(640-32-60,285);
				}
				else
				{	
					money.loseB();
					sign=new WinorLose(2);
					sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					sign.setAlwaysOnTop(true);
					sign.setVisible(true);
					sign.setLocation(640-32-60,285);
				}					
			}
			else
			{
				money.lose();
				sign=new WinorLose(5);
				sign.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sign.setAlwaysOnTop(true);
				sign.setVisible(true);
				sign.setLocation(640-32-60,285);
			}
		}
	}
	public String stand()
	{
		int dealerSum=dealer.getDealerSum();
		if(dealer.getDealerAces()==0)
		{
			while(dealerSum<17)
			{
				dealer.addDealerCard(deck.randomCardSuit(),deck.randomCardValue());
				dealerSum=dealer.getDealerSum();
			}
			return dealerCard()+"\nthe dealer's total is "+dealerSum+"\n";
		}
		else
		{
			if(dealer.getDealerSum()+10<=21)
			{
				dealerSum+=10;
				while(dealerSum<17)
				{
					dealer.addDealerCard(deck.randomCardSuit(),deck.randomCardValue());
					dealerSum=dealer.getDealerSum();
				}
				return dealerCard()+"\nthe dealer's total is "+dealerSum+"\n";
			}		
		}
		return null;
								
		
	}
	public void firstStand(boolean valid)
	{
		if(valid)
		{
			firstStand=true;
		}
		
	}
	public void secondStand(boolean valid)
	{
		if(valid)
		{
			secondStand=true;
		}
	}
	public boolean bothStand()
	{
		if(firstStand&&secondStand)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void removeAll()
	{
		player=new Player();
		if(divided)
		{
			player2=new Player();
			divided=false;
		}
		dealer=new Dealer();
	}
	public boolean startAgain()
	{
		sign=new WinorLose();
		return sign.startAgain();
	}
	public Money getMoney()
	{
		return money;
	}
}
