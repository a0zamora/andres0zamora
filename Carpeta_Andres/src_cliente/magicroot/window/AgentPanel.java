package magicroot.window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import cliente.CartasDisponiblesTO;
import cliente.JugadorTO;
import cliente.ServiciosWeb;
import cliente.ServiciosWebService;

import commands.Command;
import commands.DesconectarInvitadoCommand;
import commands.JugListoCommand;
import commands.ReadyPlayerCommand;

import constants.WebServiceFactory;

public class AgentPanel  extends ImagePanel {

	private MyUIListener itfc;
	private List<PlayCard> list;
	private PageableCardsPanel pgCardsPanel;
	private CardPackage selectedCards;
	private ActivePlayer player;
	private MyButton startButton;
	private MyButton kickButton;
	private int idInvited;
	private boolean invitedReady = false;
	private boolean isCreator = false;
	private PlayersPanel playersPanel;
	private String nameInvited;
	private JLabel invitedNameLabel;

	public AgentPanel(int w, int h, ActivePlayer p, MyEvent evt) {
		wid = w;
		hei = h;
		player = p;
		setSize(wid, hei);
		setLocation(0, 0);
		changeImage("CardsJPG/4Elements.jpg");
		player = p;
		nameInvited = "";

		
		invitedNameLabel = new JLabel("Enemigo: " + nameInvited);
		invitedNameLabel.setBounds((int) (wid * 0.70), (int) (hei * 0.05),
				(int) (wid * 0.30), (int) (hei * 0.10));
		invitedNameLabel.setForeground(Color.WHITE);
		invitedNameLabel.setOpaque(false);
		invitedNameLabel.setVisible(false);
		invitedNameLabel.setFont(getMyFont((float) (hei * 0.03)));
		add(invitedNameLabel);

		if (evt.getData().substring(0, 1).equals("a")) {
			isCreator = true;
		} else {
			nameInvited = evt.getData().substring(3);
			invitedNameLabel.setText("Enemigo: " + nameInvited);
			invitedNameLabel.setVisible(true);
		}

		pgCardsPanel = new PageableCardsPanel((int) (wid * 0.60),
				(int) (hei * 0.30), 4, 1, 1);
		pgCardsPanel.setLocation((int) (wid * 0.08), (int) (hei * 0.15));
		pgCardsPanel.addListener(this);
		pgCardsPanel.setSelectable(true);
		add(pgCardsPanel);
		setMyBorder();
		selectedCards = new CardPackage(2, 4);
		addCard();

		if (player.getColor() == 1) {
			startButton = new MyButton("", 0, "buttons/empezar1.jpg",
					"buttons/empezar2.jpg", "buttons/empezar3.jpg",
					"buttons/empezar4.jpg");
		} else {
			startButton = new MyButton("", 0, "buttons/listo1.jpg",
					"buttons/listo2.jpg", "buttons/listo3.jpg",
					"buttons/listo4.jpg");

		}
		startButton.setBounds((int) (wid * 0.8), (int) (hei * 0.88),
				(int) (wid * 0.15), (int) (hei * 0.09));
		startButton.SetImages();
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Command cp = new JugListoCommand(player.getIdPlayer(), Integer
						.parseInt(selectedCards.getCard(0).getId()), Integer
						.parseInt(selectedCards.getCard(1).getId()), Integer
						.parseInt(selectedCards.getCard(2).getId()), Integer
						.parseInt(selectedCards.getCard(3).getId()), Integer
						.parseInt(selectedCards.getCard(4).getId()), Integer
						.parseInt(selectedCards.getCard(5).getId()), Integer
						.parseInt(selectedCards.getCard(6).getId()), Integer
						.parseInt(selectedCards.getCard(7).getId()));

				player.setPlayingCards(selectedCards.getList());
				itfc
						.sendData(new MyEvent(AgentPanel.this, cp
								.convertirAString()));
				startButton.setEnabled(false);
				invitedReady = true;
			}
		});
		add(startButton);
		startButton.setEnabled(false);

		if (player.getColor() == 1) {
			kickButton = new MyButton("", 0, "buttons/expulsar1.jpg",
					"buttons/expulsar2.jpg", "buttons/expulsar3.jpg",
					"buttons/expulsar4.jpg");

			kickButton.setBounds((int) (wid * 0.8), (int) (hei * 0.75),
					(int) (wid * 0.15), (int) (hei * 0.09));
			kickButton.SetImages();
			kickButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					itfc.sendData(new MyEvent(AgentPanel.this,
							DesconectarInvitadoCommand.CADENA_COMANDO));
					disableKick();
				}
			});
			add(kickButton);
			kickButton.setEnabled(false);
		}

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				PlayCard card;
				card = selectedCards.cardOnPoint(e.getPoint());
				if (card != null && ((!invitedReady && !isCreator) || (isCreator))) {
					//card.setPlusIcon(false);
					card.setRdyIcon(false);
					card.initBufferedImage();
					pgCardsPanel.setCardEnable(card.getId());
					selectedCards.dropCard(card.getId());
					card.changeImage("");
					remove(card);
					pgCardsPanel.setSelectable(true);
					repaint();
					if (startButton.isEnabled()) {
						startButton.setEnabled(false);
					}
				}

			}
		});
	}
	
	public void listenersConnections(MyUIListener i) {
		itfc = i;
	}

	void addCard() {
		String color = "Blue";

		if (player.getColor() == 2) {
			color = "Red";
		}
		list = new ArrayList<PlayCard>();
		List<CartasDisponiblesTO> listTO;
		PlayCard card;
		CartasDisponiblesTO cardTO;
		JugadorTO jugador;
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb base = servicio.getServiciosWebPort();
		String id = "" + player.getUsername();
		jugador = base.consultarPerfil(id);
		listTO = jugador.getCartas();
		for (int i = 0; i < listTO.size(); i++) {
			cardTO = listTO.get(i);
			card = new PlayCard(new String("" + cardTO.getId()),
					"CardsPNG/Bimg/cardBackg" + color + ".png", cardTO
							.getFuerzaoeste(), cardTO.getFuerzaeste(), cardTO
							.getFuerzanorte(), cardTO.getFuerzasur(), cardTO
							.getElemento());
			list.add(card);
		}
		List<PlayCard> cards = new ArrayList<PlayCard>(list);
		player.setCards(cards);// moska aqui
		pgCardsPanel.setList(cards);
		pgCardsPanel.paintPage();
	}

	public void setPlayer(ActivePlayer p) {
		player = p;

	}

	@Override
	public void paintSelectedCard(PlayCard c) {
		c.setEnable(false);

		PlayCard card = new PlayCard(c);

		selectedCards.addCardinOrder(card);
		int row = card.getRow();
		int col = card.getCol();

		card.setBounds(
				(int) ((wid * 0.2) + (col * wid * 0.05) + (col * wid * 0.08)),
				(int) ((hei * 0.5) + (row * hei * 0.20) + (row * hei * 0.02)),
				(int) (wid * 0.10), (int) (hei * 0.20));

		add(card);
		repaint();
		if (selectedCards.getSize() == 8 && (invitedReady || !isCreator)) {
			startButton.setEnabled(true);
		}
		if (selectedCards.getSize() == 8){
			pgCardsPanel.setSelectable(false);
		}
	}

	public void setInvited(MyEvent evt) {
		ReadyPlayerCommand c = new ReadyPlayerCommand(evt.getData());
		idInvited = c.getId();
		nameInvited = playersPanel.getPlayerOnline(idInvited);
		invitedNameLabel.setText("Enemigo: " + nameInvited);
		invitedNameLabel.setVisible(true);
		kickButton.setEnabled(true);
	}

	public void disableKick() {
		kickButton.setEnabled(false);
		startButton.setEnabled(false);
		invitedReady = false;
		invitedNameLabel.setVisible(false);
	}

	public void isReady() {
		invitedReady = true;
		System.err.println(nameInvited+" is ready !!");
		if (isCreator)
			if (selectedCards.getSize() == 8 && invitedReady) {
				startButton.setEnabled(true);
			}
	}

	public void setPlayersPanel(PlayersPanel pPanel) {
		playersPanel = pPanel;
	}

}
