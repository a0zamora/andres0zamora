package magicroot.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import constants.WebServiceFactory;

import cliente.ServiciosWeb;
import cliente.ServiciosWebService;
import cliente.CartasDisponiblesTO;
import cliente.JugadorTO;
import cliente.MensajeTrueque;


@SuppressWarnings("serial")
/**
 * Esta clase se encarga de mostrar el perfil del Jugador, El Buzon y los Mensajes....
 */
public class ProfilePanel extends ImagePanel {

	private ImagePanel avatarImage;
	private PlayCard betterCardImage;
	private PlayCard sucksCardImage;
	private JLabel fondito;
	private JLabel nameLabel;
	private JLabel lvlLabel;
	private JLabel betterCardLabel;
	private JLabel sucksCardLabel;
	private JLabel numCartas;
	private JLabel correo;
	private JLabel puntos;
	private MyButton buzon;
	private MyButton atras;
	private MyButton atras2;
	private List<PlayCard> list;
	private ActivePlayer player;//Todo lo que tiene que ver con el buzon de mensajes xD
	private PageableCardsPanel pcp;
	private DefaultTableModel defTableModel;
	private JTable playersTable;
	private JScrollPane playersScrollP;
	private JPopupMenu popMenu;
	private Point evtPoint;
	private JLabel label;
	private MensajeTrueque mensaje;
	private List<MensajeTrueque> listaMensajes;
	private JLabel labia;
	private int idMensaje;
	private PlayCard miCarta;
	private PlayCard cartaOferta;
	private ImagePanel flechaIzquierda;
	private ImagePanel flechaDerecha;
	private MyButton aceptar;
	private MyButton rechazar;
	private JLabel miCartaLabel;
	private JLabel cartaPropuestaLabel;

	ProfilePanel(int w, int h) {
		listaMensajes = new ArrayList<MensajeTrueque>();
		wid = w;
		hei = h;
		setSize(wid, hei);
		setLocation(0, 0);
		setMyBorder();
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		changeImage("CardsPNG/Fondonegro.png");
/////////////////////////////////////////////////////////////////////////////////////////////////////
		Object[][] tableData = {};
		String[] columnNames = { "De ", "Tipo" };
		defTableModel = new DefaultTableModel(tableData, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		playersTable = new JTable(defTableModel);
		playersTable.setRowHeight(40);
		playersTable.setBackground(Color.gray);
		playersTable.setForeground(Color.WHITE);
		playersTable.setGridColor(Color.black);
		playersScrollP = new JScrollPane(playersTable);
		playersScrollP.setBackground(Color.black);
		playersScrollP.setBounds((int) (wid * 0.1), (int) (hei * 0.12),
				(int) (wid * 0.8), (int) (hei * 0.65));
		add(playersScrollP);
		playersScrollP.getViewport().setBackground(Color.BLACK);
		setColumnsWidth();
		popMenu = new JPopupMenu();
		popMenu.setFont(getMyFont((float) (hei * 0.03)));
		AbstractAction abs = new AbstractAction("Ver Mensaje") {

			public void actionPerformed(ActionEvent arg0) {
				int row = playersTable.rowAtPoint(evtPoint);
				int col = playersTable.columnAtPoint(evtPoint);
				if (row >= 0 && col >= 0) {

					idMensaje = row;
					mostrarMensaje();
				}
			}
		};
		popMenu.add(abs);
		playersTable.add(popMenu);
		playersTable.addMouseListener(new MouseAdapter() {
			public void checkForPopMenu(MouseEvent e) {
				evtPoint = e.getPoint();
				popMenu.show(playersTable, evtPoint.x, evtPoint.y);
			}
			public void mouseClicked(MouseEvent e) {
				checkForPopMenu(e);
			}

		});

		label = new JLabel("Buzon de Mensajes");
		label.setFont(getMyFont((float) (hei * 0.03)));
		label.setForeground(Color.WHITE);
		label.setBounds((int)(wid*0.40),(int)(hei*0.04), 190, 30);
		add(label);
		label.setVisible(false);
		playersScrollP.setVisible(false);
		/////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	/**
	 * agrega el avatar correspondiente al usuario e inserta algunos labels importantes
	 * para las cartas: Mejor Carta y Peor Carta
	 */
	public void setItems() {
		avatarImage = new ImagePanel(player.getAvatar());
		avatarImage.setBounds((int) (wid * 0.07), (int) (hei * 0.07),
				(int) (wid * 0.25), (int) (hei * 0.55));
		add(avatarImage);
		
		buzon = new MyButton("", 0, "buttons/buzon1.jpg",
				"buttons/buzon2.jpg", "buttons/buzon3.jpg", "buttons/buzon4.jpg");
		buzon.setBounds((int) (wid * 0.60), (int) (hei * 0.55),
				(int) (wid * 0.15), (int) (hei * 0.085));
		buzon.SetImages();
		buzon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					esconderTodo();	
			}
		});
		add(buzon);
		
		atras =  new MyButton("", 0, "buttons/atras1.jpg",
				"buttons/atras2.jpg", "buttons/atras3.jpg", "buttons/atras4.jpg");
		atras.setBounds((int) (wid * 0.8), (int) (hei * 0.85),
				(int) (wid * 0.15), (int) (hei * 0.085));
		atras.SetImages();
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					mostrarTodo();	
			}
		});
		add(atras);
		atras.setVisible(false);
		
		atras2 = new MyButton("", 0, "buttons/atras1.jpg",
				"buttons/atras2.jpg", "buttons/atras3.jpg", "buttons/atras4.jpg");
		atras2.setBounds((int) (wid * 0.8), (int) (hei * 0.85),
				(int) (wid * 0.15), (int) (hei * 0.085));
		atras2.SetImages();
		atras2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					esconderTodo();	
			}
		});
		add(atras2);
		atras2.setVisible(false);
		
		nameLabel = new JLabel("Nombre: " + player.getUsername());
		nameLabel.setFont(getMyFont((float) (hei * 0.03)));
		nameLabel.setForeground(Color.white);
		nameLabel.setBounds((int) (wid * 0.35), (int) (hei * 0.3),
				(int) (wid * 0.25), 30);
		add(nameLabel);

		lvlLabel = new JLabel("Nivel: "+player.getLevel());
		lvlLabel.setFont(getMyFont((float) (hei * 0.03)));
		lvlLabel.setForeground(Color.white);
		lvlLabel.setBounds((int) (wid * 0.35), (int) (hei * 0.35),
				(int) (wid * 0.25), 30);
		add(lvlLabel);
		
		betterCardLabel = new JLabel("Mejor Carta");
		betterCardLabel.setFont(getMyFont((float) (hei * 0.025)));
		betterCardLabel.setForeground(Color.white);
		betterCardLabel.setBounds((int) (wid * 0.77), (int) (hei * 0.07),
				(int) (wid * 0.30), 30);
		add(betterCardLabel);

		sucksCardLabel = new JLabel("Peor Carta");
		sucksCardLabel.setFont(getMyFont((float) (hei * 0.025)));
		sucksCardLabel.setForeground(Color.white);
		sucksCardLabel.setBounds((int) (wid * 0.78), (int) (hei * 0.55),
				(int) (wid * 0.30), 30);
		add(sucksCardLabel);
		makeCardsTable();
		agregarCartas();

	}
	/**
	 * Declara el panel paginable y agrega las cartas haciendo uso de addCard
	 */
	void makeCardsTable() {

		pcp = new PageableCardsPanel((int) (wid * 0.60), (int) (hei * 0.30), 4,1,1);
		pcp.setLocation((int) (wid * 0.07), (int) (hei * 0.65));
		addCard();
		add(pcp);
		
	}

	/**
	 * agrega la lista de cartas al panel paginable
	 */
	private void addCard(){
		pcp.setList(list);
		pcp.paintPage();
	}
	
	/**
	 * Se agrega al jugador y se rellenan otros campos que tienen informacion que 
	 * tiene el jugador 
	 * @param p
	 */
	public void setPlayer(ActivePlayer p){
		list = new ArrayList<PlayCard>();
		JugadorTO jugador = new JugadorTO();
		CartasDisponiblesTO cardTO = new CartasDisponiblesTO();
		String id;
		PlayCard card;
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = servicio.getServiciosWebPort();
		jugador = cliente.consultarPerfil(p.getUsername());
		player = p;
		player.setAvatar(jugador.getNombreAvatar());
		player.setCorreo(jugador.getCorreo());
		player.setIdPlayer(jugador.getIdjugador());
		player.setUsername(jugador.getNombreusr());
		player.setPoints(jugador.getPuntos());
		player.setLevel(jugador.getNivel());
		for(int i=0;i<jugador.getCartas().size();i++){
			cardTO = jugador.getCartas().get(i);
			id=""+cardTO.getId();
			card = new PlayCard(id,"CardsPNG/Bimg/cardBackg.png",
					cardTO.getFuerzaoeste(), cardTO.getFuerzaeste(), cardTO
							.getFuerzanorte(), cardTO.getFuerzasur(), cardTO.getElemento());
			list.add(card);
			
		}
		
	}
	
	public void agregarCartas() {
		int fuerzaAUX = 0 ;
		int fuerza=list.get(0).getTop()+list.get(0).getDown()+list.get(0).getRight()+list.get(0).getLeft();
		String id = list.get(0).getId();
		for(int i = 1 ; i< list.size() ; i++){
			fuerzaAUX = list.get(i).getTop()+list.get(i).getDown()+list.get(i).getRight()+list.get(i).getLeft();
			if(fuerza > fuerzaAUX){
				fuerza = fuerzaAUX;
				sucksCardImage = new PlayCard(list.get(i));
		

			}
		}
		
		sucksCardImage.setBounds((int) (wid * 0.77), (int) (hei * 0.60),
				(int) (wid * 0.15), (int) (hei * 0.35));
		sucksCardImage.initBufferedImage();
		add(sucksCardImage);
		
		
		fuerzaAUX = 0 ;
		fuerza=list.get(0).getTop()+list.get(0).getDown()+list.get(0).getRight()+list.get(0).getLeft();
		id = list.get(0).getId();
		for(int i = 1 ; i< list.size() ; i++){
			fuerzaAUX = list.get(i).getTop()+list.get(i).getDown()+list.get(i).getRight()+list.get(i).getLeft();
			if(fuerza < fuerzaAUX){
				fuerza = fuerzaAUX;
				betterCardImage = new PlayCard(list.get(i));
			}
		}
		betterCardImage.setBounds((int) (wid * 0.77), (int) (hei * 0.12),
				(int) (wid * 0.15), (int) (hei * 0.35));
		betterCardImage.initBufferedImage();
		add(betterCardImage);
		
		numCartas = new JLabel("Tienes "+Integer.toString(list.size())+ " cartas");
		numCartas.setFont(getMyFont((float) (hei * 0.03)));
		numCartas.setForeground(Color.white);
		numCartas.setBounds((int) (wid * 0.35), (int) (hei * 0.4),
				(int) (wid * 0.25), 30);
		add(numCartas);
		
		correo = new JLabel("Correo: "+player.getCorreo());
		correo.setFont(getMyFont((float) (hei * 0.03)));
		correo.setForeground(Color.white);
		correo.setBounds((int) (wid * 0.35), (int) (hei * 0.45),
				(int) (wid * 0.50), 30);
		add(correo);
		
		puntos = new JLabel("Puntos: "+player.getPoints());
		puntos.setFont(getMyFont((float) (hei * 0.03)));
		puntos.setForeground(Color.white);
		puntos.setBounds((int) (wid * 0.35), (int) (hei * 0.5),
				(int) (wid * 0.25), 30);
		add(puntos);
		
		fondito= new JLabel(new ImageIcon("CardsPNG/flame45.gif"));
		fondito.setBounds((int) (wid * 0.35), (int) (hei * 0.01),
				(int) (wid * 0.35), (int) (hei * 0.3));
		fondito.setBackground(Color.black);
		add(fondito);
	}
	/**
	 * Con este metodo se muestra la interfaz correspondiente al buzon!!
	 */
	public void esconderTodo() {
		fondito.setVisible(false);
		puntos.setVisible(false);
		correo.setVisible(false);
		numCartas.setVisible(false);
		betterCardImage.setVisible(false);
		sucksCardImage.setVisible(false);
		nameLabel.setVisible(false);
		lvlLabel.setVisible(false);
		avatarImage.setVisible(false);
		buzon.setVisible(false);
		betterCardLabel.setVisible(false);
		sucksCardLabel.setVisible(false);
		pcp.setVisible(false);
		atras.setVisible(true);
		atras2.setVisible(false);
		playersScrollP.setVisible(true);
		label.setVisible(true);
		if(labia!=null)
			labia.setVisible(false);
		if(miCarta!=null)
			miCarta.setVisible(false);
		if(cartaOferta!=null)
			cartaOferta.setVisible(false);
		if(flechaDerecha!=null)
			flechaDerecha.setVisible(false);
		if(flechaIzquierda!=null)
			flechaIzquierda.setVisible(false);
		if(aceptar!=null)
			aceptar.setVisible(false);
		if(miCartaLabel!=null)
			miCartaLabel.setVisible(false);
		if(cartaPropuestaLabel!=null)
			cartaPropuestaLabel.setVisible(false);
		if(rechazar!=null)
			rechazar.setVisible(false);
		
		// Inicio del Servicio WEB

		ServiciosWebService base = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = base .getServiciosWebPort();

		int tam = listaMensajes.size();
		for(int i = 0 ; i < tam ; i++){
			defTableModel.removeRow(0);
		}
		
		listaMensajes = cliente.retornarMensajes(player.getIdPlayer());
	
		
		for(int i=0; i < listaMensajes.size() ; i++){
			addElementoTabla(listaMensajes.get(i).getNombreUsr(), listaMensajes.get(i).getTipo());
		}
	}
	/**
	 * Muestra la interfaz principal del perfil
	 */
	public void mostrarTodo() {
		fondito.setVisible(true);
		puntos.setVisible(true);
		correo.setVisible(true);
		numCartas.setVisible(true);
		betterCardImage.setVisible(true);
		sucksCardImage.setVisible(true);
		nameLabel.setVisible(true);
		lvlLabel.setVisible(true);
		avatarImage.setVisible(true);
		buzon.setVisible(true);
		betterCardLabel.setVisible(true);
		sucksCardLabel.setVisible(true);
		pcp.setVisible(true);
		atras.setVisible(false);
		playersScrollP.setVisible(false);
		label.setVisible(false);
	}
	/**
	 * Agrega un mensaje en la tabla
	 * @param mensaje
	 * @param tipo
	 */
	public void addElementoTabla(String mensaje, String tipo) {
		defTableModel.addRow(new String[] { mensaje , tipo });
	}
	
	/**
	 * Muestra el mensaje segun el tipo que sea, acomodando la interfaz de la forma adecuada para
	 * que se vea adecuadamente 
	 */
	public void mostrarMensaje() {
		atras.setVisible(false);
		playersScrollP.setVisible(false);
		label.setVisible(false);
		atras2.setVisible(true);
		
		if(listaMensajes.get(idMensaje).getTipo().equals("Trueque")){ //mensaje es trueque
			if(labia!=null)
				remove(labia);
			labia = new JLabel(listaMensajes.get(idMensaje).getNombreUsr()+" quiere hacer un trueque de cartas");
			labia.setFont(getMyFont((float) (hei * 0.03)));
			labia.setForeground(Color.white);
			labia.setFont(new Font("Dialog", Font.BOLD, (int) (hei*0.03)));
			labia.setBounds((int) (wid * 0.25), (int) (hei * 0.05),
					(int) (wid * 0.50), 30);
			add(labia);
			
			if(miCarta!=null)
				remove(miCarta);
			if(cartaOferta!=null)
				remove(cartaOferta);
			miCarta = new PlayCard(listaMensajes.get(idMensaje).getMiCarta());
			cartaOferta = new PlayCard(listaMensajes.get(idMensaje).getLaCarta());
			miCarta.setBounds((int) (wid * 0.25), (int) (hei * 0.35),
					(int) (wid * 0.15), (int) (hei * 0.30));
			miCarta.initBufferedImage();
			add(miCarta);
			cartaOferta.setBounds((int) (wid * 0.6), (int) (hei * 0.35),
					(int) (wid * 0.15), (int) (hei * 0.30));
			cartaOferta.initBufferedImage();
			add(cartaOferta);
			
			if(flechaDerecha!=null)
				remove(flechaDerecha);
			flechaDerecha = new ImagePanel("CardsPNG/flechaRojaIzquierda.png");
			flechaDerecha.setBounds((int) (wid *0.45), (int) (hei *0.4),(int) (wid *0.1),(int) (hei *0.1));
			add(flechaDerecha);
			
			if(flechaIzquierda!=null)
				remove(flechaIzquierda);
			flechaIzquierda = new ImagePanel("CardsPNG/flechaAzulDerecha.png");
			flechaIzquierda.setBounds((int) (wid *0.44), (int) (hei *0.5),(int) (wid *0.1),(int) (hei *0.1));
			add(flechaIzquierda);
			
			if(aceptar!=null)
				remove(aceptar);
			aceptar = new MyButton("", 0, "buttons/aceptar1.jpg",
					"buttons/aceptar2.jpg", "buttons/aceptar3.jpg", "buttons/aceptar4.jpg");
			aceptar.setBounds((int) (wid * 0.6), (int) (hei * 0.7),(int) (wid * 0.15),(int) (hei * 0.085));
			aceptar.SetImages();
			add(aceptar);
			aceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aceptarSolicitud();
				}
			});
			if(rechazar!=null)
				remove(rechazar);
			rechazar = new MyButton("", 0, "buttons/rechazar1.jpg",
					"buttons/rechazar2.jpg", "buttons/rechazar3.jpg", "buttons/rechazar4.jpg");
			rechazar.setBounds((int) (wid * 0.25), (int) (hei * 0.7),(int) (wid * 0.15),(int) (hei * 0.085));
			rechazar.SetImages();
			add(rechazar);
			rechazar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rechazarSolicitud();
					esconderTodo();
				}
			});
			add(rechazar);
			if(miCartaLabel!=null)
				remove(miCartaLabel);
			miCartaLabel = new JLabel ("Tu carta");
			miCartaLabel.setFont(getMyFont((float) (hei * 0.03)));
			miCartaLabel.setBounds((int) (wid * 0.25), (int) (hei * 0.30),
					(int) (wid * 0.15), (int) (hei * 0.05));
			miCartaLabel.setForeground(Color.white);
			miCartaLabel.setBackground(Color.black);
			add(miCartaLabel);
			
			if(cartaPropuestaLabel!=null)
				remove(cartaPropuestaLabel);
			cartaPropuestaLabel = new JLabel ("Carta Propuesta");
			cartaPropuestaLabel.setFont(getMyFont((float) (hei * 0.03)));
			cartaPropuestaLabel.setBounds((int) (wid * 0.6), (int) (hei * 0.30),
					(int) (wid * 0.20), (int) (hei * 0.05));
			cartaPropuestaLabel.setForeground(Color.white);
			cartaPropuestaLabel.setBackground(Color.black);
			add(cartaPropuestaLabel);
			
		}else if(listaMensajes.get(idMensaje).getTipo().equals("Confirmacion")){//mensaje es confirmacion
			if(labia!=null)
				remove(labia);
			labia = new JLabel(listaMensajes.get(idMensaje).getNombreUsr()+" Acepto la solicitud de trueque");
			labia.setFont(getMyFont((float) (hei * 0.03)));
			labia.setForeground(Color.white);
			labia.setFont(new Font("Dialog", Font.BOLD, (int) (hei*0.03)));
			labia.setBounds((int) (wid * 0.25), (int) (hei * 0.05),
					(int) (wid * 0.50), 30);
			add(labia);
			
			if(miCarta!=null)
				remove(miCarta);
			if(cartaOferta!=null)
				remove(cartaOferta);
			miCarta = new PlayCard(listaMensajes.get(idMensaje).getLaCarta());
			cartaOferta = new PlayCard(listaMensajes.get(idMensaje).getMiCarta());
			miCarta.setBounds((int) (wid * 0.25), (int) (hei * 0.35),
					(int) (wid * 0.15), (int) (hei * 0.30));
			miCarta.initBufferedImage();
			add(miCarta);
			cartaOferta.setBounds((int) (wid * 0.6), (int) (hei * 0.35),
					(int) (wid * 0.15), (int) (hei * 0.30));
			cartaOferta.initBufferedImage();
			add(cartaOferta);
			
			if(flechaDerecha!=null)
				remove(flechaDerecha);
			flechaDerecha = new ImagePanel("CardsPNG/flechaRojaIzquierda.png");
			flechaDerecha.setBounds((int) (wid *0.45), (int) (hei *0.4),(int) (wid *0.1),(int) (hei *0.1));
			add(flechaDerecha);
			
			if(flechaIzquierda!=null)
				remove(flechaIzquierda);
			flechaIzquierda = new ImagePanel("CardsPNG/flechaAzulDerecha.png");
			flechaIzquierda.setBounds((int) (wid *0.44), (int) (hei *0.5),(int) (wid *0.1),(int) (hei *0.1));
			add(flechaIzquierda);
			
			if(aceptar!=null)
				remove(aceptar);
			aceptar =  new MyButton("", 0, "buttons/aceptar1.jpg",
					"buttons/aceptar2.jpg", "buttons/aceptar3.jpg", "buttons/aceptar4.jpg");
			aceptar.setBounds((int) (wid * 0.425), (int) (hei * 0.7),(int) (wid * 0.15),(int) (hei * 0.085));
			aceptar.SetImages();
			add(aceptar);
			aceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borrarMensaje();
					esconderTodo();
				}
			});
			
			if(miCartaLabel!=null)
				remove(miCartaLabel);
			miCartaLabel = new JLabel ("Tu carta");
			miCartaLabel.setFont(getMyFont((float) (hei * 0.03)));
			miCartaLabel.setBounds((int) (wid * 0.25), (int) (hei * 0.30),
					(int) (wid * 0.15), (int) (hei * 0.05));
			miCartaLabel.setForeground(Color.white);
			miCartaLabel.setBackground(Color.black);
			add(miCartaLabel);
			
			if(cartaPropuestaLabel!=null)
				remove(cartaPropuestaLabel);
			cartaPropuestaLabel = new JLabel ("Carta Propuesta");
			cartaPropuestaLabel.setFont(getMyFont((float) (hei * 0.03)));
			cartaPropuestaLabel.setBounds((int) (wid * 0.6), (int) (hei * 0.30),
					(int) (wid * 0.20), (int) (hei * 0.05));
			cartaPropuestaLabel.setForeground(Color.white);
			cartaPropuestaLabel.setBackground(Color.black);
			add(cartaPropuestaLabel);
		}else{
			
		}
		repaint();
		updateUI();
	}
	/**
	 * para insertar el wid de la columna de la tabla
	 */
	public void setColumnsWidth() {

		TableColumn column = null;
		column = playersTable.getColumnModel().getColumn(0);
		column.setPreferredWidth(110);
		column = playersTable.getColumnModel().getColumn(1);
		column.setPreferredWidth(20);

	}
	/**
	 * Aqui se procesa el servicio web que acepta la solicitud de trueque
	 */
	public void aceptarSolicitud() {
		String mensaje;
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = servicio.getServiciosWebPort();
		mensaje = cliente.comercioJugadores(player.getIdPlayer(), listaMensajes
				.get(idMensaje).getNombreUsr(), listaMensajes.get(idMensaje)
				.getMiCarta().getId(), listaMensajes.get(idMensaje)
				.getLaCarta().getId());
		System.out.println("Remitente"+ " " +listaMensajes.get(idMensaje).getNombreUsr());
		System.out.println("Ofertada" +" "+listaMensajes.get(idMensaje).getMiCarta().getId());
		System.out.println("Propuesta" +" "+ listaMensajes.get(idMensaje).getLaCarta().getId());
		if (mensaje.equals("Ya tienes la Carta Propuesta")
				|| mensaje.equals("El remitente ya tiene esta Carta")
				|| mensaje.equals("La carta ya fue retirada del sistema")
				|| mensaje.equals("No se completo la transaccion")
				|| mensaje.equals("Transaccion Completa")) {
			JOptionPane.showMessageDialog(null, mensaje);
		}

	}
	/**
	 * Este metodo sirve para procesar y borrar el mensaje de confirmacion de
	 * el trueque
	 */
	public void borrarMensaje() {
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = servicio.getServiciosWebPort();
		if(cliente.eliminarMensajeConfirmacion(listaMensajes.get(idMensaje).getIdRespuesta())){
		}
		else{
		}
		
	}
	
	/**
	 * Este metodo sirve para rechazar una oferta y usa un servicio web para eliminar la 
	 * oferta hecha por el remitente del mensaje
	 */
	public void rechazarSolicitud() {
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = servicio.getServiciosWebPort();
		if(cliente.eliminarRespuesta(listaMensajes.get(idMensaje).getIdRespuesta())){
			JOptionPane.showMessageDialog(null, "Se Rechazo la Solicitud Satisfactoriamente");
		}else{
			JOptionPane.showMessageDialog(null, "Esta Solicitud Caduco hace Poco");
		}
	}
	/**
	 * Introducir el otro usuario.
	 */
	public ProfilePanel(int w, int h, String name) {

		wid = w;
		hei = h;
		setSize(wid, hei);
		setLocation(0, 0);
		setMyBorder();
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		changeImage("CardsPNG/Fondonegro.png");
		setPlayer(name);
		buzon.setVisible(false);
		
	}
	/**
	 * Introducir Informacion para el otro Usuario
	 */
	public void setPlayer(String name) {
		list = new ArrayList<PlayCard>();
		JugadorTO jugador = new JugadorTO();
		CartasDisponiblesTO cardTO = new CartasDisponiblesTO();
		PlayCard card;
		ServiciosWebService servicio = WebServiceFactory.createBasesDatosService();
		ServiciosWeb cliente = servicio.getServiciosWebPort();
		jugador = cliente.consultarPerfil(name);
		player = new ActivePlayer(jugador);
		
		for(int i=0;i<jugador.getCartas().size();i++){
			cardTO = jugador.getCartas().get(i);
			String id=""+cardTO.getId();
			card = new PlayCard(id,"CardsPNG/Bimg/cardBackg.png",
					cardTO.getFuerzaoeste(), cardTO.getFuerzaeste(), cardTO
							.getFuerzanorte(), cardTO.getFuerzasur(), cardTO.getElemento());
			list.add(card);
			
		}
		setItems();
	}
	
	/////////////////////////////////////////////////////////////////////////
}