package magicroot.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	private Image image;
	private int posXImage = 0;
	private int posYImage = 0;
	private int widImage=0;
	private int heiImage=0;
	private String imgName;
	protected int wid;
	protected int hei;

	public ImagePanel() {
		setLayout(null);
		imgName = "";
		repaint();
	}

	public ImagePanel(Image img) {
		setLayout(null);
		image = img;
		repaint();

	}
	
	public ImagePanel(String img) {
		setLayout(null);
		image = new ImageIcon(img).getImage();
		repaint();

	}


	@Override
	public void paint(Graphics g) {
		update(g);
	}

	@Override
	public void update(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(widImage !=0 || heiImage!=0)
		g2d.drawImage(image, posXImage, posYImage,
				widImage, heiImage, null);
		else
			g2d.drawImage(image, posXImage, posYImage,
					getWidth(), getHeight(), null);
		setOpaque(false);
		super.paint(g2d);
	}

	public void changeImage(String i) {
		imgName = i;
		image =  new ImageIcon(i).getImage();
		repaint();
	}
	public void changeImage(Image img) {
		image =img;
		repaint();
	}

	public String getImgName() {
		return imgName;
	}

	void setMyBorder() {
		setBackground(Color.DARK_GRAY);

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		CompoundBorder compound = BorderFactory.createCompoundBorder(
				raisedbevel, loweredbevel);

		setBorder(compound);
	}

	public int getPosXImage(){
		return posXImage;
		
	}
	public void setPosXImage(int x){
		posXImage =x;
		repaint();
		
	}
	
	public int getPosYImage(){
		return posYImage;
		
	}
	public void setPosYImage(int y){
		posYImage =y;
		repaint();
	}
	
	public void setWidImage(int w){
		widImage =w;
		repaint();
	}
	
	public void setHeiImage(int h){
		heiImage =h;
		repaint();
	}

	public int getWidImage(){
		return widImage;
		
	}
	public int getHeiImage(){
		return heiImage;
		
	}
	public void paintSelectedCard(PlayCard c) {
	}
	public void paintSelectedCardAndres(PlayCard c, int loca) {
	}
	
	public Font getMyFont(float tam) {
		Font labelFont = null;
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File(
					RoomPanel.class.getClassLoader().getResource(
							"fonts/vikingn.ttf").toString().substring(5)));
			labelFont = font.deriveFont(tam);
			
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (labelFont == null) {
			labelFont = new Font(null); // Fuente por defecto
		}

		return labelFont;
	}

}

