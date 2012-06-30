package com.andresoftware.tesis.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import org.hibernate.Session;

import com.andresoftware.tesis.model.SessionHibernate;
import com.andresoftware.tesis.model.VerificarLogin;


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
                Runnable nuevoCliente = new ClientThread(chatList, cliente, i);
                Thread hilo = new Thread(nuevoCliente);
                hilo.start();
                i++;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
	}

	 public void IniciarTablaRegistro() {
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