import java.util.ArrayList;
public class Player {
	private ArrayList<Card>cards;
	public Player()
	{
		cards=new ArrayList<Card>();
	}
	
	public void addPlayerCard(int suit,int value)
	{
		cards.add(new Card(suit,value));
	}
	public ArrayList<Card> getPlayerCard()
	{
		return cards;
	}
	public int getPlayerAces()
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
	public int getPlayerTen()
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
	public boolean onlyTwo()
	{
		if(cards.size()==2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int getPlayerSum()
	{	
		int x=0;
		for(Card cards:cards)
		{
			if(cards.getValue()>10)
			{
				x+=10;
			}else
			{
				x+=cards.getValue();
			}
		}
		return x;
	}
	public void remove()
	{
		cards.remove(1);
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
	public int[] getPlayerCardValue()
	{
		int[] a=new int[2];
		for(int i=0;i<cards.size();i++)
		{
			int b=cards.get(i).getValue();
			if(b>10)
			{
				b=10;
			}
			a[i]=b;
		}
		return a;
	}
}