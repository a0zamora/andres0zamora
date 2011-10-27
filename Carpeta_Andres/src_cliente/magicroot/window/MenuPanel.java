package magicroot.window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.event.EventListenerList;

import commands.CrearCommand;
import commands.JugDesconectadoRoomCommand;

@SuppressWarnings("serial")
public class MenuPanel extends ImagePanel {
	MyUIListener listener;

	private EventListenerList evtMenuPanelListenerList = new EventListenerList();
	private MyButton goToLobby;
	private MyButton goToStore;
	private MyButton goToProfile;
	private MyButton createRoom;
	private MyButton deleteRoom;
	private MyButton leaveRoom;
	private MyButton agentRoom;
	private ActivePlayer player;

	public MenuPanel(int w, int h) {
		wid = w;
		hei = h;
		setSize(wid, hei);
		setLayout(null);
		setMyBorder();
		setBackground(Color.DARK_GRAY);
		changeImage("CardsPNG/Fondonegro.png");

		goToLobby = new MyButton("", 0, "buttons/lobby1.jpg",
				"buttons/lobby2.jpg", "buttons/lobby3.jpg", "buttons/lobby4.jpg");
		goToLobby.setBounds((int) (wid * 0.3), (int) (hei * 0.08),
				(int) (wid * 0.40), (int) (hei * 0.1));
		goToLobby.SetImages();
		goToLobby.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				fireMultiPanelEvent(new MyEvent(MenuPanel.this, "Lobby"),
						MyListener.SHOW_LOBBY);

				// CrearCommand c = new CrearCommand(player.getIdPlayer());
				listener.sendData(new MyEvent(MenuPanel.this,
						"mostrar_partidas"));
			}
		});
		add(goToLobby);

		goToStore = new MyButton("", 0, "buttons/tienda1.jpg",
				"buttons/tienda2.jpg", "buttons/tienda3.jpg", "buttons/tienda4.jpg");
		goToStore.setBounds((int) (wid * 0.3), (int) (hei * 0.22),
				(int) (wid * 0.40), (int) (hei * 0.1));
		goToStore.SetImages();
		goToStore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireMultiPanelEvent(new MyEvent(MenuPanel.this, "Store"),
						MyListener.SHOW_MARKET);

			}
		});
		add(goToStore);

		goToProfile = new MyButton("", 0, "buttons/perfil1.jpg",
				"buttons/perfil2.jpg", "buttons/perfil3.jpg", "buttons/perfil4.jpg");
		goToProfile.setBounds((int) (wid * 0.3), (int) (hei * 0.36),
				(int) (wid * 0.40), (int) (hei * 0.1));
		goToProfile.SetImages();
		goToProfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireMultiPanelEvent(new MyEvent(MenuPanel.this, player
						.getUsername()), MyListener.SHOW_PROFILE);
			}
		});
		add(goToProfile);

		createRoom = new MyButton("", 0, "buttons/crear1.jpg",
				"buttons/crear2.jpg", "buttons/crear3.jpg", "buttons/crear4.jpg");
		createRoom.setBounds((int) (wid * 0.3), (int) (hei * 0.50),
				(int) (wid * 0.4), (int) (hei * 0.1));
		createRoom.SetImages();
		createRoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.setColor(1);
				player.setPlaying(true);
				// parametro "a" creador es azul
				fireMultiPanelEvent(new MyEvent(MenuPanel.this, "a"),
						MyListener.SHOW_ROOM);

				CrearCommand c = new CrearCommand(player.getIdPlayer());
				listener.sendData(new MyEvent(MenuPanel.this, c
						.convertirAString()));

				intoRoom(true);

			}
		});
		add(createRoom);

		deleteRoom = new MyButton("", 0, "buttons/borrar1.jpg",
				"buttons/borrar2.jpg", "buttons/borrar3.jpg", "buttons/borrar4.jpg");
		deleteRoom.setBounds((int) (wid * 0.3), (int) (hei * 0.66),
				(int) (wid * 0.4), (int) (hei * 0.1));
		deleteRoom.SetImages();
		deleteRoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.setPlaying(false);
				goToStore.setEnabled(true);
				goToProfile.setEnabled(true);
				goToLobby.setEnabled(true);
				createRoom.setEnabled(true);
				leaveRoom.setEnabled(false);
				deleteRoom.setVisible(false);
				deleteRoom.setEnabled(false);
				player.setColor(0);

				fireMultiPanelEvent(new MyEvent(MenuPanel.this, "lobby"),
						MyListener.SHOW_LOBBY);
				listener.sendData(new MyEvent(MenuPanel.this,
						JugDesconectadoRoomCommand.CADENA_COMANDO));

			}
		});
		deleteRoom.setEnabled(false);
		deleteRoom.setVisible(false);
		add(deleteRoom);

		leaveRoom = new MyButton("", 0, "buttons/desertar1.jpg",
				"buttons/desertar2.jpg", "buttons/desertar3.jpg", "buttons/desertar4.jpg");
		leaveRoom.setBounds((int) (wid * 0.3), (int) (hei * 0.80),
				(int) (wid * 0.4), (int) (hei * 0.1));
		leaveRoom.SetImages();
		leaveRoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				player.setPlaying(false);
				goToStore.setEnabled(true);
				goToProfile.setEnabled(true);
				goToLobby.setEnabled(true);
				createRoom.setEnabled(true);
				leaveRoom.setEnabled(false);
				leaveRoom.setVisible(false);
				leaveRoom.setEnabled(false);
				player.setColor(0);
				fireMultiPanelEvent(new MyEvent(MenuPanel.this, "lobby"),
						MyListener.SHOW_LOBBY);
				JugDesconectadoRoomCommand c = new JugDesconectadoRoomCommand(
						player.getIdPlayer());
				listener.sendData(new MyEvent(MenuPanel.this, c
						.convertirAString()));
			}
		});
		leaveRoom.setEnabled(false);
		leaveRoom.setVisible(false);
		add(leaveRoom);
		
		agentRoom = new MyButton("", 0, "buttons/agente.jpg",
				"buttons/agente3.jpg", "buttons/agente2.jpg", 
				"buttons/agente.jpg");
		agentRoom.setBounds((int) (wid * 0.3), (int) (hei * 0.65),
				(int) (wid * 0.4), (int) (hei * 0.32));
		agentRoom.SetImages();
		agentRoom.setVisible(true);
		agentRoom.setEnabled(true);
		agentRoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Para poner lo que va a pasar cuando se toque este boton.
				player.setColor(1);
				player.setPlaying(true);
				// parametro "a" creador es azul
				fireMultiPanelEvent(new MyEvent(MenuPanel.this, "a"),
						MyListener.SHOW_ROOM);

				CrearCommand c = new CrearCommand(player.getIdPlayer());
				listener.sendData(new MyEvent(MenuPanel.this, c
						.convertirAString()));

				intoRoom(true);
				
			}
		});
		agentRoom.setToolTipText("Jugar VS Maquina");
		add(agentRoom);
	}

	public void addMenuPanelListener(MyListener listener) {
		evtMenuPanelListenerList.add(MyListener.class, listener);
	}

	public EventListener[] getMenuPanelListeners() {
		return evtMenuPanelListenerList.getListeners(MyListener.class);
	}

	protected void fireMultiPanelEvent(MyEvent evt, int action) {
		EventListener[] eventListeners = getMenuPanelListeners();

		for (int i = 0; i < eventListeners.length; i++) {
			MyListener listener = (MyListener) eventListeners[i];

			switch (action) {
			case MyListener.SHOW_PROFILE:
				listener.showProfile(evt);
				break;
			case MyListener.SHOW_ROOM:
				listener.showRoom(evt);
				break;
			case MyListener.SHOW_MARKET:
				listener.showMarket(evt);
				break;
			case MyListener.SHOW_LOBBY:
				listener.showLobby(evt);
				break;
			}
		}
	}
/**
 * Letras para losBotones
 * 
 * Fuente Viking
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
	protected void fireServerEvent(MyEvent evt, int action) {

	}

	public void listenersConnections(MyUIListener i) {
		listener = i;

	}

	public void setPlayer(ActivePlayer p) {
		player = p;
	}

	public void outRoom() {
		goToStore.setEnabled(true);goToStore.setVisible(true);
		goToProfile.setEnabled(true);goToProfile.setVisible(true);
		goToLobby.setEnabled(true);goToLobby.setVisible(true);
		createRoom.setEnabled(true);createRoom.setVisible(true);
		leaveRoom.setVisible(false);
		leaveRoom.setEnabled(false);
		deleteRoom.setEnabled(false);
		agentRoom.setVisible(true);

	}

	public void intoRoom(boolean creator) {
		goToStore.setEnabled(false);goToStore.setVisible(false);
		goToProfile.setEnabled(false);goToProfile.setVisible(false);
		goToLobby.setEnabled(false);goToLobby.setVisible(false);
		createRoom.setEnabled(false);createRoom.setVisible(false);
		if (creator) {
			
			leaveRoom.setEnabled(false);leaveRoom.setVisible(false);
			deleteRoom.setEnabled(true);deleteRoom.setVisible(true);
			agentRoom.setVisible(false);
			
		} else {
			
			leaveRoom.setEnabled(true);leaveRoom.setVisible(true);
			deleteRoom.setVisible(false);deleteRoom.setEnabled(false);
			agentRoom.setVisible(false);
			
		}
	}
}
