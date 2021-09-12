
public class Card {
	private int suit,value;
	public Card(int suit,int value)
	{
		this.suit=suit;
		this.value=value;
	}
	public int getValue()
	{
		return value;
	}
	public int getSuit()
	{
		return suit;
	}
	public String toString()
	{
		return suit+" "+value;
	}
	public String getFileName(int a)
	{
		if (a == 0)
		{
			return "cardImages/backCover.png";
		}
		else
		{
			return String.format("cardImages/%s/%s.png", this.suit, this.value);
		}
	}
}
