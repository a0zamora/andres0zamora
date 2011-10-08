package magicroot.window;


@SuppressWarnings("serial")
public class MultiPanel extends ImagePanel implements MyListener {

	private MyUIListener listener;
	private RoomPanel    roomPanel;
	private ProfilePanel profilePanel;
	private MarketPanel  marketPanel;
	private LobbyPanel   lobbyPanel;
	private MenuPanel   menuPanel;
	private ActivePlayer player;
	private PlayersPanel playersPanel;

	public MultiPanel(int w ,int h) {
		wid=w;
		hei=h;
		setSize(wid, hei);
		setLayout(null);
		changeImage("CardsJPG/4Elements.jpg");
		setMyBorder();
		updateUI();
	}
	

	public void addListener(MenuPanel m){
		menuPanel = m;
	}
	
	@Override
	public void showRoom(MyEvent evt) {
		if(getComponentCount() !=0){
			remove(0);
		}
		roomPanel = new RoomPanel(wid,hei,player,evt);
		roomPanel.listenersConnections(listener);
		roomPanel.setPlayer(player);
		roomPanel.setPlayersPanel(playersPanel);
		menuPanel.intoRoom(false);
		add(roomPanel);
		repaint();
		updateUI();
	}

	@Override
	public void showLobby(MyEvent evt) {
		if(getComponentCount() !=0){
			remove(0);
		}
		lobbyPanel   = new LobbyPanel(wid,hei,listener);
		lobbyPanel.addIternalListener(this);
		lobbyPanel.listenersConnections(listener);
		lobbyPanel.setPlayer(player);
		menuPanel.outRoom();
		add(lobbyPanel);
		repaint();
		updateUI();
	}

	@Override
	public void showProfile(MyEvent evt) {
		if(getComponentCount() !=0){
			remove(0);
		}
		if(player.getUsername().equals(evt.getData())){

			profilePanel = new ProfilePanel(wid,hei);
			profilePanel.setPlayer(player);
			profilePanel.setItems();
			add(profilePanel);
		}
		else{
			profilePanel = new ProfilePanel(wid,hei, evt.getData());
			add(profilePanel);
		}
		
		repaint();
		updateUI();
	}

	@Override
	public void showMarket(MyEvent evt) {
		if(getComponentCount() !=0){
			remove(0);
		}
		marketPanel = new MarketPanel(wid, hei, player);
		add(marketPanel);
		repaint();
		updateUI();

	}

	public void listenersConnections(MyUIListener i) {
		listener=i;
	}

	public void addRoom(MyEvent evt){
		if(lobbyPanel !=null)
		lobbyPanel.addRoom(evt.getData());
	}
	
	public void deleteRoom(MyEvent evt){
		if(lobbyPanel !=null)
		lobbyPanel.deleteRoom(evt.getData());
	}


	public void setPlayer(ActivePlayer p) {
				player=p;
	}

	@Override
	public void showMakeShop(MyEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showStore(MyEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showViewShops(MyEvent evt) {
		// TODO Auto-generated method stub
		
	}

	public void setInvited(MyEvent evt) {
		roomPanel.setInvited(evt);
		
	}

	public void disableKick(MyEvent evt) {
		roomPanel.disableKick();
		
	}

	public void isReady(MyEvent evt) {
		roomPanel.isReady();
		
	}
	public void setPlayersPanel(PlayersPanel pPanel) {
		playersPanel = pPanel;
	}
}


