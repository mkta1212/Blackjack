import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Odd {
	String server = "jdbc:mysql://140.119.19.73:9306/";
	String database = "TG11";
	String config= "?useUnicode=true&characterEncoding=utf8";
	String url = server + database + config;
	String username = "TG11";
	String password = "9dwudz";
	Connection conn = null;
	public Odd()
	{
		
	}
	public String getMove(int a,int b,int c) throws SQLException
	{
		try
		{
			if(a==1||b==1)
			{
				if(b==1)
				{
					b=a;
					a=1;
				}
				conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stat=conn.prepareStatement("SELECT Move FROM Blackjack2 WHERE Player1=? AND Player2=? AND Dealer_card=?");
				stat.setInt(1,a);
				stat.setInt(2,b);
				stat.setInt(3,c);
				ResultSet result=stat.executeQuery();
				if(result.next())
				{
					return result.getString(1);
				}
			}
			else if(a==b)
			{
				conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stat=conn.prepareStatement("SELECT Move FROM Blackjack2 WHERE Player1=? AND Player2=? AND Dealer_card=?");
				stat.setInt(1,a);
				stat.setInt(2,b);
				stat.setInt(3,c);
				ResultSet result=stat.executeQuery();
				if(result.next())
				{
					return result.getString(1);
				}
			}
			else
			{
				conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stat=conn.prepareStatement("SELECT Move FROM Blackjack1 WHERE Player_total=? AND Dealer_card=?");
				stat.setInt(1,a+b);
				stat.setInt(2,c);
				ResultSet result=stat.executeQuery();
				if(result.next())
				{
					return result.getString(1);
				}
			}
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch(NullPointerException exception)
			{
				JOptionPane.showMessageDialog(null,"No Internet Connection");
			}
		}
		return "Stand";
	}
}
