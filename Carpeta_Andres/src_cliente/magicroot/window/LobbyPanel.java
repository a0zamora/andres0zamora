package magicroot.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import commands.CrearCommand;
import commands.UnirCommand;

@SuppressWarnings("serial")
public class LobbyPanel extends ImagePanel {

	private List<MyButton> roomsList;
	private MyUIListener listener;
	private MyListener list;
	private ActivePlayer player;
	private int roomsAmount;
	private int page;
	private JButton nextButton;
	private JButton prevButton;
	public LobbyPanel(int w, int h, MyUIListener i) {
		wid = w;
		hei = h;
		roomsList = new ArrayList<MyButton>();
		roomsAmount = 0;
		page = 0;
		listener = i;
		setSize(wid, hei);
		setLocation(0, 0);
		setLayout(null);
		changeImage("CardsJPG/4Elements.jpg");
		setMyBorder();
		String prevButtonImg = "CardsPNG/flechaIzq.png";
		String nextButtonImg = "CardsPNG/flechaDer.png";
		
		nextButton = new JButton();
		initButton(nextButton, nextButtonImg,
				 (int) (wid - (hei * 0.1)*1.33 - (wid * 0.02)),
				 (int) (hei - (hei * 0.10) - (hei * 0.02)) );
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				page++;
				repaintRooms();
				if(page*9 <= roomsAmount)
					nextButton.setEnabled(true);
				else
					nextButton.setEnabled(false);

				prevButton.setEnabled(true);
			}
		});
		if(page*9 <= roomsAmount)
			nextButton.setEnabled(false);
		add(nextButton);
	
		prevButton = new JButton();
		initButton(prevButton, prevButtonImg,
				(int) (wid * 0.02),
				(int) (hei - (hei * 0.10) - (hei * 0.02)) );
		
		prevButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				page--;
				repaintRooms();
				if(page==0)
					prevButton.setEnabled(false);
				if(page*9 <= roomsAmount)
					nextButton.setEnabled(true);
				else
					nextButton.setEnabled(false);
			}
		});
		prevButton.setEnabled(false);
		add(prevButton);
		


	}

	public void listenersConnections(MyUIListener i) {
		listener = i;
	}

	public void addRoom(String data) {

		CrearCommand c = new CrearCommand(data);
		String name = ("  " + c.getUsrName());
		roomsAmount++;
		makeRoom(name,c.getId());
	}

	public void deleteRoom(String name) {
		String compareValue;
		compareValue = name.substring(9);
		MyButton button;
		
		for (int i = 0; i < roomsList.size(); i++) {
			button = roomsList.get(i);
			if (compareValue.equals(""+button.getId())) {
				roomsAmount--;
				remove(roomsList.remove(i));				
				repaintRooms();
				break;
			}
		}
	}

	public void addIternalListener(MyListener l) {
		list = l;
	}

	public void setPlayer(ActivePlayer p) {
		player = p;
	}

	private void makeRoom(String data,int id) {

		Font font= getMyFont((float) (hei * 0.03));
		
		MyButton button = new MyButton(data,id, "CardsJPG/blue7-1.jpg",
				"CardsJPG/blue7-2.jpg", "CardsJPG/blue7-3.jpg","",font, 200, 100);
		
		button.setPos(roomsAmount);
		button.setLayout(null);
		button.setActionCommand(data);
		button.setMyBorder();
		button.SetImages();
		button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				MyButton b;
				b = (MyButton) e.getComponent();
				player.setColor(2);
				player.setPlaying(true);
				list.showRoom(new MyEvent(LobbyPanel.this, "r-"
						+ b.getActionCommand()));
				UnirCommand c = new UnirCommand(b.getId());
				listener.sendData(new MyEvent(LobbyPanel.this, c
						.convertirAString()));

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
			}
		});
		roomsList.add(button);
		repaintRooms();
	}

	private void repaintRooms() {
		MyButton button;
		int k = page * 9;
		removeAll();
		if (roomsList == null || roomsAmount==0) {
			repaint();
			return;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				button = roomsList.get(k);
				System.err.println("boton " + k);
				button.setLocation(
						(int) (wid * 0.06 + j * button.getWidth() + j * wid
								* 0.05), (int) (hei * 0.1 + i
								* button.getHeight() + i * hei * 0.08));

				add(button);
				button.repaint();
				if ((k + 1) == roomsList.size())
					return;
				k++;

			}
		}

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
	
}
