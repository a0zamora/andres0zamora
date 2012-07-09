package com.andresoftware.tesis.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import org.hibernate.Session;



import com.andresoftware.tesis.model.SessionHibernate;
import com.andresoftware.tesis.model.VerificarLogin;
import com.andresoftware.tesis.statemachine.ClientConection;
import com.andresoftware.tesis.statemachine.GameInterface;


/**
 * Servidor de chat.
 * Acepta conexiones de clientes, crea un hilo para atenderlos, y espera la
 * siguiente conexion.
 * @author andres
 *
 */
public class MagicRootServer
{
	/** Lista en la que se guaradara toda la conversacion */
	private DefaultListModel chatList = new DefaultListModel();
	/** lista de todas las partidas que han sido creadas por los Hilos de clientes */
	private List<GameInterface> listaPartidas;

	/** lista de todos los clientes que estan conectados en este momento */
	private List<ClientConection> listaClienteConexion;
	/**
	 * Instancia esta clase.
	 * @param args
	 */
	public static void main(String[] args)
	{
		new MagicRootServer();
	}

	/**
	 * Se mete en un bucle infinito para ateder clientes, lanzando un hilo
	 * para cada uno de ellos.
	 */
	public MagicRootServer()
	{
		IniciarTablaRegistro();

		int i=0;
		try
		{
			ServerSocket socketServidor = new ServerSocket(5557);
			while (true)
			{
				Socket cliente = socketServidor.accept();//Este es el buffer compartido
				Runnable nuevoCliente = new ClientThread(chatList, cliente, i,listaPartidas, listaClienteConexion);
				Thread hilo = new Thread(nuevoCliente);
				hilo.start();
				i++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void IniciarTablaRegistro() {
		listaPartidas = new ArrayList<GameInterface>();
		listaClienteConexion = new ArrayList<ClientConection>();
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		List<VerificarLogin> lista;
		lista = session.createCriteria(VerificarLogin.class).list();
		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				session.delete(lista.get(i));
			}
			session.getTransaction().commit();
			session.close();

		} else {
			session.getTransaction().commit();
			session.close();
		}
	}

}