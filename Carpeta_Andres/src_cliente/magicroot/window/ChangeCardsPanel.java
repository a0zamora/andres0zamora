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

public class ChangeCardsPanel extends ImagePanel {
	
	private JLabel fondito;
	private JLabel fondito2;
	private PlayCard ofertCard;
	private PlayCard chageCard;
	private ImagePanel flechaDerecha;
	private ImagePanel flechaIzquierda;
	private PageableCardsPanel ofertPanelCards;
	private PageableCardsPanel chagePanelCards;
	private List<PlayCard> ofertCardList;
	private List<PlayCard> chageCardList;
	private ActivePlayer player;
	private JLabel cartasDisponibles;
	private JLabel cartasOfertadas;
	private JButton solicitud;
	private boolean arriba;
	private boolean abajo;
	
	public ChangeCardsPanel(int w , int h , ActivePlayer aPlayer) {
		arriba = false;
		abajo = false;
		player = aPlayer;
		// Init UI
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

		flechaDerecha = new ImagePanel("CardsPNG/flechaRojaDerecha.png");
		flechaDerecha.setBounds((int) (wid *0.45), (int) (hei *0.4),(int) (wid *0.1),(int) (hei *0.1));
		add(flechaDerecha);
		
		flechaIzquierda = new ImagePanel("CardsPNG/flechaAzulIzquierda.png");
		flechaIzquierda.setBounds((int) (wid *0.44), (int) (hei *0.5),(int) (wid *0.1),(int) (hei *0.1));
		add(flechaIzquierda);
		
		solicitud = new JButton("Enviar Solicitud");
		solicitud.setBorder(null);
		solicitud.setBackground(Color.black);
		solicitud.setOpaque(false);
		solicitud.setBounds((int) (wid * 0.4), (int) (hei * 0.6),(int) (wid * 0.2),(int) (hei * 0.05));
		add(solicitud);
		solicitud.setBorder(null);
		solicitud.setForeground(Color.white);
		solicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarSolicitud();
			}
		});
		solicitud.setEnabled(false);
		
		// Jlabels
		
		cartasDisponibles = new JLabel("Mis cartas Ofertadas");
		add(cartasDisponibles);
		cartasDisponibles.setVisible(true);
		cartasDisponibles.setBackground(Color.black);
		cartasDisponibles.setBorder(null);
		cartasDisponibles.setForeground(Color.white);
		cartasDisponibles.setOpaque(false);
		cartasDisponibles.setBounds((int) (wid * 0.36), (int) (hei * 0.25),(int) (wid * 0.35),(int) (hei * 0.05));
		
		cartasOfertadas = new JLabel("Cartas Ofertadas del Sistema");
		add(cartasOfertadas);
		cartasOfertadas.setVisible(true);
		cartasOfertadas.setBackground(Color.black);
		cartasOfertadas.setBorder(null);
		cartasOfertadas.setForeground(Color.white);
		cartasOfertadas.setOpaque(false);
		cartasOfertadas.setBounds((int) (wid * 0.34), (int) (hei * 0.65),(int) (wid * 0.35),(int) (hei * 0.05));
		
		// Request for ofert cars 
		ofertCardList = new ArrayList<PlayCard>();
		chageCardList = new ArrayList<PlayCard>();
		List<CartasDisponiblesTO> listTO;
		PlayCard card;
		CartasDisponiblesTO cardTO;
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb base = servicio.getServiciosWebPort();
		listTO = base.retornarCartasOfertadas(player.getIdPlayer());
		for(int i =0 ; i < listTO.size(); i++){
			cardTO = listTO.get(i);
			card = new PlayCard(""+cardTO.getId(),"CardsPNG/Bimg/cardBackg.png",
					cardTO.getFuerzaoeste(), cardTO.getFuerzaeste(), cardTO
							.getFuerzanorte(), cardTO.getFuerzasur(), cardTO.getElemento());	
			ofertCardList.add(card);
		}
		
		
		listTO = base.retornarCartasOfertadasSistemas(player.getIdPlayer());
		for(int i =0 ; i < listTO.size(); i++){
			cardTO = listTO.get(i);
			card = new PlayCard(""+cardTO.getId(),"CardsPNG/Bimg/cardBackg.png",
					cardTO.getFuerzaoeste(), cardTO.getFuerzaeste(), cardTO
							.getFuerzanorte(), cardTO.getFuerzasur(), cardTO.getElemento());
			card.setIdOferta(cardTO.getIdOferta());
			chageCardList.add(card);
		}
		
		ofertPanelCards = new PageableCardsPanel((int)(wid*0.6),(int)(hei*0.30), 4, 0, 0,(int) (hei * 0.01));
		ofertPanelCards.setList(ofertCardList);
		ofertPanelCards.setLocation((int) (wid * 0.2), (int) (hei * 0.01));
		ofertPanelCards.addListener(this);
		ofertPanelCards.setSelectable(true);
		add(ofertPanelCards);
		ofertPanelCards.paintPage();
		
		chagePanelCards = new PageableCardsPanel((int)(wid*0.6),(int)(hei*0.30), 4, 0, 0,(int) (hei * 0.7));
		chagePanelCards.setList(chageCardList);
		chagePanelCards.setLocation((int) (wid * 0.2), (int) (hei * 0.7));
		chagePanelCards.addListener(this);
		chagePanelCards.setSelectable(true);
		add(chagePanelCards);
		chagePanelCards.paintPage();
		
		updateUI();
		repaint();
		
	}
	/**
	 * Manejar los dos pageables que hay en el Panel
	 * @param PlayCard
	 * @param int
	 */
	public void paintSelectedCardAndres(PlayCard cardSelected, int loc) {

		if (loc == (int) (hei * 0.01)) {// Pageable de Arriba
			arriba = true;
			if (arriba && abajo){
				solicitud.setEnabled(true);
			}
			if (ofertCard != null)
				remove(ofertCard);

			ofertCard = new PlayCard(cardSelected);
			ofertCard.setBounds((int) (wid * 0.25), (int) (hei * 0.35),
					(int) (wid * 0.15), (int) (hei * 0.30));
			add(ofertCard);
			ofertCard.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}				
				public void mouseClicked(MouseEvent e) {
					arriba = false;
					solicitud.setEnabled(false);
					ofertCard.setVisible(false);
					repaint();
					
				}
			});
			
			repaint();
		} else {// Pageable de Abajo
			abajo = true;
			if (arriba && abajo){
				solicitud.setEnabled(true);
			}
			if (chageCard != null)
				remove(chageCard);

			chageCard = new PlayCard(cardSelected);
			chageCard.setIdOferta(cardSelected.getIdOferta());
			chageCard.setBounds((int) (wid * 0.6), (int) (hei * 0.35),
					(int) (wid * 0.15), (int) (hei * 0.30));
			add(chageCard);
			chageCard.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}				
				public void mouseClicked(MouseEvent e) {
					
					chageCard.setVisible(false);
					solicitud.setEnabled(false);
					abajo = false;
					repaint();
					
				}
			});
			repaint();
		}

	}
	
	/**
	 * Aqui los que se hace es procesar la solicitud de trueque de cartas
	 */
	public void enviarSolicitud(){
		
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = servicio.getServiciosWebPort();
		System.err.println("id Carta Ofertada: "+ chageCard.getIdOferta());
		String respuesta = cliente.mandarSolicitudTrueque(player.getIdPlayer(),
				Integer.parseInt(ofertCard.getId()), chageCard.getIdOferta());
		
		if (respuesta.equals("La carta que quieres la tienes como Ofertada")
				|| respuesta
						.equals("La carta que quieres ya la tienes y esta Disponible para usar")
				|| respuesta.equals("solicitud enviada")
				|| respuesta
						.equals("Esta carta fue retirada hace poco del sistema")
				|| respuesta
						.equals("Esta carta ya la tiene el otro jugador como disponible")
				|| respuesta
						.equals("Esta carta ya la tiene el otro jugador como ofertada")
				|| respuesta.equals("Solicitud Enviada Satisfactoriamente")
				|| respuesta.equals("La solicitud ya fue enviada")) {
			JOptionPane.showMessageDialog(null, respuesta);
		} else {
			JOptionPane.showMessageDialog(null, "TETErio el servicio web");
		}
		
		
		
	}

}
