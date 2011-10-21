package magicroot.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import commands.ActualizarTablero;
import commands.CambioTableroCommand;
import commands.CartaCommand;
import commands.CartasVolteadaCommand;
import commands.Command;
import commands.DesconexionJugandoCommand;
import commands.EleguirTableroCommand;
import commands.EnviarConectadosCommand;
import commands.FinJuegoPorDesconexion;
import commands.GameOver;
import commands.JugListoCommand;
import commands.JugarCommand;
import commands.PosicionBean;
import commands.PuntosCommand;
import commands.TimeOutCommand;
import commands.TurnoCommand;

import constants.Elemento;

@SuppressWarnings("serial")
public class PlayPanel extends ImagePanel {

	private MyUIListener listener;
	private CardPackage playerCards;
	private CardPackage battleCards;
	private CardPackage enemyCards;
	private int bckupX;
	private int bckupY;
	private Thread thread;
	private Thread animationThread;
	private PlayCard activeCard = null;
	private ActivePlayer player;
	private ChatPanel chatPanel;
	private boolean turn = false;
	private boolean selecting = false;
	private boolean playing = true;
	private ImagePanel myArrowTurn = null;
	private ImagePanel enemyArrowTurn = null;
	private ImagePanel battleTable;
	private ImagePanel finishPanel;
	private ImagePanel chooseTableElement;
	private JLabel myPoints = null;
	private JLabel enemyPoints = null;
	private MyButton exitButton;
	private MyButton leaveButton;
	private MainFrame mf;
	private JProgressBar myProgressBar;
	private JProgressBar enemyProgressBar;

	public PlayPanel(MyEvent evt, int w, int h, MainFrame m,
			ActivePlayer aPlayer) {
		wid = w;
		hei = h;
		mf = m;
		player = aPlayer;
		changeImage("CardsJPG/4Elements.jpg");
		setMyBorder();

		finishPanel = new ImagePanel("");
		add(finishPanel);

		initChoosePanel();

		chatPanel = new ChatPanel((int) (wid * 0.6), (int) (hei * 0.25));
		chatPanel.setLocation((int) (wid * 0.2), (int) (hei * 0.75));
		chatPanel.setOpaque(false);
		chatPanel.changeImage("");
		chatPanel.putText("System: Fight !!!");
		add(chatPanel);

		// TODO: Poner el 60 o el tiempo en una constante bien identificada
		// para que se pueda cambiar facilmente
		myProgressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 15);
		myProgressBar.setBounds((int) (wid * 0.23), (int) (hei * 0.45),
				(int) (wid * 0.10), (int) (hei * 0.05));

		add(myProgressBar);

		// TODO: Poner el 60 o el tiempo en una constante bien identificada
		// para que se pueda cambiar facilmente
		enemyProgressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 15);
		enemyProgressBar.setBounds((int) (wid * 0.68), (int) (hei * 0.45),
				(int) (wid * 0.10), (int) (hei * 0.05));

		add(enemyProgressBar);

		battleTable = new ImagePanel("CardsJPG/fourElements.jpg");
		add(battleTable);

		myArrowTurn = new ImagePanel("");
		add(myArrowTurn);

		enemyArrowTurn = new ImagePanel("");
		add(enemyArrowTurn);

		myPoints = new JLabel("0");
		myPoints.setBounds((int) (wid * 0.27), (int) (hei * 0.25),
				(int) (wid * 0.10), (int) (hei * 0.10));
		myPoints.setFont(new Font("DejaVu Sans", 0, 50));
		myPoints.setForeground(Color.WHITE);
		add(myPoints);

		enemyPoints = new JLabel("0");
		enemyPoints.setBounds((int) (wid * 0.72), (int) (hei * 0.25),
				(int) (wid * 0.10), (int) (hei * 0.10));
		enemyPoints.setFont(new Font("DejaVu Sans", 0, 50));
		enemyPoints.setForeground(Color.WHITE);
		add(enemyPoints);

		leaveButton = new MyButton("", 0, "buttons/desertar1.jpg",
				"buttons/desertar2.jpg", "buttons/desertar3.jpg",
				"buttons/desertar4.jpg");

		leaveButton.setBounds((int) (wid - wid * 0.1 - wid * 0.07), (int) (hei
				- hei * 0.1 - hei * 0.08), (int) (wid * 0.10),
				(int) (hei * 0.05));
		leaveButton.SetImages();

		leaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mf.setNormalPanel(null);
				player.setPlaying(false);
				listener.sendData(new MyEvent(PlayPanel.this,
						DesconexionJugandoCommand.CADENA_COMANDO));
				listener.sendData(new MyEvent(PlayPanel.this,
						EnviarConectadosCommand.CADENA_COMANDO));

			}
		});
		add(leaveButton);

		playerCards = new CardPackage(4, 2);

		battleCards = new CardPackage(4, 4);
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				paintTableCards(i, j, (int) (wid * 0.345), (int) (hei * 0.015),
						battleCards, "CardsJPG/card1-8.jpg");
			}

		enemyCards = new CardPackage(4, 2);
		PlayCard eCard = new PlayCard();
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 2; j++) {
				paintCards(i, j, (int) (wid * 0.8), (int) (hei * 0.1),
						enemyCards, eCard);
			}

		cardMovingEfect();
		updateUI();
	}

	void paintCards(int row, int col, int initPosX, int initPosY,
			CardPackage pack, PlayCard aCard) {

		PlayCard card = new PlayCard(aCard);
		// (posXinicial)+(wid)+(separacionX),(posYinicial)+(heigth)+(separacionY,
		// tamX,tamY)
		card.setBounds((int) (initPosX + (col * wid * 0.08)), (int) (initPosY
				+ (row * hei * 0.20) - (row * hei * 0.02)), (int) (wid * 0.07),
				(int) (hei * 0.16));
		card.setEnable(true);
		card.initBufferedImage();
		pack.addCard(card, row, col);
		add(card);
	}

	void paintTableCards(int row, int col, int initPosX, int initPosY,
			CardPackage pack, String img) {
		int color = 1;
		if (player.getColor() == 2)
			color = 2;
		PlayCard card = new PlayCard("", img, 1, 1, 1, 1, "");
		// (posXinicial)+(wid)+(separacionX),(posYinicial)+(heigth)+(separacionY,
		// tamX,tamY)
		card.setBounds(
				(int) (initPosX + (col * wid * 0.09) - (col * wid * 0.01)),
				(int) (initPosY + (row * hei * 0.20) - (row * hei * 0.02)),
				(int) (wid * 0.09), (int) (hei * 0.20));
		card.setEnable(true);
		pack.addCard(card, row, col);

		card.setWidImage((int) (card.getWidth() * 0.8));
		card.setHeiImage((int) (card.getHeight() * 0.8));

		card
				.setPosXImage((int) ((card.getPosXImage() + ((card.getWidth() - card
						.getWidImage()) / 2))));
		card
				.setPosYImage((int) ((card.getPosYImage() + ((card.getHeight() - card
						.getHeiImage()) / 2))));

		add(card);
	}

	void cardMovingEfect() {
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (turn == false)
					return;
				PlayCard pcard;
				PlayCard bcard;
				pcard = activeCard;
				bcard = battleCards.cardOnPoint(e.getPoint());
				if (pcard != null && bcard != null && bcard.getEnable()) {
					remove(pcard);
					pcard = playerCards
							.dropCard(pcard.getRow(), pcard.getCol());

					int rowC = bcard.getRow();
					int colC = bcard.getCol();
					bcard = battleCards
							.dropCard(bcard.getRow(), bcard.getCol());
					bcard.changeImage("");
					pcard.setBounds(bcard.getBounds());
					pcard.setPosXImage(bcard.getPosXImage());
					pcard.setPosYImage(bcard.getPosYImage());
					pcard.setWidImage(bcard.getWidImage());
					pcard.setHeiImage(bcard.getHeiImage());
					pcard.setEnable(false);

					battleCards.addCard(pcard, bcard.getRow(), bcard.getCol());
					add(pcard);
					updateUI();
					repaint();
					updateUI();
					// -------------------------------------------------------------

					Command c = new JugarCommand(player.getIdPlayer(), Integer
							.parseInt(pcard.getId()), rowC, colC);
					listener.sendData(new MyEvent(PlayPanel.this, c
							.convertirAString()));
					turn = false;

				} else if ((pcard != null && bcard != null
						&& !bcard.getEnable() && activeCard != null)
						|| battleCards.cardOnPoint(e.getPoint()) == null
						&& activeCard != null) {
					activeCard.setLocation(bckupX, bckupY);
					pcard.setMoving(false, 0, 0);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (turn == false)
					return;
				PlayCard card;
				card = playerCards.cardOnPoint(e.getPoint());
				if (card != null) {
					superposition(card);
					bckupX = card.getX();
					bckupY = card.getY();
					activeCard = card;
					activeCard.setMoving(true, e.getX(), e.getY());
					new Runnable() {
						@Override
						public void run() {
							while (activeCard.getMoving()) {
								try {
									Thread.sleep(50);
									updateUI();
									repaint();
									updateUI();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					};
				} else
					activeCard = null;
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) {
				if (activeCard != null && turn == true)
					if (activeCard.getMoving()) {
						activeCard.moveTo(e.getX(), e.getY());

					}
			}

			public void mouseMoved(MouseEvent e) {
			}
		});

	}

	private void superposition(PlayCard card) {
		PlayCard cardp;

		remove(card);
		add(card);

		for (int i = 0; i < battleCards.getSize(); i++) {
			cardp = battleCards.getCard(i);
			remove(cardp);
			add(cardp);
		}
		for (int i = 0; i < playerCards.getSize(); i++) {
			cardp = playerCards.getCard(i);
			if (card != cardp) {
				remove(cardp);
				add(cardp);
			}
		}
		for (int i = 0; i < enemyCards.getSize(); i++) {
			cardp = enemyCards.getCard(i);
			remove(cardp);
			add(cardp);
		}
	}

	public void addListener(MyUIListener i) {
		listener = i;
		chatPanel.listenersConnections(listener);
	}

	public void setPlayCards(MyEvent evt) {

		int c = 0;
		int vector[] = new int[8];
		int id = 0;
		if (player.getColor() == 2) {
			id = 10;
		}
		String data = evt.getData();
		JugListoCommand command = new JugListoCommand(data);

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 2; j++) {
				paintCards(i, j, (int) (wid * 0.05), (int) (hei * 0.1),
						playerCards, player.getPlayingCards().get(c));
				c++;
			}
	}

	public void setPlayer(ActivePlayer p) {
		player = p;
	}

	public void putText(String data) {
		chatPanel.putText(data);

	}

	public void updatePlayedCard(MyEvent evt) {

		String element = "agua";
		String color = "Blue";
		JugarCommand jc = new JugarCommand(evt.getData());
		CartaCommand cc = (CartaCommand) jc.getCartaComando();
		if (cc.getElemento() == 1)
			element = "fuego";
		if (cc.getElemento() == 2)
			element = "aire";
		if (cc.getElemento() == 3)
			element = "tierra";
		if (player.getColor() == 1)
			color = "Red";
		PlayCard pcard = new PlayCard("" + cc.getId(),
				"CardsPNG/Bimg/cardBackg" + color + ".png", cc.getFo(), cc
						.getFe(), cc.getFn(), cc.getFs(), element);
		int rowC = jc.getX();
		int colC = jc.getY();
		int id = jc.getIdCarta();

		PlayCard bcard = battleCards.dropCard(rowC, colC);
		bcard.changeImage("");
		pcard.setBounds(bcard.getBounds());
		pcard.setPosXImage(bcard.getPosXImage());
		pcard.setPosYImage(bcard.getPosYImage());
		pcard.setWidImage(bcard.getWidImage());
		pcard.setHeiImage(bcard.getHeiImage());
		pcard.setEnable(false);
		pcard.initBufferedImage();
		battleCards.addCard(pcard, bcard.getRow(), bcard.getCol());
		remove(bcard);
		remove(pcard);
		add(pcard);
		dropEnemyCard();
		updateUI();
		repaint();
		updateUI();
	}

	public void flipedCard(MyEvent evt) {

		CartasVolteadaCommand c = new CartasVolteadaCommand(evt.getData());
		int rowC = c.getX();
		int colC = c.getY();
		PlayCard pcard = battleCards.getCard(rowC, colC);
		if (c.getColor() == 2) {
			pcard.flipBlueCardFirst();

		} else {
			pcard.flipRedCardFirst();

		}
		updateUI();
		repaint();
		updateUI();
	}

	public void setTurn(MyEvent evt) {
		TurnoCommand c = new TurnoCommand(evt.getData());
		String marrow = "CardsPNG/flechaAzulIzquierda.png";
		String earrow = "CardsPNG/flechaRojaDerecha.png";

		if (player.getColor() == 2) {
			marrow = "CardsPNG/flechaRojaIzquierda.png";
			earrow = "CardsPNG/flechaAzulDerecha.png";
		}
		remove(myArrowTurn);
		remove(enemyArrowTurn);
		if (c.getIdHiloEnTurno() == player.getIdPlayer()) {
			turn = true;
			selecting = false;
			myArrowTurn = new ImagePanel(marrow);
			myArrowTurn.setBounds((int) (wid * 0.23), (int) (hei * 0.35),
					(int) (wid * 0.10), (int) (hei * 0.10));
			enemyArrowTurn.changeImage("");
			myProgressBar.setEnabled(false);

		} else {
			turn = false;
			enemyArrowTurn = new ImagePanel(earrow);
			enemyArrowTurn.setBounds((int) (wid * 0.70), (int) (hei * 0.35),
					(int) (wid * 0.10), (int) (hei * 0.10));
			myArrowTurn.changeImage("");

		}
		add(myArrowTurn);
		add(enemyArrowTurn);
		setProgressBars(c.getIdHiloEnTurno());
		updateUI();
		repaint();
		updateUI();
	}

	private void dropEnemyCard() {
		PlayCard dCard;
		dCard = enemyCards.dropLastCard();
		if (dCard != null) {
			dCard.changeImage("");
			remove(dCard);
		}
		updateUI();
		repaint();
		updateUI();
	}

	public void gameOver(MyEvent evt) {
		GameOver c = new commands.GameOver(evt.getData());

		playing = false;
		chooseTableElement.setVisible(false);
		if (c.getIdGanador() == player.getIdPlayer()) {
			WinnerAnimation("buttons/ganaste3.png", "");

		} else if (c.getIdGanador() == 0) {
			WinnerAnimation("buttons/empate.png", "");
		} else {
			WinnerAnimation("buttons/perdiste.png", "");
		}

		setExitButton();
	}

	private void setExitButton() {
		remove(leaveButton);
		exitButton = new MyButton("", 0, "buttons/lobby1.jpg",
				"buttons/lobby2.jpg", "buttons/lobby3.jpg",
				"buttons/lobby4.jpg");

		exitButton.setBounds((int) (wid - wid * 0.1 - wid * 0.07), (int) (hei
				- hei * 0.1 - hei * 0.08), (int) (wid * 0.10),
				(int) (hei * 0.05));
		exitButton.SetImages();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mf.setNormalPanel(null);
				player.setPlaying(false);
				listener.sendData(new MyEvent(PlayPanel.this,
						EnviarConectadosCommand.CADENA_COMANDO));
			}
		});
		add(exitButton);
		setFinalScore();
		repaint();

	}

	public void setPoints(MyEvent evt) {

		PuntosCommand c = new PuntosCommand(evt.getData());

		if (player.getColor() == 1) {
			myPoints.setFont(new Font("DejaVu Sans", 0, 40));
			myPoints.setText("" + c.getAzulPts());
			enemyPoints.setText("" + c.getRojoPts());
		} else {
			myPoints.setText("" + c.getRojoPts());
			enemyPoints.setText("" + c.getAzulPts());
		}
		updateUI();
		repaint();
		updateUI();
	}

	private void setProgressBars(int turnId) {
		remove(myProgressBar);
		myProgressBar.setMinimum(0);
		myProgressBar.setMaximum(50);
		myProgressBar.setStringPainted(false);
		myProgressBar.setOpaque(false);
		myProgressBar.setValue(0);

		remove(enemyProgressBar);
		enemyProgressBar.setMinimum(0);
		enemyProgressBar.setMaximum(50);
		enemyProgressBar.setStringPainted(false);
		enemyProgressBar.setOpaque(false);
		enemyProgressBar.setValue(0);
		if (player.getColor() == 1) {
			myProgressBar.setForeground(Color.BLUE);
			enemyProgressBar.setForeground(Color.RED);
		} else {
			myProgressBar.setForeground(Color.RED);
			enemyProgressBar.setForeground(Color.BLUE);
		}

		Runnable runnable = new Runnable() {
			int i = 0;
			int j = 0;

			@Override
			public void run() {
				while ((turn || selecting) && playing) {
					myProgressBar.setValue(i);
					Rectangle progressRect = myProgressBar.getBounds();
					progressRect.x = 0;
					progressRect.y = 0;
					myProgressBar.paintImmediately(progressRect);
					try {
						thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
				}

				while (!turn && playing) {
					enemyProgressBar.setValue(j);
					Rectangle progressRect = enemyProgressBar.getBounds();
					progressRect.x = 0;
					progressRect.y = 0;
					enemyProgressBar.paintImmediately(progressRect);
					try {
						thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					j++;
				}
			}
		};
		thread = new Thread(runnable);
		thread.start();
		if (turn)
			add(myProgressBar);
		else
			add(enemyProgressBar);

		updateUI();
		repaint();
		updateUI();
	}

	private void WinnerAnimation(String firstAnimation,
			final String secondAnimation) {

		finishPanel.changeImage(firstAnimation);
		finishPanel.setBounds((int) (wid * 0.3), (int) (hei * 0.2),
				(int) (wid * 0.40), (int) (hei * 0.30));

		Runnable runnable = new Runnable() {

			@Override
			public void run() {

				runFinishPanel();
				try {
					Thread.sleep(3200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!secondAnimation.equals("")) {
					finishPanel.setBounds((int) (wid * 0.3), (int) (hei * 0.2),
							(int) (wid * 0.40), (int) (hei * 0.30));
					finishPanel.changeImage(secondAnimation);
					runFinishPanel();
				}
			}
		};
		animationThread = new Thread(runnable);
		animationThread.start();

	}

	public void updateTableElement(MyEvent evt) {

		CambioTableroCommand c = new CambioTableroCommand(evt.getData());
		String newTable = "AquaTable";
		if (c.getElemento() == 1)
			newTable = "FireTable";
		if (c.getElemento() == 2)
			newTable = "WindTable";
		if (c.getElemento() == 3)
			newTable = "EarthTable";
		changeImage("CardsJPG/" + newTable + ".jpg");
		updateUI();
		repaint();
		updateUI();
	}

	public void chooseTableElement(MyEvent evt) {

		chooseTableElement.setVisible(true);
		selecting = true;

	}

	private void initChoosePanel() {

		chooseTableElement = new ImagePanel("CardsJPG/4Elements.jpg");
		chooseTableElement.setBounds((int) (wid * 0.4), (int) (hei * 0.2),
				(int) (wid * 0.20), (int) (hei * 0.30));
		chooseTableElement.setMyBorder();
		chooseTableElement.setLayout(new GridLayout(5, 1));
		final JRadioButton aquaRadBtn = new JRadioButton("Agua");
		aquaRadBtn.setForeground(Color.WHITE);
		aquaRadBtn.setOpaque(false);
		final JRadioButton fireRadBtn = new JRadioButton("Fuego");
		fireRadBtn.setForeground(Color.WHITE);
		fireRadBtn.setOpaque(false);
		final JRadioButton earthRadBtn = new JRadioButton("Tierra");
		earthRadBtn.setForeground(Color.WHITE);
		earthRadBtn.setOpaque(false);
		final JRadioButton windRadBtn = new JRadioButton("Aire");
		windRadBtn.setForeground(Color.WHITE);
		windRadBtn.setOpaque(false);
		ButtonGroup group = new ButtonGroup();
		
		group.add(aquaRadBtn);
		group.add(fireRadBtn);
		group.add(earthRadBtn);
		group.add(windRadBtn);
		aquaRadBtn.setSelected(true);
		chooseTableElement.add(aquaRadBtn);
		chooseTableElement.add(fireRadBtn);
		chooseTableElement.add(earthRadBtn);
		chooseTableElement.add(windRadBtn);

		MyButton acept = new MyButton("", 0, "buttons/enviar1.jpg",
				"buttons/enviar2.jpg", "buttons/enviar3.jpg",
				"buttons/enviar4.jpg");
		acept.setSize(150, 80);
		acept.SetImages();
		acept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Elemento tableElement = Elemento.agua;
				if (earthRadBtn.isSelected() == true) {
					tableElement = Elemento.tierra;
				}
				if (fireRadBtn.isSelected() == true) {
					tableElement = Elemento.fuego;
				}
				if (windRadBtn.isSelected() == true) {
					tableElement = Elemento.aire;
				}

				selecting = false;
				EleguirTableroCommand c = new EleguirTableroCommand(
						tableElement, false);
				chooseTableElement.setVisible(false);
				listener.sendData(new MyEvent(PlayPanel.this, c
						.convertirAString()));
			}

		});
		chooseTableElement.add(acept);
		chooseTableElement.setVisible(false);
		add(chooseTableElement);

		updateUI();
		repaint();
		updateUI();
	}

	private void runFinishPanel() {
		int dx;
		int dy;
		int wid, hei, posX, posY;
		for (int i = 0; i < 10; i++) {
			posX = finishPanel.getX();
			posY = finishPanel.getY();
			wid = finishPanel.getWidth();
			hei = finishPanel.getHeight();
			dx = (int) (wid * i * 0.01) / 2;
			dy = (int) (hei * i * 0.02) / 2;
			finishPanel.setBounds(posX - dx, posY - dy, wid + (dx * 2), hei
					+ (dy * 2));

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void gameOverByDesconection(MyEvent evt) {

		FinJuegoPorDesconexion c = new FinJuegoPorDesconexion(evt.getData());
		chooseTableElement.setVisible(false);
		if (c.getIdGanador() == player.getIdPlayer()) {
			playing = false;
			WinnerAnimation("buttons/ganaste3.png","");
			setExitButton();
		}
	}

	public void gameOverByTimeOut(MyEvent evt) {
		TimeOutCommand c = new TimeOutCommand(evt.getData());
		chooseTableElement.setVisible(false);
		 if (c.getId() != player.getIdPlayer()) {
			 playing = false;
			 WinnerAnimation("buttons/ganaste3.png", "");
			 setExitButton();
		 }else if (c.getId() == player.getIdPlayer()) {
			 playing = false;
			 WinnerAnimation("buttons/perdiste.png", "");
			 setExitButton();
			 }

	}
	
	private void setFinalScore(){

		myPoints.setBounds((int) (wid * 0.26), (int) (hei * 0.55),
				(int) (wid * 0.15), (int) (hei * 0.15));
		myPoints.setFont(new Font("DejaVu Sans", 0, 100));

		enemyPoints.setBounds((int) (wid * 0.71), (int) (hei * 0.55),
				(int) (wid * 0.15), (int) (hei * 0.15));
		enemyPoints.setFont(new Font("DejaVu Sans", 0, 100));
		
		myProgressBar.setVisible(false);
		enemyProgressBar.setVisible(false);
	}

	public void setAditionalCardPoint(MyEvent evt) {
		ActualizarTablero c = new ActualizarTablero (evt.getData());
		PlayCard card;
		List<PosicionBean> list = c.getList();
		PosicionBean  posBean;
		for (int i = 0; i < list.size(); i++){
				posBean = list.get(i);
				card = battleCards.getCard(posBean.getX(), posBean.getY());
				
				if (posBean.getSameElement()) {
					card.setPlusIcon(true);
					card.initBufferedImage();
				} 
				else {
					if (card.getEnable()) {
						card.changeImage("CardsJPG/card1-8.jpg");
					} 
					else {
						card.setPlusIcon(false);
						card.initBufferedImage();

					}
				}

				card.repaint();
			}

	}
}