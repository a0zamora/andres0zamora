package magicroot.window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import constants.WebServiceFactory;

import cliente.ServiciosWeb;
import cliente.ServiciosWebService;
import cliente.CartasDisponiblesTO;
import cliente.JugadorTO;

public class OfferCardsPanel extends ImagePanel {

	private MyButton ofertaButon;
	private MyButton quitarOferta;
	private JLabel cartasDisponibles;
	private JLabel cartasOfertadas;
	private MyButton atrasBoton;
	private List<PlayCard> misCartaOfertasLista;
	private List<PlayCard> misCartasLista;
	private PageableCardsPanel misCartas;
	private PageableCardsPanel misOfertas;
	private PlayCard card;
	private ActivePlayer player;
	private int ofertaId;
	private JLabel fondito;
	private JLabel fondito2;
	
	/**
	 * Constructor de este panel
	 * @param w int 
	 * @param h int 
	 * @param aPlayer ActivePLayer
	 */
	public OfferCardsPanel(int w , int h , ActivePlayer aPlayer, JButton botonsin) {
		player = aPlayer;
		setMyBorder();
		setLayout(null);
		wid = w;
		hei = h;
		setSize(wid, hei);
		
		changeImage("CardsPNG/Fondonegro.png");
		
		//Antorchas
		fondito= new JLabel(new ImageIcon("CardsPNG/flame45.gif"));
		fondito.setBounds((int) (wid * 0.8), (int) (hei * 0.4),
				(int) (wid * 0.2), (int) (hei * 0.3));
		fondito.setBackground(Color.black);
		fondito.setOpaque(false);
		add(fondito);
		fondito2= new JLabel(new ImageIcon("CardsPNG/flame45.gif"));
		fondito2.setBounds((int) (wid * 0.001), (int) (hei * 0.4),
				(int) (wid * 0.2), (int) (hei * 0.3));
		fondito2.setBackground(Color.black);
		fondito2.setOpaque(false);
		add(fondito2);
		
		cartasDisponibles = new JLabel("Mis cartas Disponibles");
		add(cartasDisponibles);
		cartasDisponibles.setVisible(true);
		cartasDisponibles.setBackground(Color.black);
		cartasDisponibles.setBorder(null);
		cartasDisponibles.setForeground(Color.white);
		cartasDisponibles.setOpaque(false);
		cartasDisponibles.setBounds((int) (wid * 0.36), (int) (hei * 0.25),(int) (wid * 0.35),(int) (hei * 0.05));
		
		cartasOfertadas = new JLabel("Mis cartas Ofertadas");
		add(cartasOfertadas);
		cartasOfertadas.setVisible(true);
		cartasOfertadas.setBackground(Color.black);
		cartasOfertadas.setBorder(null);
		cartasOfertadas.setForeground(Color.white);
		cartasOfertadas.setOpaque(false);
		cartasOfertadas.setBounds((int) (wid * 0.36), (int) (hei * 0.65),(int) (wid * 0.35),(int) (hei * 0.05));
		
		ofertaButon = new MyButton("", 0, "buttons/ofertar1.jpg",
				"buttons/ofertar2.jpg", "buttons/ofertar3.jpg", "buttons/ofertar4.jpg");
		ofertaButon.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
					ofertarCarta();
				
			}
		});
		add(ofertaButon);
		ofertaButon.setVisible(false);
		ofertaButon.setBackground(Color.black);
		ofertaButon.setBorder(null);
		ofertaButon.setForeground(Color.white);
		
		quitarOferta =new MyButton("", 0, "buttons/quitar1.jpg",
				"buttons/quitar2.jpg", "buttons/quitar3.jpg", "buttons/quitar4.jpg");
		quitarOferta.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
					quitarCarta();
				
			}
		});
		add(quitarOferta);
		quitarOferta.setVisible(false);
		quitarOferta.setBackground(Color.black);
		quitarOferta.setBorder(null);
		quitarOferta.setForeground(Color.white);
		
		atrasBoton = new MyButton("", 0, "buttons/atras1.jpg",
				"buttons/atras2.jpg", "buttons/atras3.jpg", "buttons/atras4.jpg");
		atrasBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atrasPanel();
			}
		});
		add(atrasBoton);
		atrasBoton.setVisible(false);
		atrasBoton.setBackground(Color.black);
		atrasBoton.setBorder(null);
		atrasBoton.setForeground(Color.white);
		
		misCartasLista = new ArrayList<PlayCard>();
		misCartaOfertasLista = new ArrayList<PlayCard>();
		List<CartasDisponiblesTO> listTO;
		PlayCard card;
		CartasDisponiblesTO cardTO;
		JugadorTO jugador;
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb base = servicio.getServiciosWebPort();
		String id = "" + player.getUsername();
		jugador = base.consultarPerfil(id);
		listTO = jugador.getCartas();
		for(int i=0;i<listTO.size();i++){
			cardTO = listTO.get(i);
			card = new PlayCard(""+cardTO.getId(),"CardsPNG/Bimg/cardBackg.png",
					cardTO.getFuerzaoeste(), cardTO.getFuerzaeste(), cardTO
							.getFuerzanorte(), cardTO.getFuerzasur(), cardTO.getElemento());
			
			misCartasLista.add(card);
		}
		
		listTO = base.retornarCartasOfertadas(player.getIdPlayer());
		for(int i =0 ; i < listTO.size(); i++){
			cardTO = listTO.get(i);
			card = new PlayCard(""+cardTO.getId(),"CardsPNG/Bimg/cardBackg.png",
					cardTO.getFuerzaoeste(), cardTO.getFuerzaeste(), cardTO
							.getFuerzanorte(), cardTO.getFuerzasur(), cardTO.getElemento());
			
			misCartaOfertasLista.add(card);
		}
		
		
		
		

		misOfertas = new PageableCardsPanel((int)(wid*0.6),(int)(hei*0.30), 4, 0, 0,(int) (hei * 0.7));
		misOfertas.setList(misCartaOfertasLista);
		misOfertas.setLocation((int) (wid * 0.2), (int) (hei * 0.7));
		misOfertas.addListener(this);
		misOfertas.setSelectable(true);
		add(misOfertas);
		misOfertas.paintPage();

		misCartas = new PageableCardsPanel((int)(wid*0.6),(int)(hei*0.30), 4, 0, 0,(int) (hei * 0.01));
		misCartas.setList(misCartasLista);
		misCartas.setLocation((int) (wid * 0.2), (int) (hei * 0.01));
		misCartas.addListener(this);
		misCartas.setSelectable(true);
		add(misCartas);
		misCartas.paintPage();
		updateUI();
		repaint();
		
	}
	/**
	 * Cuando una carta se presiona pasa lo que a continuacion se pone....
	 */
	public void paintSelectedCardAndres(PlayCard cardSelected, int loc) {
		if(loc == (int) (hei * 0.7)){
			fondito2.setVisible(false);
			cartasDisponibles.setVisible(false);
			quitarOferta.setVisible(true);
			quitarOferta.setBounds((int) (wid * 0.7), (int) (hei * 0.05),
					(int) (wid * 0.15),(int) (hei * 0.1));
			quitarOferta.SetImages();
			atrasBoton.setVisible(true);
			atrasBoton.setBounds((int) (wid * 0.7), (int) (hei * 0.2),
					(int) (wid * 0.15),(int) (hei * 0.1));
			atrasBoton.SetImages();
			misCartas.setVisible(false);
			if(card !=null)
				remove(card);
			
			card= new PlayCard(cardSelected);
			ofertaId = Integer.parseInt(card.getId());
			card.setBounds((int) (wid * 0.07), (int) (hei * 0.1),
					(int) (wid * 0.25), (int) (hei * 0.55));
			add(card);
			repaint();
		}
		else{
			fondito2.setVisible(false);
			cartasOfertadas.setVisible(false);
			ofertaButon.setVisible(true);
			ofertaButon.setBounds((int) (wid * 0.7), (int) (hei * 0.7),(int) (wid * 0.15),(int) (hei * 0.1));
			ofertaButon.SetImages();
			misOfertas.setVisible(false);
			
			atrasBoton.setVisible(true);
			atrasBoton.setBounds((int) (wid * 0.7), (int) (hei * 0.85),(int) (wid * 0.15),(int) (hei * 0.1));
			atrasBoton.SetImages();
			
			if(card !=null)
				remove(card);
			
			card= new PlayCard(cardSelected);
			ofertaId = Integer.parseInt(card.getId());
			card.setBounds((int) (wid * 0.07), (int) (hei * 0.35),
					(int) (wid * 0.25), (int) (hei * 0.55));
			add(card);
			repaint();
		}
	}
	/**
	 * Para agregar una carta a la lista de cartas ofertadas
	 */
	public void ofertarCarta() {
		ServiciosWebService base = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = base.getServiciosWebPort();
		List<CartasDisponiblesTO> listTO = new ArrayList<CartasDisponiblesTO>() ;
		listTO = cliente.ofertarCartas(player.getIdPlayer(),ofertaId);
		
		if(listTO.size() == 0){
			JOptionPane.showMessageDialog(null, "No puedes ofertar esta Carta");
			atrasBoton.doClick();
		}
		else{
			JOptionPane.showMessageDialog(null, "Se completo la Oferta");
			for(int i=0 ; i<misCartasLista.size(); i++ ){
				if(ofertaId == Integer.parseInt(misCartasLista.get(i).getId())){
					misCartaOfertasLista.add(misCartasLista.remove(i));
					misCartas.setList(misCartasLista);
					misCartas.paintPage();
				}
			}
			misOfertas.setList(misCartaOfertasLista);
			misOfertas.paintPage();
			updateUI();
			repaint();
			atrasBoton.doClick();
			
		}
		
			
	}
	/**
	 * Para quitar una carta de la lista de las cartas ofertadas
	 */
	public void quitarCarta() {
		ServiciosWebService base = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = base.getServiciosWebPort();
		List<CartasDisponiblesTO> listTO = null;
		if(cliente.retirarCartaOfertada(player.getIdPlayer(),ofertaId)){
			JOptionPane.showMessageDialog(null, "Se retiro la carta satisfactoriamente");
			for(int i=0 ; i<misCartaOfertasLista.size(); i++ ){
				if(ofertaId == Integer.parseInt(misCartaOfertasLista.get(i).getId())){
					misCartasLista.add(misCartaOfertasLista.remove(i));
					
				}
			}
			misCartas.setList(misCartasLista);
			misOfertas.setList(misCartaOfertasLista);
			misOfertas.paintPage();
			misCartas.paintPage();
			updateUI();
			repaint();
			atrasBoton.doClick();
		}
		else{
			JOptionPane.showMessageDialog(null, "No se pudo retirar la carta satisfactoriamente");
		}
	
	}
	/**
	 * Para volver al panel del principio
	 */
	public void atrasPanel() {
		cartasDisponibles.setVisible(true);
		cartasOfertadas.setVisible(true);
		card.setVisible(false);
		misCartas.setVisible(true);
		misOfertas.setVisible(true);
		ofertaButon.setVisible(false);
		quitarOferta.setVisible(false);
		atrasBoton.setVisible(false);
		fondito2.setVisible(true);
	}
	
}