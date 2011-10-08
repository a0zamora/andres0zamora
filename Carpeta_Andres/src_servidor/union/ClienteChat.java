package union;
import java.net.Socket;

import javax.swing.JFrame;

/**
 * Clase con el main de un cliente del chat.
 * Establece la conexion y crea la ventana y la clase de control.
 *@author andres
 */
//creardo
//jugarAzul 2 1 2 1 2 3 4 0

//jugarRojo 3 1 3 1 2 3 4 0

public class ClienteChat
{
    /** Socket con el servidor del chat */
    private Socket socket;
    
    /** GUI del cliente */
    private PanelCliente panel;
 
    
    //Mi_Id 1
    
    //creador
    //jugarAzul 1 1 1 1 2 3 4 0
  
    //Mi_Id 2
    //jugarRojo 2 1 3 1 2 3 4 0
    
    
    /**
     * Arranca el Cliente de chat.
     */
    public static void main(String[] args)
    {
        new ClienteChat();
    }
    
    /**
     * Crea la ventana, establece la conexión e instancia al controlador.
     */
    public ClienteChat()
    {
        try
        {
            creaYVisualizaVentana();
            socket = new Socket("localhost", 5557);
            
            ControlCliente control = new ControlCliente(socket, panel);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    

    /**
     * Crea una ventana, le mete dentro el panel para el cliente y la muestra por pantalla
     */
    private void creaYVisualizaVentana()
    {
    	panel = new PanelCliente();
    	panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}