import javax.swing.JFrame;

public class Viewer {

	public static void main(String[] args) {
		FrontPage frontPage=new FrontPage();
		frontPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frontPage.setVisible(true);
		frontPage.setLocation(320, 130);
	}

}
