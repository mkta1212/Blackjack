import java.util.ArrayList;

	public class Dealer {
	private ArrayList<Card>cards;
	
	public Dealer()
	{
		cards=new ArrayList<Card>();
	}
	
	public void addDealerCard(int suit,int value)
	{
		
		cards.add(new Card(suit,value));
	}
	public ArrayList<Card> getDealerCard()
	{
		return cards;
	}
	public int getDealerAces()
	{
		int acenum=0;
		for(Card cards:cards)
		{
			if(cards.getValue()==1)
			{
				acenum++;
			}
		}
		return acenum;
	}
	public int getDealerTen()
	{
		int tenNum=0;
		for(Card cards:cards)
		{
			if(cards.getValue()>=10)
			{
				tenNum++;
			}
		}
		return tenNum;
	}
	public int getDealerSum()
	{	
		int x=0;
		for(Card cards:cards)
		{
			if(cards.getValue()>10)
			{
				
				x+=10;
			}
			else
			{
				x+=cards.getValue();
			}
		}
		return x;
	}
	
	public String currentCard()
	{
		String str="["+cards.get(1).toString()+"]";
		return str;
	}
	public int currentCardValue()
	{
		int a=cards.get(1).getValue();
		if(a>10)
		{
			a=10;
		}
		return a;
	}
	public String toString()
	{
		String str="";
		for(int i=0;i<cards.size();i++)
		{
			str+='['+cards.get(i).toString()+']';
		}
		return str;
	}
}