package com.andresoftware.tesis.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.andresoftware.tesis.commands.CommandCreateUser;
import com.andresoftware.tesis.commands.CommandLogin;
import com.andresoftware.tesis.model.SessionHibernate;
import com.andresoftware.tesis.model.VerificarLogin;
import com.andresoftware.tesis.responses.Responses;
import com.andresoftware.tesis.statemachine.*;
import commands.BorrarConexionCommand;
import commands.ClientNotFound;
import commands.Command;
import commands.ConexionNuevaCommand;
import commands.CrearCommand;
import commands.DesconectarInvitadoCommand;
import commands.DesconexionJugandoCommand;
import commands.EleguirTableroCommand;
import commands.EnviarConectadosCommand;
import commands.GameOver;
import commands.HandCheck;
import commands.JugDesconectadoRoomCommand;
import commands.JugListoCommand;
import commands.JugarCommand;
import commands.RevanchaAzulCommand;
import commands.RevanchaRojoCommand;
import commands.ServerKick;
import commands.UnirCommand;
import commands.VersionCommand;

/**
 * Hilo encargado de atender a un cliente.
 * 
 * @author andres
 */
public class ClientThread implements Runnable, ListDataListener {
	/** Para saber si esta vivo el hilo */
	private boolean corriendo = true;

	/** Lista en la que se guarda toda la charla */
	private DefaultListModel charla;

	/** Socket al que está conectado el cliente */
	@SuppressWarnings("unused")
	private Socket socket;

	/** Lista de partidas creadas */
	private List<GameInterface> gameInterfaceList;

	/** Para lectura de datos en el socket */
	private DataInputStream dataInput;

	/** Para escritura de datos en el socket */
	private DataOutputStream dataOutput;

	/** Para identificar a los Hilos */
	private int id;

	public void setId(int id) {
		this.id = id;
	}
	/** Para saber si este hilo es creador de una partida */
	private boolean creadorDePartida;

	/** para saber si el hilo esta conectado a un room o a una partida */
	private boolean estado;

	/** lista de todos los clientes que estan conectados en este momento */
	public List<ClientConection> listaClienteConexion;

	@SuppressWarnings("unused")
	private boolean idCliente = false;

	public String userName;

	private boolean handCheck = false;

	private Responses responses;

	/** La partida que creo este hilo o la partida a la que esta suscrito */
	private GameInterface gameInterface;

	/**
	 * Crea una instancia de esta clase y se suscribe a cambios en la charla.
	 * 
	 * @param charla
	 *            La lista de textos que componen la charla del chat
	 * @param socket
	 *            Socket con el cliente.
	 */
	public ClientThread(DefaultListModel charla, Socket socket, int id,
			List<GameInterface> partidas, List<ClientConection> listaClienteConexion) {
		estado = false;
		this.gameInterfaceList = partidas;
		this.charla = charla;
		this.socket = socket;
		this.id = id;
		creadorDePartida = false;
		this.listaClienteConexion = listaClienteConexion;
		responses = new Responses(this);

		try {
			dataInput = new DataInputStream(socket.getInputStream());
			dataOutput = new DataOutputStream(socket.getOutputStream());
			charla.addListDataListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ClientThread() {
	}


	/**
	 * Atiende el socket.
	 */

	public void run() {
		try {
			while (corriendo) {

				String text = dataInput.readUTF();
				System.err.println("Texto entrante= "+text);
				if(text.startsWith(CommandLogin.CADENA_COMANDO)){
					loginVerification(text);
				}else if(text.startsWith(CommandCreateUser.CADENA_COMANDO)){
					createNewUser(text);
				}else if (text.startsWith(VersionCommand.CADENA_COMANDO)) {

					verificarVersionServidor(text);

				} else if (text.startsWith(CrearCommand.CADENA_COMANDO)) {

					crearPartida();
					Command comando = new CrearCommand(id);
					gameInterface.enviarMensajeMaquina(comando);

				} else if (text.startsWith("eliminar_partida")) {

					eliminarPartida();

				} else if (text.startsWith(DesconectarInvitadoCommand.CADENA_COMANDO)) {

					gameInterface.sacarInvitado();
				}

				else if (text.startsWith(JugListoCommand.CADENA_COMANDO)) {

					Command comando = new JugListoCommand(text);
					if (gameInterface.llenarListaCartas(comando)) {
						gameInterface.enviarMensajeMaquina(comando);
					} else {
						comando = new ServerKick(id);
						gameInterface.enviar(comando);
					}

				} else if (text.startsWith(EleguirTableroCommand.CADENA_COMANDO)) {
					Command comando = new EleguirTableroCommand(text);
					gameInterface.enviarMensajeMaquina(comando);

				} else if (text.startsWith(JugDesconectadoRoomCommand.CADENA_COMANDO)) {

					Command comando = new JugDesconectadoRoomCommand(id);
					desconectarRoom(comando);

				} else if (text.equals(EnviarConectadosCommand.CADENA_COMANDO)) {

					enviarConexiones();

				} else if (text.startsWith(UnirCommand.CADENA_COMANDO)) {

					Command comando = new UnirCommand(text);

					if (unirPartida(comando)) {
						comando = new UnirCommand(id);
						gameInterface.enviarMensajeMaquina(comando);
					}
					else{
						comando = new JugDesconectadoRoomCommand(id);
						enviarMensaje(comando.convertirAString());
					}

				} else if (text.startsWith(JugarCommand.CADENA_COMANDO)) {

					Command comando = new JugarCommand(text);
					gameInterface.enviarMensajeMaquina(comando);

				} else if (text.startsWith(GameOver.CADENA_COMANDO)) {

					Command comando = new GameOver(text);
					gameInterface.enviarMensajeMaquina(comando);

				} else if (text.startsWith(RevanchaAzulCommand.CADENA_COMANDO)) {

					Command comando = new RevanchaAzulCommand(text);
					gameInterface.enviarMensajeMaquina(comando);

				} else if (text.startsWith(RevanchaRojoCommand.CADENA_COMANDO)) {

					Command comando = new   RevanchaRojoCommand(text);
					gameInterface.enviarMensajeMaquina(comando);

				} else if (text.startsWith(DesconexionJugandoCommand.CADENA_COMANDO)) {

					if (estado && creadorDePartida) {
						Command comando = new JugDesconectadoRoomCommand(id);
						desconectarRoom(comando);
					} else if (estado && !creadorDePartida) {
						Command comando = new JugDesconectadoRoomCommand(id);
						desconectarRoom(comando);
					}
				} else if (estado) {

					gameInterface.enviarMensajeRoom(text, id);

				} else if (text.startsWith("mostrar_partidas")) {

					partidasCliente();

				}

				else {
					enviar_a_Todos(text);
				}
			}

		} catch (Exception e) {

			manejarDesconexion();
			System.err.println("Jugador desconectado");

		} // fin del manejo de la exepcion
		
	}
	//--------------------------------------------------------------------------------------
	private void loginVerification(String text) {
		responses.loginVerification(text);
	}
	//--------------------------------------------------------------------------------------
	private void createNewUser(String text) {
		responses.createNewUser(text);
	}
	//--------------------------------------------------------------------------------------
	public void sendToAllUsers(String texto) {
		synchronized (charla) {
			charla.addElement(texto);
		}
	}
	//--------------------------------------------------------------------------------------	
	public void enviar_a_Todos(String texto) {
		synchronized (charla) {
			charla.addElement(texto);
		}
	}
	//--------------------------------------------------------------------------------------
	public void enviarMensaje(String text) {
		try {
			dataOutput.writeUTF(text);
			System.err.println("Data saliente: "+text);
		} catch (IOException e) {
			System.err.println("Error Enviando Jugada");
		}
	}
	//*********************************************************************//
	//***                            Metodos                            ***//
	//*********************************************************************//

	public void verificarVersionServidor(String texto) {

		VersionCommand comando = new VersionCommand(texto);
		// si las versiones son iguales
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(Version.VERSION);
		int tamPartes = partes.length;
		for (int k = 0; k < tamPartes; k++) {
			if (partes[k].startsWith(comando.getVersion())) {
				@SuppressWarnings("unused")
				Command comandoCheck = new HandCheck(id);
				// enviarMensaje(comandoCheck.convertirAString());
				handCheck = true;
				break;
			}

		}// si no econtro una version soportada
		if (!handCheck) {
			// envia el comando de cliente no encontrador
			Command clientCommand = new ClientNotFound(id);
			enviarMensaje(clientCommand.convertirAString());
			// mata el hilo
			corriendo = false;
			borrarRegistro();
			BorrarConexionCommand borrar = new BorrarConexionCommand(
					"Borrar_Conexion " + Integer.toString(id) + " " + userName);
			sendToAllUsers(borrar.convertirString());
			borrarConexion();
		}
	}
	// *******************************************************************************//
	public int getId() {
		return id;
	}
	// *******************************************************************************//
	public void crearPartida() {
		gameInterface = new GameInterface(this);
		gameInterface.setName(userName);

		Command comando = new CrearCommand(getId());
		sendToAllUsers(comando.convertirAString() + " " + userName);
		gameInterfaceList.add(gameInterface);
	}
	// *******************************************************************************//
	public void enviarjugadaAPartida(String str) {
		gameInterface = new GameInterface(this);
		gameInterface.enviarJugada(str);
		gameInterfaceList.add(gameInterface);

	}
	// *******************************************************************************//
	public void setInfo() {
		try {

			dataOutput.writeUTF("Nombre " + Integer.toString(id));
		} catch (IOException e) {
			System.err.println("Error enviando el id al cliente");
		}
	}
	// *******************************************************************************//
	public void partidasCliente() {

		for (int i = 0; i < gameInterfaceList.size(); i++) {
			if (!gameInterfaceList.get(i).getEstado()) {
				try {
					dataOutput.writeUTF(CrearCommand.CADENA_COMANDO + " "
							+ Integer.toString(gameInterfaceList.get(i).getId()) + " "
							+ gameInterfaceList.get(i).getNombre());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	// *******************************************************************************//
	public boolean unirPartida(Command comando) {
		estado = true;
		for (int i = 0; i < gameInterfaceList.size(); i++) {
			if ((comando.getId() == gameInterfaceList.get(i).getId())
					&& !gameInterfaceList.get(i).getEstado()) {
				gameInterface = gameInterfaceList.get(i);
				gameInterface.setEstado(true);
				gameInterface.setHiloRojo(this);
				gameInterface.setEstadoHiloAzul(true);
				synchronized (charla) {
					charla.addElement("Eliminar "
							+ Integer.toHexString(gameInterface.getId()));
				}
				return true;
			}
		}
		return false;
	}
	// *******************************************************************************//
	public void desconectarRoom(Command comando) {
		gameInterface.enviarMensajeMaquina(comando);
	}
	// *******************************************************************************//
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	// *******************************************************************************//
	public void desconectarCreador() {

		for (int i = 0; i < gameInterfaceList.size(); i++) {
			if (gameInterface.getId() == gameInterfaceList.get(i).getId()) {
				gameInterfaceList.remove(i);
				break;
			}
		}
		creadorDePartida = false;
		estado = false;
		gameInterface = null;
	}
	// *******************************************************************************//
	public void eliminarPartida() {
		for (int i = 0; i < gameInterfaceList.size(); i++) {
			if (gameInterface.getId() == gameInterfaceList.get(i).getId()) {
				gameInterfaceList.remove(i);
				creadorDePartida = false;
				gameInterface = null;
				sendToAllUsers("Eliminar " + Integer.toString(id));
				break;
			}
		}
	}
	// *******************************************************************************//
	@SuppressWarnings("unchecked")
	public void borrarRegistro() {

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		List<VerificarLogin> verificarLogin = new ArrayList<VerificarLogin>();
		verificarLogin = session.createCriteria(VerificarLogin.class)
				.add(Restrictions.eq("idJugador", id)).list();
		if (!verificarLogin.isEmpty()) {
			session.delete(verificarLogin.get(0));
		}
		session.getTransaction().commit();
		session.close();
	}

	// *******************************************************************************//
	public void anularPartida() {
		gameInterface = null;
	}

	// *******************************************************************************//
	public void enviarConectados() {
		for (int i = 0; i < listaClienteConexion.size(); i++) {
			try {
				dataOutput.writeUTF(ConexionNuevaCommand.CADENA_COMANDO + " "
						+ listaClienteConexion.get(i).getNivel() + " "
						+ listaClienteConexion.get(i).getNombreUsr());
			} catch (IOException e) {
				System.err.println("Error enviando conectados");
			}
		}
	}

	// *******************************************************************************//
	public void borrarConexion() {
		for (int i = 0; i < listaClienteConexion.size(); i++) {
			if (listaClienteConexion.get(i).getNombreUsr().equals(userName)) {
				listaClienteConexion.remove(i);
				break;
			}
		}
	}

	// *******************************************************************************//
	public void enviarConexiones() {
		for (int i = 0; i < listaClienteConexion.size(); i++) {
			try {
				dataOutput.writeUTF(ConexionNuevaCommand.CADENA_COMANDO + " "
						+ listaClienteConexion.get(i).getNivel() + " "
						+ listaClienteConexion.get(i).getNombreUsr());
			} catch (IOException e) {
				System.err.println("Error enviando conexion despues de jugar");
			}

		}
	}
	//	// *******************************************************************************//
	public void manejarDesconexion() {
		corriendo = false;
		if (estado && creadorDePartida) {
			Command comando = new JugDesconectadoRoomCommand(id);
			desconectarRoom(comando);
		} else if (!estado && creadorDePartida) {
			eliminarPartida();
		} else if (estado && !creadorDePartida) {
			Command comando = new JugDesconectadoRoomCommand(id);
			desconectarRoom(comando);
		}
		borrarRegistro();
		BorrarConexionCommand borrar = new BorrarConexionCommand(
				"Borrar_Conexion " + Integer.toString(id) + " " + userName);
		sendToAllUsers(borrar.convertirString());
		borrarConexion();
	}

	// *******************************************************************************//
	public boolean isCreadorDePartida() {
		return creadorDePartida;
	}

	// *******************************************************************************//
	public void setCreadorDePartida(boolean creadorDePartida) {
		this.creadorDePartida = creadorDePartida;
	}
	// *******************************************************************************//
	// **                                Lista                                      **//
	// *******************************************************************************//
	public void intervalRemoved(ListDataEvent e) {
	}

	public void contentsChanged(ListDataEvent e) {
	}
	// *******************************************************************************//
	public void intervalAdded(ListDataEvent e) {
		String texto = (String) charla.getElementAt(e.getIndex0());
		if ((corriendo && !estado)
				|| texto.startsWith(ConexionNuevaCommand.CADENA_COMANDO)
				|| texto.startsWith(BorrarConexionCommand.CADENA_COMANDO)) {
			try {
				dataOutput.writeUTF(texto);
			} catch (Exception excepcion) {
				System.err.println("Error escribiendo en charla en el hilo #"
						+ Integer.toString(id));
			}
		}

	}
	// *******************************************************************************//

}