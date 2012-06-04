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
import com.andresoftware.tesis.commands.CommandCreateUserAnswer;
import com.andresoftware.tesis.commands.CommandLogin;
import com.andresoftware.tesis.commands.CommandLoginAnswer;
import com.andresoftware.tesis.model.CartaBean;
import com.andresoftware.tesis.model.CartasDisponiblesBean;
import com.andresoftware.tesis.model.JugadorBean;
import com.andresoftware.tesis.model.SessionHibernate;
import com.andresoftware.tesis.model.VerificarLogin;

import commands.BorrarConexionCommand;
import commands.ClientNotFound;
import commands.Command;
import commands.ConexionNuevaCommand;
import commands.CrearCommand;
import commands.HandCheck;
import commands.JugDesconectadoRoomCommand;
import commands.Mi_IdCommand;
import commands.VersionCommand;

/**
 * Hilo encargado de atender a un cliente.
 * 
 * @author andres
 */
public class HiloDeCliente implements Runnable, ListDataListener {
	/** Para saber si esta vivo el hilo */
	private boolean corriendo = true;

	/** Lista en la que se guarda toda la charla */
	private DefaultListModel charla;

	/** Socket al que está conectado el cliente */
	private Socket socket;

	/** Para lectura de datos en el socket */
	private DataInputStream dataInput;

	/** Para escritura de datos en el socket */
	private DataOutputStream dataOutput;

	/** Para identificar a los Hilos */
	private int id;

	/** Para saber si este hilo es creador de una partida */
	private boolean creadorDePartida;

	/** La partida que creo este hilo o la partida a la que esta suscrito */
	private Partida partida;

	/** Lista de partidas creadas */
	private List<Partida> partidas;

	/** para saber si el hilo esta conectado a un room o a una partida */
	private boolean estado;

	private boolean idCliente = false;

	private String nombreUsr;

	private boolean handCheck = false;

	/** lista de todos los clientes que estan conectados en este momento */
	private List<ClienteConexion> listaClienteConexion;

	/**
	 * Crea una instancia de esta clase y se suscribe a cambios en la charla.
	 * 
	 * @param charla
	 *            La lista de textos que componen la charla del chat
	 * @param socket
	 *            Socket con el cliente.
	 */
	public HiloDeCliente(DefaultListModel charla, Socket socket, int id,
			List<Partida> partidas, List<ClienteConexion> listaClienteConexion) {
		estado = false;
		this.partidas = partidas;
		this.charla = charla;
		this.socket = socket;
		this.id = id;
		creadorDePartida = false;
		this.listaClienteConexion = listaClienteConexion;

		try {
			dataInput = new DataInputStream(socket.getInputStream());
			dataOutput = new DataOutputStream(socket.getOutputStream());
			charla.addListDataListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		partida = new Partida();
		partidasCliente();
	}

	public HiloDeCliente() {
	}


	/**
	 * Atiende el socket.
	 */

	public void run() {
		try {
			while (corriendo) {

				String texto = dataInput.readUTF();
				if(texto.startsWith(CommandLogin.CADENA_COMANDO)){
					verificarLogin(texto);
				}else if(texto.startsWith(CommandCreateUser.CADENA_COMANDO)){
					crearNuevoUsuario(texto);
				}
				else {
					enviar_a_Todos(texto);
				}
				
				System.out.println("Llego lo siguiente: "+texto);
			}

		} catch (Exception e) {
			System.err.println("Desconectado");
			//			manejarDesconexion();

		} // fin del manejo de la exepcion
	}
	private void crearNuevoUsuario(String texto) {
		CommandCreateUser createUser = new CommandCreateUser(texto);
		String answer="";
		JugadorBean jugador = new JugadorBean();
		jugador.setNombreUsr(createUser.getName());
		jugador.setCorreo("ninguno");
		jugador.setClave(createUser.getPassword());
		jugador.setPuntos(6);
		jugador.setNivel(3);
		jugador.setNombreAvatar("CardsJPG/Heroe.jpeg");

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		List<JugadorBean> lista1;
		List<CartaBean> cartasAgua = new ArrayList<CartaBean>();
		List<CartaBean> cartasFuego = new ArrayList<CartaBean>();
		lista1 = new ArrayList<JugadorBean>();
		lista1 = session.createCriteria(JugadorBean.class)
				.add(Restrictions.eq("nombreUsr", createUser.getName())).list();
		cartasAgua = session.createCriteria(CartaBean.class).add(Restrictions.eq("elemento", "agua")).list();
		cartasFuego = session.createCriteria(CartaBean.class).add(Restrictions.eq("elemento", "fuego")).list();
		for (int i = 0; i < 4; i++) {
			CartasDisponiblesBean disponibles = new CartasDisponiblesBean();
			disponibles.setJugadorRef(jugador);
			disponibles.setCartaRef(cartasAgua.get(i));
			jugador.getCartasDisponiblesRef().add(disponibles);
		}
		for(int i=0;i<6;i++){
			CartasDisponiblesBean disponibles = new CartasDisponiblesBean();
			disponibles.setJugadorRef(jugador);
			disponibles.setCartaRef(cartasFuego.get(i));
			jugador.getCartasDisponiblesRef().add(disponibles);
		}
		if (lista1.isEmpty()) {
			session.save(jugador);
			session.getTransaction().commit();
			session.close();
			answer="true";

		} else {
			session.getTransaction().commit();
			session.close();
			answer="false";
		}
		CommandCreateUserAnswer commandAnswer = new CommandCreateUserAnswer(
				CommandCreateUserAnswer.CADENA_COMANDO
				+" "+answer);
		enviarMensaje(commandAnswer.convertirAString());
	}

	//--------------------------------------------------------------------------------------
	private void verificarLogin(String entrante) {
		String answer="";
		CommandLogin login = new CommandLogin(entrante);
		Session session = SessionHibernate.getInstance().getSession();

		session.beginTransaction();

		List<JugadorBean> user;
		user = new ArrayList<JugadorBean>();
		List<VerificarLogin> aux = new ArrayList<VerificarLogin>();
		user = session.createCriteria(JugadorBean.class)
				.add(Restrictions.eq("nombreUsr", login.getName()))
				.add(Restrictions.eq("clave", login.getPassword())).list();
		aux = session.createCriteria(VerificarLogin.class)
				.add(Restrictions.eq("nombreUsr", login.getName())).list();
		if(!user.isEmpty()){
			if(aux.isEmpty()){
				answer = "true";
			}else{
				answer = "false";
			}	
		}else{
			answer = "false";
		}
		CommandLoginAnswer loginAnswer = new CommandLoginAnswer(CommandLoginAnswer.CADENA_COMANDO+" "+answer);
		enviarMensaje(loginAnswer.convertirAString());
	}

	//*********************************************************************//
	//***                            Metodos                            ***//
	//*********************************************************************//
	public void procesarMiIdComando(String texto) {

		Mi_IdCommand comando = new Mi_IdCommand(texto);
		id = comando.getId();
		nombreUsr = comando.getNombreUsr();
		Command conexion = new ConexionNuevaCommand(
				ConexionNuevaCommand.CADENA_COMANDO + " "
						+ Integer.toString(id) + " "
						+ comando.getNombreUsr());
		enviarConectados();
		ClienteConexion nuevo = new ClienteConexion(
				Integer.toString(id), nombreUsr);
		listaClienteConexion.add(nuevo);
		enviar_a_Todos(conexion.convertirAString());
		idCliente = true;
		setInfo();
	}
	// *******************************************************************************//

	public void verificarVersionServidor(String texto) {

		VersionCommand comando = new VersionCommand(texto);
		// si las versiones son iguales
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(Version.VERSION);
		int tamPartes = partes.length;
		for (int k = 0; k < tamPartes; k++) {
			if (partes[k].startsWith(comando.getVersion())) {
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
					"Borrar_Conexion " + Integer.toString(id) + " " + nombreUsr);
			enviar_a_Todos(borrar.convertirString());
			borrarConexion();
		}
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
	public int getId() {
		return id;
	}

	// *******************************************************************************//
	public void enviarMensaje(String text) {
		try {
			dataOutput.writeUTF(text);
		} catch (IOException e) {
			System.err.println("Error Enviando Jugada");
		}
	}

	// *******************************************************************************//
	public void enviar_a_Todos(String texto) {
		synchronized (charla) {
			charla.addElement(texto);
		}
	}

	// *******************************************************************************//
	public void crearPartida() {
		partida = new Partida(this);
		partida.setNombre(nombreUsr);

		Command comando = new CrearCommand(getId());
		enviar_a_Todos(comando.convertirAString() + " " + nombreUsr);
		partidas.add(partida);
	}

	public void enviarjugadaAPartida(String str) {
		partida = new Partida(this);
		partida.enviarJugada(str);
		partidas.add(partida);

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

		for (int i = 0; i < partidas.size(); i++) {
			if (!partidas.get(i).getEstado()) {
				try {
					dataOutput.writeUTF(CrearCommand.CADENA_COMANDO + " "
							+ Integer.toString(partidas.get(i).getId()) + " "
							+ partidas.get(i).getNombre());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	// *******************************************************************************//

	public boolean unirPartida(Command comando) {
		estado = true;
		for (int i = 0; i < partidas.size(); i++) {
			if ((comando.getId() == partidas.get(i).getId())
					&& !partidas.get(i).getEstado()) {
				partida = partidas.get(i);
				partida.setEstado(true);
				partida.setHiloRojo(this);
				partida.setEstadoHiloAzul(true);
				synchronized (charla) {
					charla.addElement("Eliminar "
							+ Integer.toHexString(partida.getId()));
				}
				return true;
			}
		}
		return false;

	}

	// *******************************************************************************//
	public void desconectarRoom(Command comando) {
		partida.enviarMensajeMaquina(comando);
	}

	// *******************************************************************************//
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	// *******************************************************************************//
	public void desconectarCreador() {

		for (int i = 0; i < partidas.size(); i++) {
			if (partida.getId() == partidas.get(i).getId()) {
				partidas.remove(i);
				break;
			}
		}
		creadorDePartida = false;
		estado = false;
		partida = null;
	}

	// *******************************************************************************//
	public void eliminarPartida() {
		for (int i = 0; i < partidas.size(); i++) {
			if (partida.getId() == partidas.get(i).getId()) {
				partidas.remove(i);
				creadorDePartida = false;
				partida = null;
				enviar_a_Todos("Eliminar " + Integer.toString(id));
				break;
			}
		}
	}

	// *******************************************************************************//
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
		partida = null;
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
			if (listaClienteConexion.get(i).getNombreUsr().equals(nombreUsr)) {
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
	// *******************************************************************************//
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
				"Borrar_Conexion " + Integer.toString(id) + " " + nombreUsr);
		enviar_a_Todos(borrar.convertirString());
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
	// **                                SIN USO                                    **//
	// *******************************************************************************//
	public void intervalRemoved(ListDataEvent e) {
	}

	public void contentsChanged(ListDataEvent e) {
	}
	// *******************************************************************************//

}