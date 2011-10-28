package magicroot.window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import constants.WebServiceFactory;

import cliente.ServiciosWeb;
import cliente.ServiciosWebService;
import cliente.CartasDisponiblesTO;

@SuppressWarnings("serial")
public class StorePanel extends ImagePanel {

	private MyButton buyButton;
	private ImagePanel avatarImage;
	private ImagePanel fondo;
	private JLabel nameLabel;
	private JLabel fuerzanorte;
	private JLabel fuerzasur;
	private JLabel fuerzaeste;
	private JLabel fuerzaoeste;
	private JLabel costo;
	private JLabel puntosDisponibles;
	private JLabel elemento;
	private ImagePanel labia;
	private JLabel fondito;
	private PageableCardsPanel pgCardsPanel;
	private PlayCard card;
	private List<PlayCard> buyList;
	private int pintador=0;
	private ActivePlayer jugador;
	private int idCarta;
	private List<CartasDisponiblesTO> cartasparaventa;

	public StorePanel(int w, int h, ActivePlayer jugador) {
		
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = servicio.getServiciosWebPort();
		CartasDisponiblesTO  cardTO;
		PlayCard card;
		buyList = new ArrayList<PlayCard>();
		cartasparaventa = cliente.retornarCartas();
		for(int i=0;i<cartasparaventa.size();i++){
			cardTO = cartasparaventa.get(i);
			card = new PlayCard(""+cardTO.getId(),"CardsPNG/Bimg/cardBackg.png",
					cardTO.getFuerzaoeste(), cardTO.getFuerzaeste(), cardTO
							.getFuerzanorte(), cardTO.getFuerzasur(), cardTO.getElemento());
			card.setCost(cardTO.getCosto());
			
			buyList.add(card);
			
			
		}
		System.err.println("tama;o"+cartasparaventa.size());
		this.jugador = jugador;
		setMyBorder();
		setLayout(null);
		setWid(w);
		setHei(h);
		setSize(wid, hei);
		setBackground(Color.DARK_GRAY);
		changeImage("CardsPNG/Fondonegro.png");
	
		pgCardsPanel = new PageableCardsPanel((int)(wid*0.6),(int)(hei*0.30), 4, 0, 0);
		pgCardsPanel.setList(buyList);
		pgCardsPanel.setLocation((int) (wid * 0.2), (int) (hei * 0.01));
		pgCardsPanel.addListener(this);
		pgCardsPanel.setSelectable(true);
		add(pgCardsPanel);
		pgCardsPanel.paintPage();
		
		buyButton = new MyButton("", 0, "buttons/comprar1.jpg",
				"buttons/comprar2.jpg", "buttons/comprar3.jpg", "buttons/comprar4.jpg");
		buyButton.setBounds((int) (wid * 0.70), (int) (hei * 0.80),
				(int) (wid * 0.20), (int) (hei * 0.1));
		buyButton.SetImages();
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyButtonClicked();
			}
		});
		buyButton.setVisible(false);
		add(buyButton);

//		labia = new JTextArea("\tBienvenido al mercado de cartas Magicroot\n" +
//				"\nAqui vas a poder encontrar todas las cartas que tenemos a la venta. " +
//				"\nMagicRoot tiene una gran cantidad de cartas para que puedas subir de" +
//				"\nnivel en nuestro Juego de cartas. " +
//				"\nCada carta tiene un nivel y un costo relacionado respectivamente." +
//				"\nDependiendo de las cartas que tengas MagicRoot te ubicara en el nivel " +
//				"\nal que correspondas. " +
//				"\n\nPara comprar cartas solo debes clickear cualquiera de las cartas que" +
//				"\nse muestran arriba y luego oprimir el boton que se habilitara en la" +
//				"\nparte inferior derecha de la pantalla que dice COMPRAR" +
//				"\n\nIMPORTANTE: No puedes tener cartas repetidas.");
//		labia.setForeground(Color.white);
//		labia.setEditable(false);
//		labia.setFont(new Font("Dialog", Font.PLAIN, (int) (hei*0.035)));
//		labia.setOpaque(false);
		labia = new ImagePanel("CardsPNG/labia.png");
		labia.setBounds((int) (wid * 0.2), (int) (hei * 0.3),
				(int) (wid * 0.6), (int) (hei*0.65));
		add(labia);
		
		
		fondito= new JLabel(new ImageIcon("CardsPNG/column.gif"));
		fondito.setBounds((int) (wid * 0.3), (int) (hei * 0.45),
				(int) (wid * 0.35), (int) (hei * 0.3));
		fondito.setBackground(Color.black);
		fondito.setVisible(false);
		add(fondito);
		
	    nameLabel = new JLabel();
		fuerzanorte  = new JLabel();
		fuerzasur = new JLabel();
		fuerzaeste  = new JLabel();
		fuerzaoeste   = new JLabel();
		costo  = new JLabel();
		elemento  = new JLabel();		
		puntosDisponibles  = new JLabel();
		

		updateUI();
		repaint();

	}

	void setMyBorder() {

		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();

		CompoundBorder compound = BorderFactory.createCompoundBorder( //
				raisedbevel, loweredbevel);
		this.setBorder(compound);

	}


	@SuppressWarnings("deprecation")
	private void buyButtonClicked() {
		String resultadoCompra;
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = servicio.getServiciosWebPort();
		resultadoCompra= cliente.comercioComputadora(jugador.getUsername(), idCarta);
		if(resultadoCompra.equals("compra_exitosa")){
			JOptionPane.showMessageDialog(null, "Compra exitosa");
			jugador.setPoints(jugador.getPoints()-card.getCost());
			puntosDisponibles.setText("Tienes " + jugador.getPoints() +" puntos disponibles");
			
		}
		else if(resultadoCompra.equals("tiene_carta")){
			JOptionPane.showMessageDialog(null, "Ya tienes esta carta");
		}
		else if(resultadoCompra.equals("faltan_puntos")){
			JOptionPane.showMessageDialog(null, "Te faltan puntos para adquirir esta carta");
		}
		else if(resultadoCompra.equals("Tienes esa Carta en Oferta"))
			JOptionPane.showMessageDialog(null, "Tienes esta carta en Oferta");

	}

	private void increaseButtonClicked() {
		// TODO
	}

	public void setWid(int wid) {
		this.wid = wid;
	}
	

	public int getWid() {
		return wid;
	}

	public void setHei(int hei) {
		this.hei = hei;
	}

	public int getHei() {
		return hei;
	}
	
	

	public void paintSelectedCard(PlayCard cardSelected) {
		
		if(card !=null)
			remove(card);
		cardSelected.setRdyIcon(false);
		cardSelected.initBufferedImage();
		card= new PlayCard(cardSelected);
		card.setCost(cardSelected.getCost());
		idCarta = Integer.parseInt(card.getId());
		
		
			labia.setVisible(false);
			fondito.setVisible(true);
			fondito.setBounds((int) (wid * 0.6), (int) (hei * 0.45),
					(int) (wid * 0.35), (int) (hei * 0.3));

		buyButton.setVisible(true);
		
		nameLabel.setText("Numero de Id: " + idCarta);
		nameLabel.setForeground(Color.white);
		nameLabel.setBounds((int) (wid * 0.37), (int) (hei * 0.30),
				(int) (wid * 0.25), 30);
		add(nameLabel);
		
		fuerzanorte.setText("Fuerza Norte: " + card.getTop());
		fuerzanorte.setForeground(Color.white);
		fuerzanorte.setBounds((int) (wid * 0.37), (int) (hei * 0.35),
				(int) (wid * 0.25), 30);
		add(fuerzanorte);
		
		fuerzasur.setText("Fuerza Sur: " + card.getDown());
		fuerzasur.setForeground(Color.white);
		fuerzasur.setBounds((int) (wid * 0.37), (int) (hei * 0.40),
				(int) (wid * 0.25), 30);
		add(fuerzasur);
		
		fuerzaeste.setText("Fuerza Este: " + card.getRight());
		fuerzaeste.setForeground(Color.white);
		fuerzaeste.setBounds((int) (wid * 0.37), (int) (hei * 0.45),
				(int) (wid * 0.25), 30);
		add(fuerzaeste);
		
		fuerzaoeste.setText("Fuerza Oeste: " + card.getLeft());
		fuerzaoeste.setForeground(Color.white);
		fuerzaoeste.setBounds((int) (wid * 0.37), (int) (hei * 0.5),
				(int) (wid * 0.25), 30);
		add(fuerzaoeste);
		
		elemento.setText("Elemento: " + card.getEleStr());
		elemento.setForeground(Color.white);
		elemento.setBounds((int) (wid * 0.37), (int) (hei * 0.55),
				(int) (wid * 0.25), 30);
		add(elemento);
		
		costo.setText("Costo: " + card.getCost()+ " puntos");
		costo.setForeground(Color.white);
		costo.setBounds((int) (wid * 0.37), (int) (hei * 0.6),
				(int) (wid * 0.25), 30);
		add(costo);
		
		puntosDisponibles.setText("Tienes " + jugador.getPoints() +" puntos disponibles");
		puntosDisponibles.setForeground(Color.white);
		puntosDisponibles.setBounds((int) (wid * 0.37), (int) (hei * 0.7),
				(int) (wid * 0.45), 30);
		add(puntosDisponibles);
		
		card.setBounds((int) (wid * 0.07), (int) (hei * 0.35),
				(int) (wid * 0.25), (int) (hei * 0.55));
		add(card);
		
		repaint();
	}

}
