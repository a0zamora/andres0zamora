package magicroot.window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class PageableCardsPanel extends ImagePanel {

	private CardPackage pack;
	private int actualPage;
	private int elementsperPage;
	private JButton nextButton;
	private JButton prevButton;
	private String nextButtonImg;
	private String prevButtonImg;
	private int col;
	private int row;
	private int actualCount=0;
	private ImagePanel listener;
	private boolean selectable=false;
	public PageableCardsPanel(int w, int h, int ele, int r, int c) {
		setSize(w, h);
		pack = new CardPackage(0, 0);
		prevButtonImg = "CardsPNG/flechaIzq.png";
		nextButtonImg = "CardsPNG/flechaDer.png";
		wid = w;
		hei = h;
		row =r;
		col =c;
		actualPage = 0;
		elementsperPage = ele;

		nextButton = new JButton();
		initButton(nextButton, nextButtonImg,
				 (int) (wid - (hei * 0.1)*1.33 - (wid * 0.02)),
				 (int) (hei - (hei * 0.10) - (hei * 0.02)) );
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actualPage++;
				paintPage();
				if(pack.getSize() <= actualCount)
					nextButton.setEnabled(false);
				prevButton.setEnabled(true);
			}
		});
		if(pack.getSize() <= 4)
			nextButton.setEnabled(false);
		add(nextButton);
	
		prevButton = new JButton();
		initButton(prevButton, prevButtonImg,
				(int) (wid * 0.02),
				(int) (hei - (hei * 0.10) - (hei * 0.02)) );
		
		prevButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actualPage--;
				paintPage();
			//	if(actualPage==0)
				if(actualCount<5)
					prevButton.setEnabled(false);
				nextButton.setEnabled(true);
			}
		});
		prevButton.setEnabled(false);
		add(prevButton);
		
	
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {	
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				PlayCard card;
				card = pack.cardOnPoint(e.getPoint());
				if(card!=null && card.getEnable()==true && selectable==true)
					((ImagePanel)listener).paintSelectedCard(card);
			}
		});
	}
	
	void paintPage(){
		PlayCard card;
		int j=0;
		int counter =actualPage*elementsperPage;
		
		if(pack.getSize()==0)
			return;
		if(actualCount != 0)
			for (int i = 0; i < 4; i++) {
				remove(2);
				if(getComponentCount()==2)
					break;
			}
		if(pack!=null){
			//posiciona las cartas fuera del rango de vision
			for(int k =0;k<pack.getSize();k++){
				card = pack.getCard(k);
				card.setLocation(-wid, -hei);
			}
			
		for(int i = counter ;i<counter+elementsperPage ; i++ , j++){
				card = pack.getCard(i);
			card.setBounds((int) (wid * (0.05 + (j * 0.23))),
					(int) (hei * 0.05), (int) (wid * 0.20),
					(int) (hei * 0.80));
			
			card.initBufferedImage();
			add(card);
			repaint();
			actualCount=i+1;
			if(actualCount==pack.getSize())
				break;
		}
		}
	}
	
	
	public void addCard(PlayCard card) {
		if(card!=null){
		pack.addCard(card,0,0);
		if(pack.getSize() <= 4)
			nextButton.setEnabled(false);
		else
			nextButton.setEnabled(true);
		}
	}


	public int getActualPage() {
		return actualPage;
	}

	public void setElementsperPage(int elementsperPage) {
		this.elementsperPage = elementsperPage;
	}

	public int getElementsperPage() {
		return elementsperPage;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getCol() {
		return col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRow() {
		return row;
	}

	private void initButton(JButton button, String img, int x, int y) {

		button.setSize((int) (wid * 0.15), (int) (hei * 0.1));
		button.setSize((int)( (hei * 0.1)*1.33),(int) (hei * 0.1));
		button.setLocation(x,y);
		ImagePanel panel = new ImagePanel(img);
		panel.setSize(button.getSize());
		panel.setLocation(0, 0);
		button.setLayout(null);
		button.setOpaque(false);
		button.setBackground(Color.BLACK);
		button.setBorderPainted(false);
		button.setSelectedIcon(null);
		button.add(panel);
	}

	public void setList(List<PlayCard> list) {
		pack.setList(list);
		if(pack.getSize() <= 4)
			nextButton.setEnabled(false);
		else
		nextButton.setEnabled(true);
	
		goBack();
	}

	public void addListener(ImagePanel panel){
		listener = panel;
	}

	public void setCardEnable(String id) {
		PlayCard card = null;
		for(int i=0;i< pack.getSize();i++){
			card=pack.getCard(i);
			if(card.getId()==id)
				card.setEnable(true);
		}
	}
	public void setSelectable(boolean s){
		selectable=s;
	}
	
	/**
	 * Cesar Necesito este nuevo constructor para trabajar con los dos paneles pageables Bro
	 * Cualquier pregunta me avisas!!!
	 */
	public PageableCardsPanel(int w, int h, int ele, int r, int c, final int location) {
		setSize(w, h);
		pack = new CardPackage(0, 0);
		prevButtonImg = "CardsPNG/flechaIzq.png";
		nextButtonImg = "CardsPNG/flechaDer.png";
		wid = w;
		hei = h;
		row =r;
		col =c;
		actualPage = 0;
		elementsperPage = ele;

		nextButton = new JButton();
		initButton(nextButton, nextButtonImg,
				 (int) (wid - (hei * 0.1)*1.33 - (wid * 0.02)),
				 (int) (hei - (hei * 0.10) - (hei * 0.02)) );
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actualPage++;
				paintPage();
				if(pack.getSize() == actualCount)
					nextButton.setEnabled(false);
				prevButton.setEnabled(true);
			}
		});
//		if(pack.getSize() == actualCount)
//			nextButton.setEnabled(false);
		add(nextButton);
	
		prevButton = new JButton();
		initButton(prevButton, prevButtonImg,
				(int) (wid * 0.02),
				(int) (hei - (hei * 0.10) - (hei * 0.02)) );
		
		prevButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actualPage--;
				paintPage();
			//	if(actualPage==0)
				if(actualCount<5)
					prevButton.setEnabled(false);
				nextButton.setEnabled(true);
			}
		});
		prevButton.setEnabled(false);
		add(prevButton);
		
	
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {	
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				PlayCard card;
				card = pack.cardOnPoint(e.getPoint());
				if(card!=null && card.getEnable()==true && selectable==true)
					((ImagePanel)listener).paintSelectedCardAndres(card, location);
			}
		});
	}
	
	public void goBack(){
		actualPage=0;
		paintPage();
	}
}
