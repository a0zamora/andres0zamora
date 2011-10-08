package magicroot.window;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.event.EventListenerList;

import commands.ActualizarTablero;
import commands.BorrarConexionCommand;
import commands.CambioTableroCommand;
import commands.CartasVolteadaCommand;
import commands.ClientNotFound;
import commands.ConexionNuevaCommand;
import commands.CrearCommand;
import commands.EleguirTableroCommand;
import commands.EmpezoPartidaCommand;
import commands.FinJuegoPorDesconexion;
import commands.GameOver;
import commands.InvitadoDesconectadoCommand;
import commands.JugDesconectadoRoomCommand;
import commands.JugListoCommand;
import commands.JugarCommand;
import commands.PuntosCommand;
import commands.ReadyPlayerCommand;
import commands.TimeOutCommand;
import commands.TurnoCommand;

public class Interface implements MyUIListener, Runnable {

	private MyServerListener listener;
	private String dataInput;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private Socket socket;
	private ActivePlayer player;
	
	public Interface(ActivePlayer p,MyServerListener l) {
		
        try
        {
    		//socket = new Socket("localhost", 5557);
    		socket = new Socket("150.185.180.170", 5557);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Thread socketsThread = new Thread(this);
            player=p;
            socketsThread.start();
            dataOutputStream.writeUTF("Mi_Id "+Integer.toString(p.getIdPlayer())+" "+p.getUsername());
            listener=l;
//            Mi_IdCommand c = new Mi_IdCommand(p.getIdPlayer(),p.getUsername());
//            dataOutputStream.writeUTF(c.toString());
            
        } catch (Exception e)
        {
        	System.err.println("Error en la conexion");
        }
    }
	
	@Override
	public void run() {
		try {
			while (true) {
				dataInput = dataInputStream.readUTF();
				if (dataInput.startsWith(CrearCommand.CADENA_COMANDO)){
				      listener.createRoom(new MyEvent(Interface.this, dataInput));
				}
				else if (dataInput.startsWith("Eliminar")) {
					listener.deleteRoom(new MyEvent(Interface.this, dataInput));
				}
				else if (dataInput.startsWith("Nombre")) {
			          listener.changeName(new MyEvent(Interface.this, dataInput));
				}
			
				else if (dataInput.startsWith(EmpezoPartidaCommand.CADENA_COMANDO)) {
					listener.showPlayPanel(new MyEvent(Interface.this, dataInput));
				}
				
				else if (dataInput.startsWith(JugListoCommand.CADENA_COMANDO) ) {
					listener.setPlayCards(new MyEvent(Interface.this, dataInput));
				}
				
				else if (dataInput.startsWith(JugarCommand.CADENA_COMANDO) ) {
					listener.updatePlayedCard(new MyEvent(Interface.this, dataInput));
				}

				else if (dataInput.startsWith(CartasVolteadaCommand.CADENA_COMANDO) ) {
			          listener.flipedCard(new MyEvent(Interface.this, dataInput));
				}
				
				else if (dataInput.startsWith(TurnoCommand.CADENA_COMANDO) ) {
			          listener.setTurn(new MyEvent(Interface.this, dataInput));
				}

				else if (dataInput.startsWith(GameOver.CADENA_COMANDO) ) {
				      listener.gameOver(new MyEvent(Interface.this, dataInput));
				}
				else if (dataInput.startsWith(FinJuegoPorDesconexion.CADENA_COMANDO) ) {
				      listener.gameOverByDesconection(new MyEvent(Interface.this, dataInput));
				}
				else if (dataInput.startsWith(TimeOutCommand.CADENA_COMANDO) ) {
				      listener.gameOverByTimeOut(new MyEvent(Interface.this, dataInput));
				}
				else if (dataInput.startsWith(PuntosCommand.CADENA_COMANDO) ) {
					listener.setPoints(new MyEvent(Interface.this, dataInput));
				}

				else if (dataInput.startsWith(ConexionNuevaCommand.CADENA_COMANDO) ) {
					listener.addPlayerConnected(new MyEvent(Interface.this, dataInput));
				}

				else if (dataInput.startsWith(BorrarConexionCommand.CADENA_COMANDO) ) {	
					listener.removePlayerConnected(new MyEvent(Interface.this, dataInput));
				}

				else if (dataInput.startsWith(ReadyPlayerCommand.CADENA_COMANDO)) {
			          listener.setInvited(new MyEvent(Interface.this, dataInput));
				}
				
				else if (dataInput.startsWith(JugDesconectadoRoomCommand.CADENA_COMANDO)) {
			          listener.setLobby(new MyEvent(Interface.this, dataInput));
				}
				else if (dataInput.startsWith(InvitadoDesconectadoCommand.CADENA_COMANDO)) {
			          listener.disableKick(new MyEvent(Interface.this, dataInput));
				}
				else if (dataInput.startsWith(CambioTableroCommand.CADENA_COMANDO)) {
			          listener.updateTableElement(new MyEvent(Interface.this, dataInput));
				}
				else if (dataInput.startsWith(EleguirTableroCommand.CADENA_COMANDO)) {
			          listener.chooseTableElement(new MyEvent(Interface.this, dataInput));
				}
				else if (dataInput.startsWith(ActualizarTablero.CADENA_COMANDO)) {
			          listener.setAditionalCardPoint(new MyEvent(Interface.this, dataInput));
				}
				
				else if (dataInput.startsWith(ClientNotFound.CADENA_COMANDO)) {
			          listener.clientNotFound(new MyEvent(Interface.this, dataInput));
				}

				else if (dataInput.startsWith("El_Invitado_Esta_Listo")) {
			          listener.isReady(new MyEvent(Interface.this, dataInput));
				}
				else if (dataInput.startsWith("Se_desconecto_Creador")) {
			          listener.setLobby(new MyEvent(Interface.this, dataInput));
				}
				
				else {
					listener.updateChat(new MyEvent(Interface.this, dataInput));	
				}
				
				System.err.println("DATA ENTRANTE "+dataInput);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		@Override
	public void userChatUpdate(MyEvent evt) {
		
		try{
            dataOutputStream.writeUTF(player.getUsername() +" : "+evt.getData());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

	@Override
	public void sendData(MyEvent evt) {
		try{
            dataOutputStream.writeUTF(evt.getData());
        } catch (Exception e){
            e.printStackTrace();
        }
  
	}
}

