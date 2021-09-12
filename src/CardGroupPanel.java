import java.util.ArrayList;

import javax.swing.JPanel;

public class CardGroupPanel extends JPanel{
	CardGroupPanel(ArrayList<Card> cards, int left, int top, int width, int height, int gap)
	{

		int numCards=cards.size();

		setBounds(left, top, 35 + numCards * (width + gap), height);
		setLayout(null);
		setOpaque(false); 
		for (int i = 0; i < numCards; i++) {
			
			ImagePanel cardImagePanel = new ImagePanel(cards.get(i).getFileName(1));
			cardImagePanel.setBounds(i * (width + gap), 0, width, height);
			add(cardImagePanel);
		}
	}
	CardGroupPanel(ArrayList<Card> cards, int left, int top, int width, int height, int gap, boolean valid)
	{

		int numCards=cards.size();

		setBounds(left, top, 35 + numCards * (width + gap), height);
		setLayout(null);
		setOpaque(false); 
		for (int i = 0; i < numCards; i++) {
			
			ImagePanel cardImagePanel = new ImagePanel(cards.get(i).getFileName(i));
			cardImagePanel.setBounds(i * (width + gap), 0, width, height);
			add(cardImagePanel);
		}
	}
}