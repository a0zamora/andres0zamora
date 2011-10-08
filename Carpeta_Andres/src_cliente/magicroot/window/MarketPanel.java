package magicroot.window;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;



public class MarketPanel extends ImagePanel {

	
	private ActivePlayer player;
	private JButton storeButton;
	private JButton offerCardsButton;
	private JButton changeCardsButton;
	
	public MarketPanel(int w , int h,ActivePlayer aPlayer) {
		
		setMyBorder();
		player = aPlayer;
		wid =w;
		hei =h;
		setSize(wid, hei);
		changeImage("CardsPNG/Fondonegro.png");
		storeButton = new JButton(new ImageIcon("CardsPNG/mercado.png"));
		storeButton.setBounds((int)(wid*0.01),(int)(hei*0.01),(int)(wid*0.4),(int)(hei*0.5));
		storeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				StorePanel storePanel = new StorePanel(wid, hei, player);
				add(storePanel);
				storeButton.setVisible(false);
				offerCardsButton.setVisible(false);
				changeCardsButton.setVisible(false);
				repaint();
				
			}
		});
		storeButton.setBorder(null);
		storeButton.setOpaque(false);
		storeButton.setToolTipText("Comprar Carta");
		storeButton.setBackground(Color.black);
		add(storeButton);
	
		offerCardsButton = new JButton(new ImageIcon("CardsPNG/oferta.png"));
		offerCardsButton.setBounds((int)(wid*0.6),(int)(hei*0.1),(int)(wid*0.3),(int)(hei*0.4));
		offerCardsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(getComponentCount() !=0){
					remove(0);
				}
				OfferCardsPanel offerCardsPanel = new OfferCardsPanel(wid, hei, player,offerCardsButton);
				add(offerCardsPanel);
				storeButton.setVisible(false);
				offerCardsButton.setVisible(false);
				changeCardsButton.setVisible(false);
				repaint();
			}
		});
		offerCardsButton.setBorder(null);
		offerCardsButton.setOpaque(false);
		offerCardsButton.setToolTipText("Ofertar Carta");
		offerCardsButton.setBackground(Color.black);
		add(storeButton);
		add(offerCardsButton);
	
		changeCardsButton = new JButton(new ImageIcon("CardsPNG/manos.png"));
		changeCardsButton.setBounds((int)(wid*0.3),(int)(hei*0.5),(int)(wid*0.3),(int)(hei*0.4));
		changeCardsButton.setToolTipText("Cambiar Carta");
		changeCardsButton.setOpaque(false);
		changeCardsButton.setBackground(Color.black);
		changeCardsButton.setBorder(null);
		changeCardsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(getComponentCount() !=0){
					remove(0);
				}
				ChangeCardsPanel changeCardsPanel = new ChangeCardsPanel(wid, hei, player);
				add(changeCardsPanel);
				storeButton.setVisible(false);
				offerCardsButton.setVisible(false);
				changeCardsButton.setVisible(false);
				repaint();
			}
		});
		add(changeCardsButton);
	
	}
	
	public void setPlayer(ActivePlayer aPlayer){
		player = aPlayer;
	}
	
	
	
	
	
}
