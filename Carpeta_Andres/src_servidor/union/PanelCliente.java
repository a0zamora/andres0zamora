package union;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;


/**
 * Panel para mostrar la conversación y pedir al usuario el texto que quiere
 * enviar.
 * @author andres
 */
public class PanelCliente extends JFrame
{
	private Container contenedor;
    /** Scroll */
    private JScrollPane scroll;

    /** Area para mostrar la conversación */
    private JTextArea textArea;
    
    /** Para pedir el texto al usuario */
    private JTextField textField;
    
    /** Botón para enviar el texto */
    private JButton boton;
    
    /** para probar las maricas listas */
    private JList partidas;
    private String nombresColores[]= {};
    private ListModel juegos;
    
    private int id = -1;
    /**
     * Crea el panel con todos sus componentes. Un Area de texto para ver la
     * conversación, un textField para escribir el texto que queremos enviar y
     * un botón de enviar.
     *            Contenedor en el que añadir todos los componentes
     */
    public PanelCliente()
    {
    	super("Cliente MagicRoot");
    	contenedor= getContentPane();
        contenedor.setLayout(new FlowLayout());
        textArea = new JTextArea(10, 25);
        textArea.setEditable(false);
        contenedor.add(new JScrollPane(textArea));
        
        //Texto Action Listener
        textField = new JTextField(25);
        contenedor.add(textField);
        
        boton = new JButton("Enviar");
        contenedor.add(boton);
    
        partidas = new JList(nombresColores);
		partidas.setFixedCellWidth(300);
		partidas.setFixedCellHeight(15);
		partidas.setVisibleRowCount(5);
		partidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contenedor.add(new JScrollPane(partidas));
		
        setSize(350,350);
        setVisible(true);
        
    }
    /** Añade el actionListener que se le pasa tanto a pulsar <intro> en el
     * textField como al botón.
     */
    public void addActionListener(ActionListener accion)
    {
        textField.addActionListener( accion );
        boton.addActionListener( accion );
        
    }

    /**
     *      Añade el texto que se le pasa al textArea.
     */
	public void addTexto(String texto) 
	{

		if (texto.startsWith("Crear"))
		{
			modificarLista(texto);
		}
		else if (texto.startsWith("Eliminar")) {
			eliminarElemento(texto);
		}
		else if ( texto.startsWith("Nombre") ){
			modificarNombre( texto );
		}

		else {
			textArea.append(texto);
			textArea.append("\n");
			textArea.setCaretPosition(textArea.getText().length() - 1);
		}

	}

    /**
     * Devuelve el texto que hay en el textfield y lo borra.
     * @return texto
     */
    public String getTexto()
    {
        String texto = textField.getText();
        textField.setText("");
        return texto;
    }
    /**
     * Esta funcion modifica la lista de partidas agregando asi la nueva partida creada por un cliente!!!
     * @param texto
     */
	public void modificarLista(String texto){
    	String aux[];
    	aux = new String [nombresColores.length+1];
    	for(int i=0; i<nombresColores.length; i++){
    		aux[i]=nombresColores[i];
    	}
    	aux[nombresColores.length]=texto;
    	nombresColores=aux;
		partidas.setListData(nombresColores);
    }
	/**
	 * Esta funcion sirve para eliminar la partida contenida en texto
	 * @param texto
	 */
	public void eliminarElemento(String texto)
	{
		String aux3;
		String aux2 = texto.substring(9);
		String aux[];
		int j=0;
		
		aux = new String [nombresColores.length - 1];
		for(int i=0 ; i < nombresColores.length ; i++){
			aux3=nombresColores[i].substring(6);
			if(!aux2.equals(aux3) )
			{
				aux[j]=nombresColores[i];
				j++;
			}
		}
		nombresColores = aux;

		partidas.setListData(nombresColores);
		partidas.repaint();
		contenedor.repaint();
	}
	
	public void modificarNombre(String texto){
		 id = Integer.parseInt( ( texto.substring( 7 ) ) );
		 setTitle("Cliente MagicRoot # "+ Integer.toString(id));
	}
	
//    public static void main(String[] args) {
//		PanelCliente aplicacion= new PanelCliente();
//		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
}