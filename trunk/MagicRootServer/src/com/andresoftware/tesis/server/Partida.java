package com.andresoftware.tesis.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.andresoftware.tesis.model.CartaBean;
import com.andresoftware.tesis.model.JugadorBean;
import com.andresoftware.tesis.model.PartidasJugadas;
import com.andresoftware.tesis.model.SessionHibernate;

import commands.AzulJugarCommand;
import commands.CambioTableroCommand;
import commands.CartaInvalidaCommand;
import commands.Command;
import commands.CrearCommand;
import commands.EleguirTableroCommand;
import commands.GameOver;
import commands.JugDesconectadoRoomCommand;
import commands.JugListoCommand;
import commands.JugarCommand;
import commands.RevanchaAzulCommand;
import commands.RevanchaRojoCommand;
import commands.RojoJugarCommand;
import commands.UnirCommand;

import constants.TipoColor;
import constants.TipoDeEvento;

/**
 * Clase que contiene los dos hilos de clientes para poder asi comunicarse con
 * cada cliente...
 * 
 * @author Andres & Rafael
 * 
 */
public class Partida {

	private int id; // El Id de la partida

	private ClientThread hiloAzul, hiloRojo; // Los dos hilos de clientes

	private int idHiloEnTurno, idHiloEnEspera;

	private boolean jugando; // para saber si se esta jugando

	private String nombre; // Nombre de la partida

	private boolean estado; // Estado para saber si se esta jugando con esta
							// partida

	private boolean consultarCartasAzul = false, consultarCartasRojo = false;

	private boolean revanchaAzul = false, revanchaRojo = false;

	private Tablero tablero;

	public Map<String, Object> ctx;

	public Map<String, Object> par;

	private MaquinaEstados maquina;

	private List<Card> consultaCartasAzules;

	private List<Card> consultaCartasRojas;

	private List<Card> listaCartasAzules;

	private List<Card> listaCartasRojas;

	private Command idCartasAzules;

	private Command idCartasRojas;

	// *******************************************************************************//
	public Partida() {
		/* Empty */
	}

	// *******************************************************************************//
	public Partida(ClientThread hiloAzul) {
		consultaCartasAzules = new ArrayList<Card>();
		consultaCartasRojas = new ArrayList<Card>();
		this.hiloAzul = hiloAzul;
		id = hiloAzul.getId();
		estado = false;
		tablero = new Tablero(this);
		ctx = new HashMap<String, Object>();
		ctx.put("tablero", tablero);
		par = new HashMap<String, Object>();
		maquina = new MaquinaEstados(this);
		this.hiloAzul.setCreadorDePartida(true);
		hiloRojo = new ClientThread();
	}

	// *********************************************************************//
	// *** INTERFAZ ***//
	// *********************************************************************//

	public void Revancha(ClientThread hiloAzul ,ClientThread hiloRojo){
		consultaCartasAzules = new ArrayList<Card>();
		consultaCartasRojas = new ArrayList<Card>();
		this.hiloAzul = hiloAzul;
		id = hiloAzul.getId();
		estado = false;
		tablero = new Tablero(this);
		ctx = new HashMap<String, Object>();
		ctx.put("tablero", tablero);
		par = new HashMap<String, Object>();
		//maquina = new MaquinaEstados(this);
		this.hiloAzul.setCreadorDePartida(true);
		this.hiloRojo = hiloRojo;
		
	}
	
	public int getIdHiloAzul() {
		return hiloAzul.getId();
	}

	// *******************************************************************************//
	public int getIdHiloRojo() {
		return hiloRojo.getId();
	}

	// *******************************************************************************//
	public int getIdHiloEnTurno() {
		return idHiloEnTurno;
	}

	// *******************************************************************************//
	public void setIdHiloEnTurno(int idHiloEnTurno) {
		this.idHiloEnTurno = idHiloEnTurno;
	}

	// *******************************************************************************//
	public int getIdHiloEnEspera() {
		return idHiloEnEspera;
	}

	// *******************************************************************************//
	public void setIdHiloEnEspera(int idHiloEnEspera) {
		this.idHiloEnEspera = idHiloEnEspera;
	}

	// *******************************************************************************//
	public void switchTurn() {
		if (idHiloEnTurno == getIdHiloAzul()) {
			setIdHiloEnTurno(getIdHiloRojo());
			setIdHiloEnEspera(getIdHiloAzul());
		} else {
			setIdHiloEnTurno(getIdHiloAzul());
			setIdHiloEnEspera(getIdHiloRojo());
		}
	}

	// *******************************************************************************//
	public void crearPartida() {
	}

	// *******************************************************************************//
	public void setId(int id) {
		this.id = id;
	}

	// *******************************************************************************//
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	// *******************************************************************************//
	public int getId() {
		return id;
	}

	// *******************************************************************************//
	public void setHiloRojo(ClientThread hiloRojo) {
		this.hiloRojo = hiloRojo;
	}

	// *******************************************************************************//
	public ClientThread getHiloRojo() {
		return hiloRojo;
	}

	// *******************************************************************************//
	public void setEstadoHiloAzul(boolean estado) {
		hiloAzul.setEstado(estado);
	}

	// *******************************************************************************//
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	// *******************************************************************************//
	public boolean getEstado() {
		return estado;
	}

	// *******************************************************************************//
	private void enviar(Command command, int id) {
		if (id == hiloAzul.getId()) {
			hiloAzul.enviarMensaje(command.convertirAString());
		}
		if (id == hiloRojo.getId()) {
			hiloRojo.enviarMensaje(command.convertirAString());
		}
	}

	// *******************************************************************************//
	public void enviar(String str, int id) {
		if (id == hiloAzul.getId()) {
			hiloAzul.enviarMensaje(str);
		}
		if (id == hiloRojo.getId()) {
			hiloRojo.enviarMensaje(str);
		}
	}

	public void enviar(Command comando) {
		int id = comando.getId();
		enviar(comando.convertirAString(), id);

	}

	// *******************************************************************************//
	public void enviarMsjAmbosJug(Command comando) {

		String str = comando.convertirAString();

		hiloRojo.enviarMensaje(str);
		hiloAzul.enviarMensaje(str);

	}

	public void enviarMsjAmbosJug(String str) {

		hiloAzul.enviarMensaje(str);
		hiloRojo.enviarMensaje(str);
	}

	// *******************************************************************************//
	public void enviarMsjJugTurno(Command comando) {
		enviar(comando, idHiloEnTurno);

	}

	// *******************************************************************************//
	/***
	 * 
	 * metodo privado para enviar cosas sencillas a cliente solo sera usado a
	 * modo de prueba se removera luego
	 * 
	 */
	// *******************************************************************************//
	public void enviarMsjJugEspera(Command comando) {
		enviar(comando, idHiloEnEspera);
	}

	// *******************************************************************************//
	public void enviarMensajeRoom(String texto, int id) {
		hiloAzul.enviarMensaje(texto);
		hiloRojo.enviarMensaje(texto);
	}

	// *******************************************************************************//
	public void enviarMensajeMaquina(Command comando) {
		String texto = comando.convertirAString();

		if (texto.startsWith(CrearCommand.CADENA_COMANDO)
				|| texto.startsWith(UnirCommand.CADENA_COMANDO)) {
			initCrearOUnirOCambioTableroInterno(comando);

		} else if (texto.startsWith(JugDesconectadoRoomCommand.CADENA_COMANDO)) {

			initDesconexion(comando);

		} else if (texto.startsWith(JugListoCommand.CADENA_COMANDO)) {

			initListo(comando);

		} else if (texto.startsWith(JugarCommand.CADENA_COMANDO)) {
			initJugada(comando, texto);

		} else if (texto.startsWith(GameOver.CADENA_COMANDO)) {
			initPayerLeave(comando);

		} else if (texto.startsWith(EleguirTableroCommand.CADENA_COMANDO)) {
			initCambioTablero(comando);
		} else if (texto.startsWith(RevanchaRojoCommand.CADENA_COMANDO)) {
			initRevanchaRojo(comando);
		} else if (texto.startsWith(RevanchaAzulCommand.CADENA_COMANDO)) {
			initRevanchaAzul(comando);

		} else {

			enviar("Wrong commad", getIdHiloAzul());
			enviar("Wrong commad", getIdHiloRojo());

		}
	}

	private void initRevanchaAzul(Command comando) {
		RevanchaAzulCommand revanchaAzulComand = (RevanchaAzulCommand) comando;
		boolean boolRevanchaAzul = revanchaAzulComand.getRevancha();
		TipoDeEvento evento;
		if (boolRevanchaAzul) {
			evento = TipoDeEvento.revanchaAzulOk;
		} else {
			evento = TipoDeEvento.NoRevancha;
		}

		try {
			maquina.disparaEvento(evento, null, null, comando.getId());
		} catch (UnknowEventForState e) {
			e.printStackTrace();
		} catch (IdCreadorInvalido e) {
			e.printStackTrace();
		}

	}

	private void initRevanchaRojo(Command comando) {
		RevanchaRojoCommand revanchaRojoComand = (RevanchaRojoCommand) comando;
		boolean boolRevanchaRojo = revanchaRojoComand.getRevancha();
		TipoDeEvento evento;
		if (boolRevanchaRojo) {
			evento = TipoDeEvento.revanchaRojoOk;
		} else {
			evento = TipoDeEvento.NoRevancha;
		}

		try {
			maquina.disparaEvento(evento, null, null, comando.getId());
		} catch (UnknowEventForState e) {
			e.printStackTrace();
		} catch (IdCreadorInvalido e) {
			e.printStackTrace();
		}

	}

	private void initCambioTablero(Command comando) {
		Command cambioCommand;
		EleguirTableroCommand comandoTablero = (EleguirTableroCommand) comando;
		tablero.setCambioTablero(comandoTablero.getElemento());
		cambioCommand = new CambioTableroCommand(tablero.getElemento(), false);
		enviarMsjAmbosJug(cambioCommand);
		try {
			maquina.disparaEvento(comando.getTipoDeEvento(), null, null,
					comando.getId());
		} catch (UnknowEventForState e) {
			e.printStackTrace();
		} catch (IdCreadorInvalido e) {
			e.printStackTrace();
		}
		tablero.actualizarTablero();
		tablero.enviarActualizacoinTablero();

	}

	// *******************************************************************************//
	private void initCrearOUnirOCambioTableroInterno(Command comando) {

		try {
			maquina.disparaEvento(comando.getTipoDeEvento(), null, null,
					comando.getId());
		} catch (UnknowEventForState e) {
			e.printStackTrace();
		} catch (IdCreadorInvalido e) {
			e.printStackTrace();
		}
	}

	// *******************************************************************************//
	private void initPayerLeave(Command comando) {

		int id = comando.getId();

		if (id == getIdHiloAzul()) {
			try {
				maquina.disparaEvento(TipoDeEvento.creadorDesconectado, null,
						null, comando.getId());
				enviarHiloRojo(comando);
			} catch (UnknowEventForState e) {
				e.printStackTrace();
			} catch (IdCreadorInvalido e) {
				e.printStackTrace();
			}

		} else if (id == getIdHiloRojo()) {

			try {
				maquina.disparaEvento(TipoDeEvento.invitadoDesconectado, null,
						null, comando.getId());
				enviarHiloAzul(comando);
			} catch (UnknowEventForState e) {
				e.printStackTrace();
			} catch (IdCreadorInvalido e) {
				e.printStackTrace();
			}

		}

	}

	// *******************************************************************************//
	private void initDesconexion(Command comando) {

		if (comando.getId() == getIdHiloRojo()) {

			try {
				maquina.disparaEvento(TipoDeEvento.invitadoDesconectado, null,
						null, comando.getId());
			} catch (UnknowEventForState e) {
				e.printStackTrace();
			} catch (IdCreadorInvalido e) {
				e.printStackTrace();
			}

		} else if (comando.getId() == getIdHiloAzul()) {

			try {
				maquina.disparaEvento(TipoDeEvento.creadorDesconectado, null,
						null, comando.getId());
			} catch (UnknowEventForState e) {
				e.printStackTrace();
			} catch (IdCreadorInvalido e) {
				e.printStackTrace();
			}

		} else {
			enviar("#1Wrong ID", getIdHiloAzul());
			enviar("#1Wrong ID", getIdHiloRojo());
		}

	}

	// *******************************************************************************//
	private void initListo(Command comando) {

		if (comando.getId() == getIdHiloRojo()) {

			try {
				maquina.disparaEvento(TipoDeEvento.InvitadoRdy, null, null,
						comando.getId());
			} catch (UnknowEventForState e) {
				e.printStackTrace();
			} catch (IdCreadorInvalido e) {
				e.printStackTrace();
			}

		} else if (comando.getId() == getIdHiloAzul()) {

			try {
				maquina.disparaEvento(TipoDeEvento.creadorRdy, null, null,
						comando.getId());
			} catch (UnknowEventForState e) {
				e.printStackTrace();
			} catch (IdCreadorInvalido e) {
				e.printStackTrace();
			}

		} else {
			enviar("#2Wrong ID ", getIdHiloAzul());
			enviar("#2Wrong ID", getIdHiloRojo());
		}

	}

	// *******************************************************************************//
	private void initJugada(Command comando, String texto) {

		System.out.println("IDHilo hilo en turno" + getIdHiloEnTurno());
		System.out.println("ID Hilo  azul" + getIdHiloAzul());
		System.out.println("ID Hilo rojo" + getIdHiloRojo());

		if (getIdHiloEnTurno() == comando.getId()
				&& getIdHiloEnTurno() == getIdHiloAzul()) {

			System.out
					.println("--------------------TURNO AZUL--------------------");
			AzulJugarCommand jugAzulCommando = new AzulJugarCommand(texto);
			Card carta = new Card();
			//try {
				carta = encontrarIdCartaAzul(jugAzulCommando.getIdCarta());
				carta.setColor(TipoColor.AZUL);

				par.put("carta", carta);
				par.put("row", jugAzulCommando.getX());
				par.put("colum", jugAzulCommando.getY());

				try {
					maquina.disparaEvento(jugAzulCommando.getTipoDeEvento(),
							ctx, par, jugAzulCommando.getId());
				} catch (UnknowEventForState e) {
					e.printStackTrace();
				} catch (IdCreadorInvalido e) {
					enviarHiloAzul("ID invalido.....");
				}

//			} catch (NullPointerException e) {
//				CartaInvalidaCommand cartaInvComando = new CartaInvalidaCommand(
//						jugAzulCommando.getIdCarta(), jugAzulCommando.getX(),
//						jugAzulCommando.getY());
//				enviarHiloAzul(cartaInvComando);
//				System.out.println(e);
//			}

		} else if (getIdHiloEnTurno() == comando.getId()
				&& getIdHiloEnTurno() == getIdHiloRojo()) {
			System.out
					.println("--------------------TURNO ROJO--------------------");

			RojoJugarCommand jugRojoCommando = new RojoJugarCommand(texto);
			Card carta = new Card();
			try {
				carta = encontrarIdCartaRojas(jugRojoCommando.getIdCarta());
				carta.setColor(TipoColor.ROJA);
				par.put("carta", carta);
				par.put("row", jugRojoCommando.getX());
				par.put("colum", jugRojoCommando.getY());

				try {
					maquina.disparaEvento(jugRojoCommando.getTipoDeEvento(),
							ctx, par, jugRojoCommando.getId());
				} catch (UnknowEventForState e) {
					e.printStackTrace();
				} catch (IdCreadorInvalido e) {
					enviarHiloRojo("Id invalido .......");
				}

			} catch (NullPointerException e) {
				CartaInvalidaCommand cartaInvComando = new CartaInvalidaCommand(
						jugRojoCommando.getIdCarta(), jugRojoCommando.getX(),
						jugRojoCommando.getY());
				enviarHiloRojo(cartaInvComando);
				System.out.println(e);
			}

		} else {

			enviar("Wrong ID or Color", getIdHiloAzul());
			enviar("Wrong ID or Color", getIdHiloRojo());
		}

	}

	// *******************************************************************************//
	private Card encontrarIdCartaAzul(int idCarta) {

		Card carta = new Card();
		for (int k = 0; k < listaCartasAzules.size(); k++) {
			carta = listaCartasAzules.get(k);

			if (carta.getIdCarta() == idCarta) {
				return listaCartasAzules.remove(k);
			}

		}
		return null;
	}

	// *******************************************************************************//
	private Card encontrarIdCartaRojas(int idCarta) {

		Card carta = new Card();
		for (int k = 0; k < listaCartasRojas.size(); k++) {
			carta = listaCartasRojas.get(k);
			if (carta.getIdCarta() == idCarta) {
				return listaCartasRojas.remove(k);
			}

		}
		return null;
	}

	// *******************************************************************************//
	private void imprimirListasDeIdRojas() {
		Card carta = new Card();
		for (int k = 0; k < listaCartasRojas.size(); k++) {
			carta = listaCartasRojas.get(k);

		}
	}

	// *******************************************************************************//
	private void imprimirListasDeIdAzules() {
		Card carta = new Card();
		for (int k = 0; k < listaCartasAzules.size(); k++) {
			carta = listaCartasAzules.get(k);
		}
	}

	// *******************************************************************************//
	public void enviarJugada(String str) {
	}

	// *******************************************************************************//
	public void desconectarInvitadoRoom() {
		estado = false;
		hiloRojo.setEstado(false);
		hiloAzul.setEstado(false);
		hiloAzul.sendToAllUsers("crear " + Integer.toString(hiloAzul.getId())
				+ " " + nombre);
		hiloAzul.enviarMensaje("Se_desconecto_Invitado");
	}

	// *******************************************************************************//
	public void desconectarCreadorRoom() {
		hiloRojo.setEstado(false);
		hiloAzul.desconectarCreador();
		hiloRojo.enviarMensaje("Se_desconecto_Creador");
	}

	// *******************************************************************************//
	public void enviarHiloAzul(String texto) {
		hiloAzul.enviarMensaje(texto);
	}

	public void enviarHiloAzul(Command comando) {
		String texto = comando.convertirAString();
		hiloAzul.enviarMensaje(texto);
	}

	// *******************************************************************************//
	public void enviarHiloRojo(String texto) {
		hiloRojo.enviarMensaje(texto);
	}

	// *******************************************************************************//
	public void enviarHiloRojo(Command comando) {
		String texto = comando.convertirAString();
		hiloRojo.enviarMensaje(texto);
	}

	// *******************************************************************************//
	public void procesarDesconexionJugandoCreador() {
		hiloRojo.enviarMensaje("Has_Ganado");
		hiloAzul.enviarMensaje("Has_Perdido");
		hiloRojo.setEstado(false);
		hiloAzul.desconectarCreador();
	}

	// *******************************************************************************//
	public void procesarDesconexionJugadorInvitado() {
		hiloAzul.enviarMensaje("Has_Ganado");
		hiloRojo.enviarMensaje("Has_Perdido");
		hiloRojo.setEstado(false);
		hiloAzul.desconectarCreador();
	}

	// *******************************************************************************//
	public Card consultarCarta(int idCarta, int idJugador) {
		if ((idJugador == hiloAzul.getId() && !consultarCartasAzul)
				|| (idJugador == hiloRojo.getId()) && !consultarCartasRojo) {

			Card aux = new Card();
			Session session = SessionHibernate.getInstance().getSession();
			session.beginTransaction();
			JugadorBean jugador = new JugadorBean();
			jugador = (JugadorBean) session.load(JugadorBean.class, idJugador);
			CartaBean carta = new CartaBean();
			int elementico = 0;
			for (int i = 0; i < jugador.getCartasDisponiblesRef().size(); i++) {
				carta = jugador.getCartasDisponiblesRef().get(i).getCartaRef();
				if (jugador.getCartasDisponiblesRef().get(i).getCartaRef()
						.getId() == idCarta) {
					if (carta.getElemento().equals("agua")) {
						elementico = 0;
					} else if (carta.getElemento().equals("fuego")) {
						elementico = 1;
					} else if (carta.getElemento().equals("aire")) {
						elementico = 2;
					} else if (carta.getElemento().equals("tierra")) {
						elementico = 3;
					}
					aux = new Card(carta.getId(), carta.getFuerzanorte(),
							carta.getFuerzasur(), carta.getFuerzaeste(),
							carta.getFuerzaoeste(), elementico);
					if (idJugador == hiloAzul.getId()) {
						consultaCartasAzules.add(aux);
					} else {
						consultaCartasRojas.add(aux);
					}

				} else {
					if (carta.getElemento().equals("agua")) {
						elementico = 0;
					} else if (carta.getElemento().equals("fuego")) {
						elementico = 1;
					} else if (carta.getElemento().equals("aire")) {
						elementico = 2;
					} else if (carta.getElemento().equals("tierra")) {
						elementico = 3;
					}

					Card cartaX = new Card(carta.getId(),
							carta.getFuerzanorte(), carta.getFuerzasur(),
							carta.getFuerzaeste(), carta.getFuerzaoeste(),
							elementico);
					if (idJugador == hiloAzul.getId()) {
						consultaCartasAzules.add(cartaX);
					} else {
						consultaCartasRojas.add(cartaX);
					}

				}
			}
			if (idJugador == hiloAzul.getId()) {
				consultarCartasAzul = true;
			} else {
				consultarCartasRojo = true;
			}
			session.getTransaction().commit();
			session.close();
			return aux;
		} else if (idJugador == hiloAzul.getId() && consultarCartasAzul) {
			for (int i = 0; i < consultaCartasAzules.size(); i++) {
				if (consultaCartasAzules.get(i).getIdCarta() == idCarta) {
					return consultaCartasAzules.get(i);
				}
			}
		} else if (idJugador == hiloRojo.getId() && consultarCartasRojo) {
			for (int i = 0; i < consultaCartasRojas.size(); i++) {
				if (consultaCartasRojas.get(i).getIdCarta() == idCarta) {
					return consultaCartasRojas.get(i);
				}
			}
		}

		return null;
		// CapturarHackeador;

	}

	// *******************************************************************************//
	public void guardarPartida(int idGanador, int idPerdedor,
			int puntosGanador, int puntosPerdedor) {

		PartidasJugadas guardaPartida = new PartidasJugadas(); // lo que voy a
		// guarda
		JugadorBean jugadorGanador = new JugadorBean();// jugador ganador
		JugadorBean jugadorPerdedor = new JugadorBean();// jugador perdedor

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		// haciendo la consulta para retornar a los dos jugadores
		jugadorGanador = (JugadorBean) session.load(JugadorBean.class,
				idGanador);
		jugadorPerdedor = (JugadorBean) session.load(JugadorBean.class,
				idPerdedor);
		jugadorGanador.setPuntosGanador();
		guardaPartida.setPuntosGanador(puntosGanador);
		guardaPartida.setPuntosPerdedor(puntosPerdedor);
		guardaPartida.setJugadorGanador(jugadorGanador);
		guardaPartida.setJugadorPerdedor(jugadorPerdedor);
		session.save(guardaPartida);
		session.getTransaction().commit();
		session.close();

	}

	public void guardarPartidaEmpatada(int idJug1, int idJug2, int puntosJug1,
			int puntosJug2) {
		PartidasJugadas partidaJugada = new PartidasJugadas();// la partida que
																// voy a guardar

		JugadorBean jugador1 = new JugadorBean();
		JugadorBean jugador2 = new JugadorBean();

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		// consulta de los jugadores
		jugador1 = (JugadorBean) session.load(JugadorBean.class, idJug1);
		jugador2 = (JugadorBean) session.load(JugadorBean.class, idJug2);
		jugador1.setPuntosEmpate();
		jugador2.setPuntosEmpate();
		partidaJugada.setJugadorEmpate1(jugador1);
		partidaJugada.setJugadorEmpate2(jugador2);
		// guardarn los puntos de los jugadores
		session.save(partidaJugada);
		session.getTransaction().commit();
		session.close();
	}

	// *******************************************************************************//
	public boolean llenarListaCartas(Command comando) {
		JugListoCommand comando2 = (JugListoCommand) comando;

		if (comando.getId() == hiloAzul.getId()) {
			listaCartasAzules = new ArrayList<Card>();

			if (consultarCarta(comando2.getIdCarta1(), comando2.getId()) != null) {
				listaCartasAzules.add(consultarCarta(comando2.getIdCarta1(),
						comando2.getId()));
			} else {
				return false;
			}
			if (consultarCarta(comando2.getIdCarta2(), comando2.getId()) != null) {
				listaCartasAzules.add(consultarCarta(comando2.getIdCarta2(),
						comando2.getId()));
			} else {
				return false;
			}
			if (consultarCarta(comando2.getIdCarta3(), comando2.getId()) != null) {
				listaCartasAzules.add(consultarCarta(comando2.getIdCarta3(),
						comando2.getId()));
			} else {
				return false;
			}

			if (consultarCarta(comando2.getIdCarta4(), comando2.getId()) != null) {
				listaCartasAzules.add(consultarCarta(comando2.getIdCarta4(),
						comando2.getId()));
			} else {
				return false;
			}
			if (consultarCarta(comando2.getIdCarta5(), comando2.getId()) != null) {
				listaCartasAzules.add(consultarCarta(comando2.getIdCarta5(),
						comando2.getId()));
			} else {
				return false;
			}
			if (consultarCarta(comando2.getIdCarta6(), comando2.getId()) != null) {
				listaCartasAzules.add(consultarCarta(comando2.getIdCarta6(),
						comando2.getId()));
			} else {
				return false;
			}
			if (consultarCarta(comando2.getIdCarta7(), comando2.getId()) != null) {
				listaCartasAzules.add(consultarCarta(comando2.getIdCarta7(),
						comando2.getId()));
			} else {
				return false;
			}
			if (consultarCarta(comando2.getIdCarta8(), comando2.getId()) != null) {
				listaCartasAzules.add(consultarCarta(comando2.getIdCarta8(),
						comando2.getId()));
			} else {
				return false;
			}

			this.setIdCartasAzules(comando);
			return true;

		} else if (comando.getId() == hiloRojo.getId()) {
			listaCartasRojas = new ArrayList<Card>();
			if (consultarCarta(comando2.getIdCarta1(), comando2.getId()) != null) {
				listaCartasRojas.add(consultarCarta(comando2.getIdCarta1(),
						comando2.getId()));
			} else {
				return false;
			}

			if (consultarCarta(comando2.getIdCarta2(), comando2.getId()) != null) {
				listaCartasRojas.add(consultarCarta(comando2.getIdCarta2(),
						comando2.getId()));
			} else {
				return false;
			}

			if (consultarCarta(comando2.getIdCarta3(), comando2.getId()) != null) {
				listaCartasRojas.add(consultarCarta(comando2.getIdCarta3(),
						comando2.getId()));
			} else {
				return false;
			}

			if (consultarCarta(comando2.getIdCarta4(), comando2.getId()) != null) {
				listaCartasRojas.add(consultarCarta(comando2.getIdCarta4(),
						comando2.getId()));
			} else {
				return false;
			}

			if (consultarCarta(comando2.getIdCarta5(), comando2.getId()) != null) {
				listaCartasRojas.add(consultarCarta(comando2.getIdCarta5(),
						comando2.getId()));
			} else {
				return false;
			}

			if (consultarCarta(comando2.getIdCarta6(), comando2.getId()) != null) {
				listaCartasRojas.add(consultarCarta(comando2.getIdCarta6(),
						comando2.getId()));

			} else {
				return false;
			}
			if (consultarCarta(comando2.getIdCarta7(), comando2.getId()) != null) {
				listaCartasRojas.add(consultarCarta(comando2.getIdCarta7(),
						comando2.getId()));
			} else {
				return false;
			}

			if (consultarCarta(comando2.getIdCarta8(), comando2.getId()) != null) {
				listaCartasRojas.add(consultarCarta(comando2.getIdCarta8(),
						comando2.getId()));
			} else {
				return false;
			}
			this.setIdCartasRojas(comando);
			return true;

		} else {
			enviar(" ID invalido para los id las cartas", comando.getId());

		}
		return false;
	}

	// *******************************************************************************//
	public void setIdCartasAzules(Command idCartasAzules) {
		this.idCartasAzules = idCartasAzules;
	}

	// *******************************************************************************//
	public Command getIdCartasAzules() {
		return idCartasAzules;
	}

	// *******************************************************************************//
	public void setIdCartasRojas(Command idCartasRojas) {
		this.idCartasRojas = idCartasRojas;
	}

	// *******************************************************************************//
	public Command getIdCartasRojas() {
		return idCartasRojas;
	}

	// *******************************************************************************//
	public void procesarFinDeJuego() {
		hiloRojo.setEstado(false);
		hiloRojo.anularPartida();
		hiloAzul.desconectarCreador();
	}

	// *******************************************************************************//
	public boolean isConsultarCartasAzul() {
		return consultarCartasAzul;
	}

	// *******************************************************************************//
//	public void setConsultarCartasAzul(boolean consultarCartasAzul) {
//		this.consultarCartasAzul = consultarCartasAzul;
//	}

	// *******************************************************************************//
	public boolean isConsultarCartasRojo() {
		return consultarCartasRojo;
	}

	// *******************************************************************************//
//	public void setConsultarCartasRojo(boolean consultarCartasRojo) {
//		consultaCartasAzules = new ArrayList<Card>();
//		consultaCartasRojas = new ArrayList<Card>();
//		this.consultarCartasAzul = consultarCartasRojo;
//		this.consultarCartasRojo = consultarCartasRojo;
//	}

	// *******************************************************************************//
	public void borrarPartida() {
		hiloAzul.eliminarPartida();
	}

	// *******************************************************************************//
	public void sacarInvitado() {
		Command comando = new JugDesconectadoRoomCommand(hiloRojo.getId());
		enviarMensajeMaquina(comando);
	}

	public ClientThread getHiloAzul() {
		return hiloAzul;
	}
}// fin de la clase Partida
