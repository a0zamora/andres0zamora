package magicroot.window;

import java.awt.Color;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import constants.WebServiceFactory;

import cliente.ServiciosWeb;
import cliente.ServiciosWebService;
/**
 * Clase que se va a encargar de manejar la publicidad ...
 * Por ahora solo le puse una animacion hecha por mi en .gif xD
 * @author andres
 *
 */
@SuppressWarnings("serial")
public class PublicityPanel extends ImagePanel implements Runnable{
	private JLabel logo;
	private ActivePlayer jugador;
	private int cambio;
	Thread crono;
	private List<byte[]> publicidad;
	private int i;
	/**
	 * Constructor del panel de publicidad
	 * @param w
	 * @param h
	 */
	public PublicityPanel(int w, int h) {
		cambio = 1;
		i = 0;
		llenarPanel(w, h);
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = servicio.getServiciosWebPort();
		publicidad = cliente.publicidad();
		crono = new Thread(this);
		logo = new JLabel(new ImageIcon("CardsPNG/logo3.gif"));
		logo.setBackground(Color.black);
		logo.setBounds((int) (w * 0.20), (int) (h * 0.02), (int) (w * 0.60),
				(int) (h * 0.9));
		logo.setToolTipText("Publicidad");
		add(logo);
		crono.start();	
	}
	/**
	 * para llenar el panel con las cosas que pide Cesar
	 * @param w
	 * @param h
	 */
	public void llenarPanel(int w, int h) {
		
		wid = w;
		hei = h;
		changeImage("CardsPNG/Fondonegro.png");
		setMyBorder();
	}
	/**
	 * Para obtener la publicidad que corresponde a este Jugador
	 * @param jugador
	 */
	public void setJugador(ActivePlayer jugador) {
		this.jugador = jugador;
	}
	/**
	 * Aqui se procesa el tiempo para poner la publicidad del jugador
	 * cada 30 segundos cambia la imagen
	 */
	@SuppressWarnings("static-access")
	public void run() {
		try {
			while (true) {
				if (cambio == 1) {
					cambio = 0;
					logo.setIcon(new ImageIcon("CardsPNG/logo3.gif"));
					crono.sleep(4000);
				} else {
					if(i==5)
						i=0;
					cambio = 1;
					//logo.setIcon(new ImageIcon("CardsJPG/FireTable.jpg"));
					logo.setIcon(new ImageIcon(publicidad.get(i)));
					i++;
					crono.sleep(2000);
				}

			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}	
}