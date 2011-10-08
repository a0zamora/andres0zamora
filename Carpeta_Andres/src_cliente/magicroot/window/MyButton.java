package magicroot.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class MyButton extends JButton {

	private int pos;
	private int id;
	private String name;
	private JPanel panel;
	private JLabel label;
	private String image;
	private String rollOverImage;
	private String pressedImage;
	private String disableImage;
	private Font myFont;
	public MyButton(String name, int id, String image,
			String rollOverImage, String pressedImage,String disableImage,Font font, int wid, int hei) {
		this.setSize(wid, hei);
		this.image=image;
		this.rollOverImage=rollOverImage;
		this.pressedImage=pressedImage;
		this.disableImage=disableImage;
		this.name = name;
		this.id = id;
		this.myFont = font;
		initGui();
	}
		public MyButton(String name, int id, String image,
				String rollOverImage, String pressedImage,String disableImage) {
		
		this.image=image;
		this.rollOverImage=rollOverImage;
		this.pressedImage=pressedImage;
		this.disableImage=disableImage;
		this.name = name;
		this.id = id;
		initGui();
		}
		
		private void initGui(){
		
		this.setLayout(null);
	
		this.setContentAreaFilled(false);
		this.setFocusable(false);
		this.setBorderPainted(false);
		panel = new JPanel();
		panel.setSize(getSize());

		panel.setLayout(null);
		panel.setOpaque(false);
		label = new JLabel(this.name);
		label.setSize(panel.getSize());
		label.setLocation(0, 0);
		label.setOpaque(false);
		label.setForeground(Color.WHITE);
		label.setFont(myFont);
		label.setFont(myFont);
		panel.add(label);
		add(panel);
		
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void SetImages(){
		ImageIcon scaledIcon;
		ImageIcon originalIcon;

		
		originalIcon = new ImageIcon(image);
		scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(
				(int) (getWidth()) , getHeight(), Image.SCALE_DEFAULT));

		setIcon(scaledIcon);

		originalIcon = new ImageIcon(rollOverImage);
		scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(
				(int) (getWidth() ), getHeight(), Image.SCALE_DEFAULT));

		setRolloverIcon(scaledIcon);

		originalIcon = new ImageIcon(pressedImage);
		scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(
				(int) (getWidth() ), getHeight(), Image.SCALE_DEFAULT));

		setPressedIcon(scaledIcon);
		
		originalIcon = new ImageIcon(disableImage);
		scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(
				(int) (getWidth() ), getHeight(), Image.SCALE_DEFAULT));

		setDisabledIcon(scaledIcon);
	}
	
	void setMyBorder() {
		this.setBorderPainted(true);
		this.setBackground(Color.DARK_GRAY);

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		CompoundBorder compound = BorderFactory.createCompoundBorder(
				raisedbevel, loweredbevel);

		setBorder(compound);
	}

	public void setPos(int pos) {
		this.pos = pos;
		
	}

	public boolean calculateLocation() {
		return false;
	}

}
