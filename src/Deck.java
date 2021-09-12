import java.util.ArrayList;
public class Deck {
	private ArrayList<Card> cards;
	public Deck()
	{
		cards=new ArrayList<Card>();
	}
	public void createFullCard()
	{
		for(int i=0;i<=3;i++)
		{
			for(int j=1;j<=13;j++)
			{
				cards.add(new Card(i,j));
			}
		}
	}
	public int randomCardSuit()
	{
		return (int)(Math.random()*(3-0+1))+0;
	}
	public int randomCardValue()
	{
		return (int)(Math.random()*(13-1+1))+1;
	}
}