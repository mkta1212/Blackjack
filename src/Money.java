
public class Money {
	private int money;
	private int bet;
	private boolean dou;
	public Money()
	{
		money=2000;
		bet=0;
		dou=false;
	}
	public void setBet(int a)
	{
		bet=a;
	}
	public void setDou()
	{
		dou=true;
	}
	public void resetDou()
	{
		dou=false;
	}
	public int getBet()
	{
		return bet;
	}
	public void win()
	{
		if(dou)
		{
			money+=(bet*2);
		}
		else
		{
			money+=bet;
		}
	}
	public void lose()
	{
		if(dou)
		{
			money-=(bet*2);
		}
		else
		{
			money-=bet;
		}
	}
	public void winB()
	{
		money+=(bet*2);
	}
	public void loseB()
	{
		money-=(bet*2);
	}
	public int showTotal()
	{
		return money;
	}
}
