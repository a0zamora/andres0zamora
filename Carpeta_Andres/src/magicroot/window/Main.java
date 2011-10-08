package magicroot.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import commands.Command;
import commands.VersionCommand;

import cliente.JugadorBean;
import cliente.JugadorTO;

public class Main implements MyServerListener {
	private JFrame actualFrame;
	private Interface interFace;
	private Thread thread;
	private ActivePlayer player;
	private static final String VERSION = "1.0";
	public static void main(String[] args) {
		new Main();
	}

	public Main() {

		actualFrame = new LoginFrame(this);
		actualFrame.setResizable(false);
		centerWindow();
	}

	public void changeFrame(ActivePlayer p) {
		player = p;
		Runnable runnable= new Runnable() {
			
			@Override
			public void run() {

				try {
					actualFrame.setVisible(false);
					actualFrame = new MainFrame(player);
					Thread.sleep(500);
					initConection();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		thread = new Thread(runnable);
		thread.start();
	}
	
	private void initConection(){
		interFace = new Interface(player,this);
		((MainFrame) actualFrame).addListeners(interFace);
		Command version = new VersionCommand(0,VERSION);
		interFace.sendData(new MyEvent(this, version.convertirAString()));
		centerWindow();
			
	}

	private void centerWindow() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		actualFrame.setBounds((d.width - actualFrame.getWidth()) / 2,
				(d.height - actualFrame.getHeight()) / 2, actualFrame
						.getWidth(), actualFrame.getHeight());
	}

	@Override
	public void updateChat(MyEvent evt) {
		((MainFrame) actualFrame).updateChat(evt);
	}
	@Override
	public void createRoom(MyEvent evt) {
		((MainFrame) actualFrame).createRoom(evt);
	}
	@Override
	public void deleteRoom(MyEvent evt) {
		((MainFrame) actualFrame).deleteRoom(evt);
	}

	@Override
	public void changeName(MyEvent evt) {
		((MainFrame) actualFrame).putSocketUser(evt.getData().substring(7),VERSION);
	}

	@Override
	public void showPlayPanel(MyEvent evt) {
		((MainFrame) actualFrame).setPlayPanel(evt);
	}

	@Override
	public void setPlayCards(MyEvent evt) {
		((MainFrame) actualFrame).setPlayCards(evt);
		
	}

	@Override
	public void updatePlayedCard(MyEvent evt) {
		((MainFrame) actualFrame).updatePlayedCard(evt);

	}

	@Override
	public void flipedCard(MyEvent evt) {
		((MainFrame) actualFrame).flipedCard(evt);
				
	}

	@Override
	public void setTurn(MyEvent evt) {
		((MainFrame) actualFrame).setTurn(evt);
		
	}

	@Override
	public void gameOver(MyEvent evt) {
		((MainFrame) actualFrame).gameOver(evt);
		
	}

	@Override
	public void setPoints(MyEvent evt) {
		((MainFrame) actualFrame).setPoints(evt);

	}

	@Override
	public void addPlayerConnected(MyEvent evt) {
		((MainFrame) actualFrame).addPlayerConnected(evt);
	}

	@Override
	public void removePlayerConnected(MyEvent evt) {
		((MainFrame) actualFrame).removePlayerConnected(evt);
		
	}

	@Override
	public void setInvited(MyEvent evt) {
		((MainFrame) actualFrame).setInvited(evt);

	}

	@Override
	public void setLobby(MyEvent evt) {
		((MainFrame) actualFrame).setLobby(evt);

	}

	@Override
	public void disableKick(MyEvent evt) {
		((MainFrame) actualFrame).disableKick(evt);		
		
	}

	@Override
	public void isReady(MyEvent evt) {
		((MainFrame) actualFrame).isReady(evt);		
		
	}

	@Override
	public void updateTableElement(MyEvent evt) {
		((MainFrame) actualFrame).updateTableElement(evt);		
		
	}

	@Override
	public void chooseTableElement(MyEvent evt) {
		((MainFrame) actualFrame).chooseTableElement(evt);		
		
	}

	@Override
	public void gameOverByDesconection(MyEvent evt) {
		((MainFrame) actualFrame).gameOverByDesconection(evt);		
		
	}

	@Override
	public void gameOverByTimeOut(MyEvent evt) {
		((MainFrame) actualFrame).gameOverByTimeOut(evt);		
		
	}

	@Override
	public void setAditionalCardPoint(MyEvent evt) {
		((MainFrame) actualFrame).setAditionalCardPoint(evt);		
	}

	@Override
	public void clientNotFound(MyEvent evt) {
		((MainFrame) actualFrame).clientNotFound(evt);	
	}
	
}