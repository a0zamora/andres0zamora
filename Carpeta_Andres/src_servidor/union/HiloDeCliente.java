package union;

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

import servidor.SessionHibernate;
import servidor.VerificarLogin;

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
import commands.Mi_IdCommand;
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
public class HiloDeCliente implements Runnable, ListDataListener {
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
				
				if (texto.startsWith(VersionCommand.CADENA_COMANDO)) {
					
					verificarVersionServidor(texto);
					
				} else if (texto.startsWith(CrearCommand.CADENA_COMANDO)) {
					
					crearPartida();
					Command comando = new CrearCommand(id);
					partida.enviarMensajeMaquina(comando);
					
				} else if (texto.startsWith(Mi_IdCommand.CADENA_COMANDO) && !idCliente) {
					
					procesarMiIdComando(texto);

				} else if (texto.startsWith("eliminar_partida")) {
					
					eliminarPartida();
					
				} else if (texto.startsWith(DesconectarInvitadoCommand.CADENA_COMANDO)) {
					
					partida.sacarInvitado();
				}
				
				else if (texto.startsWith(JugListoCommand.CADENA_COMANDO)) {
					
					Command comando = new JugListoCommand(texto);
					if (partida.llenarListaCartas(comando)) {
						partida.enviarMensajeMaquina(comando);
					} else {
						comando = new ServerKick(id);
						partida.enviar(comando);
					}
					
				} else if (texto.startsWith(EleguirTableroCommand.CADENA_COMANDO)) {
					Command comando = new EleguirTableroCommand(texto);
					partida.enviarMensajeMaquina(comando);

				} else if (texto.startsWith(JugDesconectadoRoomCommand.CADENA_COMANDO)) {

					Command comando = new JugDesconectadoRoomCommand(id);
					desconectarRoom(comando);
					
				} else if (texto.equals(EnviarConectadosCommand.CADENA_COMANDO)) {
					
					enviarConexiones();
					
				} else if (texto.startsWith(UnirCommand.CADENA_COMANDO)) {
					
					Command comando = new UnirCommand(texto);

					if (unirPartida(comando)) {
						comando = new UnirCommand(id);
						partida.enviarMensajeMaquina(comando);
					}
					else{
						comando = new JugDesconectadoRoomCommand(id);
						enviarMensaje(comando.convertirAString());
					}
					
				} else if (texto.startsWith(JugarCommand.CADENA_COMANDO)) {
					
					Command comando = new JugarCommand(texto);
					partida.enviarMensajeMaquina(comando);

				} else if (texto.startsWith(GameOver.CADENA_COMANDO)) {
					
					Command comando = new GameOver(texto);
					partida.enviarMensajeMaquina(comando);

				} else if (texto.startsWith(RevanchaAzulCommand.CADENA_COMANDO)) {
					
					Command comando = new RevanchaAzulCommand(texto);
					partida.enviarMensajeMaquina(comando);

				} else if (texto.startsWith(RevanchaRojoCommand.CADENA_COMANDO)) {
					
					Command comando = new   RevanchaRojoCommand(texto);
					partida.enviarMensajeMaquina(comando);

				} else if (texto.startsWith(DesconexionJugandoCommand.CADENA_COMANDO)) {
					
					if (estado && creadorDePartida) {
						Command comando = new JugDesconectadoRoomCommand(id);
						desconectarRoom(comando);
					} else if (estado && !creadorDePartida) {
						Command comando = new JugDesconectadoRoomCommand(id);
						desconectarRoom(comando);
					}
				} else if (estado) {
					
					partida.enviarMensajeRoom(texto, id);
					
				} else if (texto.startsWith("mostrar_partidas")) {
					
					partidasCliente();
					
				}

				else {
					enviar_a_Todos(texto);
				}
			}

		} catch (Exception e) {
			
			manejarDesconexion();
			
		} // fin del manejo de la exepcion
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
	/**
	 * Envía el último texto de la charla por el socket. Se avisa a este método
	 * cada vez que se mete algo en charla, incluido cuando lo mete este mismo
	 * hilo. De esta manera, lo que un cliente escriba, se le reenviará para que
	 * se muestre en el textArea.
	 */
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