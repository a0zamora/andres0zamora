package magicroot.window;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import cliente.CartasDisponiblesTO;

@SuppressWarnings("serial")
public class PlayCard extends ImagePanel {

	private int left, right, top, down;
	private int element,cost;
	private int dx, dy;
	private int cont = 10;
	private BufferedImage bimg;
	private String bimgPlusName = "CardsPNG/Bimg/plus.png";
	private String bimgBackGroundName;
	private String bimgCenterName;
	private String bimgTopName;
	private String bimgDownName;
	private String bimgRightName;
	private String bimgLeftName;
	private String id;
	private String imgName;
	private String elementStr;
	private Image img;	
	private Thread thread;
	private int row;
	private int col;
	private int idOferta;
	private boolean moving = false;
	private boolean enable = true;
	private boolean plusIcon =false;

	public PlayCard() {
		initCard("0", "", 0, 0, 0, 0, 0);
		elementStr = "";
		bimgBackGroundName = "";
		bimgCenterName ="";
	}

//	public PlayCard(String i, String n, int l, int r, int t, int d, int e, int c) {
//		img = new ImageIcon(n).getImage();
//		initCard(i, n, l, r, t, d, e, c);
//		changeImage(img);
//	}
	
	public PlayCard(String i, String n, int l, int r, int t, int d, String e) {
		img = new ImageIcon(n).getImage();
		initCard(i, n, l, r, t, d, -1);
		elementStr = e;
		changeImage(img);
	}

	public PlayCard(PlayCard card) {

		initCard(card.getId(), card.getImageName() , card.getLeft(), card
				.getRight(), card.getTop(), card.getDown(), card.getEle());
		elementStr= card.getEleStr();
		changeImage(card.getBufferedImage());
	}
	public PlayCard(CartasDisponiblesTO cardTO){
		
			initCard(Integer.toString(cardTO.getId() ) ,"CardsPNG/Bimg/cardBackg.png",
			cardTO.getFuerzaoeste(), cardTO.getFuerzaeste(), cardTO
					.getFuerzanorte(), cardTO.getFuerzasur(), -1);
			setCost(cardTO.getCosto());
			elementStr= cardTO.getElemento();

	}
	void initCard(String i, String n, int l, int r, int t, int d, int e) {
		left = l;
		right = r;
		top = t;
		down = d;
		element = e;
		imgName = n;
		id = i;
		setCost(0);
	}

	public void initBufferedImage() {

		bimgTopName = "CardsPNG/Bimg/"+top+".png";
		bimgDownName = "CardsPNG/Bimg/"+down+".png";
		bimgLeftName = "CardsPNG/Bimg/"+left+".png";
		bimgRightName = "CardsPNG/Bimg/"+right+".png";
		
		String ele="Aqua";

		if(elementStr.equals("fuego"))
			ele="Fire";
		if(elementStr.equals("aire"))
			ele="Wind";
		if(elementStr.equals("tierra"))
			ele="Earth";

		if(imgName.equals("")){
			bimgBackGroundName="CardsPNG/Bimg/cardBackg.png";
			bimgCenterName ="";
		}
		else{
			bimgBackGroundName =imgName;
			bimgCenterName = "CardsPNG/Bimg/card"+ele+".png";
		}
		
		bimg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2d = (Graphics2D) bimg.getGraphics();
		
		g2d.drawImage(new ImageIcon(bimgBackGroundName).getImage(), 0,0,
				getWidth(), getHeight(), null);
		
		g2d.drawImage(new ImageIcon(bimgCenterName).getImage(), 0,0,
				getWidth(), getHeight(), null);
		
		g2d.drawImage(new ImageIcon(bimgTopName).getImage(),
				(int)(getWidth()/2-getWidth()*0.1) ,
				(int)(getHeight()*0.05),
				(int)(getWidth()*0.20), 
				(int)(getHeight()*0.20), null);

		g2d.drawImage(new ImageIcon(bimgRightName).getImage(),
				(int)(getWidth()-getWidth()*0.25) ,
				(int)(getHeight()/2-getHeight()*0.1),
				(int)(getWidth()*0.20), 
				(int)(getHeight()*0.20), null);

		g2d.drawImage(new ImageIcon(bimgLeftName).getImage(),
				(int)(getWidth()*0.05) ,
				(int)(getHeight()/2-getHeight()*0.1),
				(int)(getWidth()*0.20), 
				(int)(getHeight()*0.20), null);

		g2d.drawImage(new ImageIcon(bimgDownName).getImage(),
				(int)(getWidth()/2-getWidth()*0.1) ,
				(int)(getHeight()-getHeight()*0.25),
				(int)(getWidth()*0.20), 
				(int)(getHeight()*0.20), null);		
		
		if (plusIcon) {
			g2d.drawImage(new ImageIcon(bimgPlusName).getImage(), (int) (0),
					(int) (0), (int) (getWidth() * 0.30),
					(int) (getHeight() * 0.30), null);
		}
		
		changeImage(Toolkit.getDefaultToolkit().createImage(bimg.getSource()) );	
	}

	public boolean contains(double x, double y) {
		Rectangle r = new Rectangle(getX() + 2, getY() + 2, getWidth() - 4,
				getHeight() - 4);
		if (r.contains(x, y))
			return true;
		return false;
	}

	public void flipRedCardFirst() {
		Runnable runnable = new Runnable() {
			public void run() {

				setWidImage(getWidth());
				setHeiImage(getHeight());

				setPosXImage(0);
				setPosYImage(0);
				sleepThread();

				for (int i = 0; i < 6; i++) {

					setWidImage((int) (getWidImage() * 0.5));
					setPosXImage((int) ((((getWidth() - getWidImage()) / 2))));
					sleepThread();
				}
				imgName="CardsPNG/Bimg/cardBackgBlue.png";
				initBufferedImage();
				for (int i = 0; i < 6; i++) {
					setWidImage((int) (getWidImage() * 2));
					setPosXImage((int) ((((getWidth() - getWidImage()) / 2))));
					sleepThread();
				}
				setWidImage((int) (getWidth() * 0.8));
				setHeiImage((int) (getHeight() * 0.8));

				setPosXImage((int) ((((getWidth() - getWidImage()) / 2))));
				setPosYImage((int) ((((getHeight() - getHeiImage()) / 2))));

			}
		};
		thread = new Thread(runnable);
		thread.start();

	}

	public void flipBlueCardFirst() {
		Runnable runnable = new Runnable() {
			public void run() {

				setWidImage(getWidth());
				setHeiImage(getHeight());

				setPosXImage(0);
				setPosYImage(0);
				sleepThread();

				for (int i = 0; i < 6; i++) {

					setWidImage((int) (getWidImage() * 0.5));
					setPosXImage((int) ((((getWidth() - getWidImage()) / 2))));
					sleepThread();
				}
				imgName="CardsPNG/Bimg/cardBackgRed.png";
				initBufferedImage();
				for (int i = 0; i < 6; i++) {
					setWidImage((int) (getWidImage() * 2));
					setPosXImage((int) ((((getWidth() - getWidImage()) / 2))));
					sleepThread();
				}
				setWidImage((int) (getWidth() * 0.8));
				setHeiImage((int) (getHeight() * 0.8));

				setPosXImage((int) ((((getWidth() - getWidImage()) / 2))));
				setPosYImage((int) ((((getHeight() - getHeiImage()) / 2))));

			}
		};
		thread = new Thread(runnable);
		thread.start();

	}

	public void moveTo(int x, int y) {
		setLocation(x - dx, y - dy);
	}

	public void setMoving(boolean m, int x, int y) {
		dx = x - getX();
		dy = y - getY();
		moving = m;
	}

	public boolean getMoving() {
		return moving;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public void setEnable(boolean e) {
		enable = e;
	}

	public boolean getEnable() {
		return enable;
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	public int getTop() {
		return top;
	}

	public int getDown() {
		return down;
	}

	public int getEle() {
		return element;
	}
	
	public String getEleStr() {
		return elementStr;
	}

	public String getId() {
		return id;
	}

	public void setId(String i) {
		id = i;
	}

	public void setTableValues(int r, int c) {
		row = r;
		col = c;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	private void sleepThread() {
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getBufferedImage() {
		return bimg;
	}
	public Image getImage() {
		return img;
	}
	public String getImageName() {
		return imgName;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}

	public int getIdOferta() {
		return idOferta;
	}


	public void setPlusIcon(boolean b) {
		this.plusIcon  = b;
	}

	public boolean getPlusIcon() {
		return this.plusIcon;
	}

}
