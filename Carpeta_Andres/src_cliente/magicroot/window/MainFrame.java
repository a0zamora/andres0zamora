package magicroot.window;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cliente.ServiciosWeb;
import cliente.ServiciosWebService;

import constants.WebServiceFactory;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private MultiPanel multiPanel;
	private MenuPanel menuPanel;
	private ChatPanel chatPanel;
	private PlayersPanel playersPanel;
	private PlayPanel playPanel;
	private PublicityPanel publicityPanel;
	private int wid;
	private int hei;
	private ActivePlayer player;
	private MyUIListener listener;
	public MainFrame(ActivePlayer p) {

		defineSize();
		getContentPane().setLayout(null);
		player = p;
		initGUI();
	}

	private void initGUI() {

		chatPanel = new ChatPanel((int) (wid * 0.6), (int) (hei * 0.3));
		chatPanel.setLocation((int) (wid * 0.2), (int) (hei * 0.7));
		add(chatPanel);
		
		multiPanel = new MultiPanel((int) (wid * 0.6), (int) (hei * 0.7));
		multiPanel.setLocation((int) (wid * 0.2), 0);
		multiPanel.setPlayer(player);
		add(multiPanel);

		menuPanel = new MenuPanel((int) (wid * 0.2), (int) (hei * 0.5));
		menuPanel.setBounds(0, 0, (int) (wid * 0.2), (int) (hei * 0.5));
		menuPanel.addMenuPanelListener(multiPanel);
		menuPanel.setPlayer(player);
		multiPanel.addListener(menuPanel);
		add(menuPanel);

		
		playersPanel = new PlayersPanel((int) (wid * 0.2), (int) (hei * 0.5));
		playersPanel.setLocation(0, (int) (hei * 0.5));
		playersPanel.setPlayer(player);
		add(playersPanel);
		
		multiPanel.setPlayersPanel(playersPanel);
		publicityPanel = new PublicityPanel((int) (wid * 0.2), (int) (hei));
		publicityPanel.setBounds((int) (wid * 0.8), 0, (int) (wid * 0.2), (int)(hei));
		publicityPanel.setJugador(player);
		add(publicityPanel);

		setVisible(true);
		//setPlayPanel(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				 ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
				 ServiciosWeb base = servicio.getServiciosWebPort();
				 base.desconectar(player.getUsername());
				System.exit(0);

			}
		});
	}

	public void addListeners(MyUIListener i) {
		listener =i;
		multiPanel.listenersConnections(i);
		menuPanel.listenersConnections(i);
		chatPanel.listenersConnections(i);
		playersPanel.listenersConnections(multiPanel);
	}

	public void updateChat(MyEvent evt) {
		chatPanel.putText(evt.getData());
		if(playPanel!=null)
		playPanel.putText(evt.getData());
	}

	public void createRoom(MyEvent evt) {
		multiPanel.addRoom(evt);
	}

	public void deleteRoom(MyEvent evt) {
		multiPanel.deleteRoom(evt);
	}

	public void setPlayPanel(MyEvent evt) {
		playPanel = new PlayPanel(evt, wid, hei,this,player);
		playPanel.setBounds(0, 0, wid, hei);
		playPanel.addListener(listener);
		playPanel.setPlayer(player);
		remove(menuPanel);
		remove(playersPanel);
		remove(chatPanel);
		remove(multiPanel);
		remove(publicityPanel);
		add(playPanel);
		
		repaint();
	}

	public void setNormalPanel(MyEvent evt) {
		remove(playPanel);
		add(menuPanel);
		add(playersPanel);
		add(chatPanel);
		add(multiPanel);
		add(publicityPanel);
		multiPanel.showLobby(evt);
		menuPanel.outRoom();
		chatPanel.myRepaint();
		repaint();
	}

	private void defineSize() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		wid = (int) d.getWidth();
		hei = (int) (d.getHeight() - (d.getHeight() * 0.02));
		setSize(wid, hei);
		setResizable(false);
	}

	public void putSocketUser(String n,String version) {
		setTitle("MagicRoot <------ The Elements Rampage "
				+"Version: "+version + " ------> "+ player.getUsername()  );
	}

	public void setPlayCards(MyEvent evt) {
		playPanel.setPlayCards(evt);
	}

	public void updatePlayedCard(MyEvent evt) {
		playPanel.updatePlayedCard(evt);
		
	}

	public void flipedCard(MyEvent evt) {
		playPanel.flipedCard(evt);
		
	}

	public void setTurn(MyEvent evt) {
		playPanel.setTurn(evt);
	}

	public void gameOver(MyEvent evt) {
		playPanel.gameOver(evt);
		
	}

	public void setPoints(MyEvent evt) {
		playPanel.setPoints(evt);
		
	}

	public void addPlayerConnected(MyEvent evt) {
		playersPanel.addPlayerOnline(evt);
		
	}
	

	public void removePlayerConnected(MyEvent evt) {
		playersPanel.removePlayerOnline(evt);		
	}

	public void setInvited(MyEvent evt) {
		multiPanel.setInvited(evt);
	}

	public void setLobby(MyEvent evt) {
		multiPanel.showLobby(evt);
	}

	public void disableKick(MyEvent evt) {
		multiPanel.disableKick(evt);
	}

	public void isReady(MyEvent evt) {
		multiPanel.isReady(evt);
		
	}

	public void updateTableElement(MyEvent evt) {
		if(playPanel!=null)
		playPanel.updateTableElement(evt);		
	}

	public void chooseTableElement(MyEvent evt) {
		if(playPanel!=null)
			playPanel.chooseTableElement(evt);	
		
	}

	public void gameOverByDesconection(MyEvent evt) {
	    if(playPanel!=null)
		playPanel.gameOverByDesconection(evt);
		
	}

	public void gameOverByTimeOut(MyEvent evt) {
		if(playPanel!=null)
			playPanel.gameOverByTimeOut(evt);
			
		
	}

	public void setAditionalCardPoint(MyEvent evt) {
		if(playPanel!=null)
			playPanel.setAditionalCardPoint(evt);
	}

	public void clientNotFound(MyEvent evt) {
		String info="Version no compatible";
		int option;	
			option=JOptionPane.showConfirmDialog(this, info, "Error",
					JOptionPane.CLOSED_OPTION);
		if(option ==0){
			System.exit(0);		
			}
			
	}
}
