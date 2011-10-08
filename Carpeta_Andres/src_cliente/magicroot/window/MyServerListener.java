package magicroot.window;

import java.util.EventListener;

public interface MyServerListener extends EventListener {


	public void updateChat(MyEvent evt);
	public void createRoom(MyEvent evt);
	public void deleteRoom(MyEvent evt);
	public void changeName(MyEvent evt);
	public void showPlayPanel(MyEvent evt);
	public void setPlayCards(MyEvent evt);
	public void updatePlayedCard(MyEvent evt);
	public void flipedCard(MyEvent evt);
	public void setTurn(MyEvent evt);
	public void gameOver(MyEvent evt);
	public void setPoints(MyEvent evt);
	public void addPlayerConnected(MyEvent evt);
	public void removePlayerConnected(MyEvent evt);
	public void setInvited(MyEvent evt);
	public void setLobby(MyEvent evt);
	public void disableKick(MyEvent evt);
	public void isReady(MyEvent evt);
	public void updateTableElement(MyEvent evt);
	public void chooseTableElement(MyEvent evt);
	public void gameOverByDesconection(MyEvent evt);
	public void gameOverByTimeOut(MyEvent evt);
	public void setAditionalCardPoint(MyEvent evt);
	public void clientNotFound(MyEvent evt);



}