package com.andresoftware.tesis.statemachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.andresoftware.tesis.server.Card;


import commands.CambioTableroCommand;
import commands.Command;
import commands.EmpezoPartidaCommand;
import commands.FinJuegoPorDesconexion;
import commands.JugDesconectadoRoomCommand;
import commands.JugarCommand;
import commands.ReadyPlayerCommand;
import commands.TimeOutCommand;
import commands.TurnoCommand;

import constants.TipoDeEvento;

//version 30/05/2011

/**
 * Maquina de Estados ue sirve para llevar la logica del juego
 * 
 * @author Rafael
 * 
 */
public class StateMachine extends Card {

	// *********************************************************************//
	// ** ATRIBUTOS **//
	// *********************************************************************//

	/** Tabla hash que guarda los parametros de las cartas */
	private Map<String, Object> par;
	/** Tabla hash que guarda al tablero */
	private Map<String, Object> ctx;
	/** Tablero */
	private TableGame tablero;

	/** Enumerado para los Estados */
	public enum TipoDeEstado {
		ESTADO_INI, 
		RANDOM_SELECT, 
		TURNA, TURNR, 
		VALIDAJUGADA_A, 
		VALIDAJUGADA_R, 
		ESTADO_FINAL, 
		PARTIDA_CREADA, 
		ELIGIENDO_CARTAS, 
		JUGADOR_PRINCIPAL_RDY, 
		JUGADOR_INVITADO_RDY, 
		ROJO_GANADOR, AZUL_GANADOR, 
		ELIGUIENDO_TABLERO_AZUL, 
		ELIGUIENDO_TABLERO_ROJO, 
		PROCESAR_REVANCHA_ROJO, 
		PROCESAR_REVANCHA_AZUL
	};

	// ----------------------------------------------------------------
	protected TipoDeEstado estado = TipoDeEstado.ESTADO_INI;
	protected TipoDeEvento eventForSocket = null;
	protected List<TipoDeEvento> colaEvento = new ArrayList<TipoDeEvento>();
	protected boolean disparado;

	private GameInterface partida;
	private int idInvitado;
	private int idCreador;
	// private Timer timer;
	private GuiTestTimer timer;
	private GuiTestTimer timerRevancha;

	// ------------------------------------------------------------

	// *****************************CONSTRUCTOR*****************************//
	public StateMachine(GameInterface partida) {
		this.partida = partida;
		par = this.partida.par;
		ctx = this.partida.ctx;
		tablero = (TableGame) ctx.get("tablero");
	}

	

	private int getIdCreador() {
		return idCreador;
	}

	private int getIdInvitado() {
		return idInvitado;
	}

	protected void eventoInternoDisparado(TipoDeEvento evento,
			Map<String, Object> ctx, Map<String, Object> par, int id)
			throws UnknowEventForState, IdCreatorInvalid {
		if (disparado) {
			colaEvento.add(evento);
			return;
		}
		disparado = true;
		System.out.println(" En el estado " + estado + ", LLego el evento: "
				+ evento);
		switch (estado) {
		case ESTADO_INI:
			estadoIni(evento, ctx, par, id);
			break;
		case PARTIDA_CREADA:
			estadorPartidaCreada(evento, ctx, par, id);
			break;

		case ELIGIENDO_CARTAS:
			estadoEliendoCartas(evento, ctx, par, id);
			break;

		case JUGADOR_PRINCIPAL_RDY:
			estadoJugadorPrincipalRdy(evento, ctx, par, id);
			break;

		case JUGADOR_INVITADO_RDY:
			estadoJugadorInvitadoRdy(evento, ctx, par, id);
			break;

		case RANDOM_SELECT:
			estadoRandomSelect(evento, ctx, par, id);
			break;

		case TURNA:
			estadoTurnA(evento, ctx, par, id);
			break;

		case TURNR:
			estadoTurnR(evento, ctx, par, id);
			break;

		case VALIDAJUGADA_A:
			estadoValidarJugada_A(evento, ctx, par, id);
			break;

		case VALIDAJUGADA_R:
			estadoValidarJugada_R(evento, ctx, par, id);
			break;

		case ELIGUIENDO_TABLERO_AZUL:
			estadoEligiendoTableroAzul(evento, ctx, par, id);
			break;

		case ELIGUIENDO_TABLERO_ROJO:
			estadoEligiendoTableroRojo(evento, ctx, par, id);
			break;

		case AZUL_GANADOR:
			estadoAzulGanador(evento, ctx, par, id);
			break;

		case ROJO_GANADOR:
			estadoRojoGanador(evento, ctx, par, id);
			break;

		case ESTADO_FINAL:
			estadoFinPartida(evento, id);
			break;
		case PROCESAR_REVANCHA_AZUL:
			estadoProcesarRevanchaAzul(evento, id);
			break;
		case PROCESAR_REVANCHA_ROJO:
			estadoProcesarRevanchaRojo(evento, id);
			break;
		default:
			estadoIlegal(estado, evento);
			break;
		}
		disparado = false;
		if (!colaEvento.isEmpty()) {
			TipoDeEvento tipEvento = colaEvento.remove(0);
			disparaEvento(tipEvento, ctx, par, id);
		}
	}

	public synchronized void disparaEvento(TipoDeEvento event,
			Map<String, Object> ctx, Map<String, Object> par, int id)
			throws UnknowEventForState, IdCreatorInvalid {
		if (checkPreFireEvent(event)) {
			eventoInternoDisparado(event, ctx, par, id);
		}
	}

	// *********************************************************************//
	protected boolean checkPreFireEvent(TipoDeEvento event) {
		return true;
	}

	// --------------------------------------------------------------------------------
	private void estadoIni(TipoDeEvento evento, Map<String, Object> ctx,
			Map<String, Object> par, int id) throws UnknowEventForState,
			IdCreatorInvalid {
		switch (evento) {
		case inicio:
			estado = TipoDeEstado.ESTADO_INI;
			break;
		case crearPartida:
			this.idCreador = id;
			estado = TipoDeEstado.PARTIDA_CREADA;
			break;

		default:
			throw new UnknowEventForState(evento, estado);

		}// fin de switch event
	}// fin de el metodo privado estodIncial
		// --------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------

	private void estadorPartidaCreada(TipoDeEvento evento,
			Map<String, Object> ctx, Map<String, Object> par, int id)
			throws UnknowEventForState {

		Command comando;

		switch (evento) {

		case llegoInvitado:
			this.idInvitado = id;
			comando = new ReadyPlayerCommand(idInvitado);
			partida.enviarHiloAzul(comando);
			//partida.setConsultarCartasRojo(false);
			estado = TipoDeEstado.ELIGIENDO_CARTAS;
			break;
		case revanchaRojoOk:
			partida.Revancha(partida.getHiloAzul(),partida.getHiloRojo());
			this.idInvitado = id;
			comando = new ReadyPlayerCommand(idInvitado);
			partida.enviarHiloAzul(comando);
			//partida.setConsultarCartasRojo(false);
			estado = TipoDeEstado.ELIGIENDO_CARTAS;
			
		case creadorDesconectado:
			partida.borrarPartida();
			estado = TipoDeEstado.ESTADO_FINAL;
			break;

		default:
			throw new UnknowEventForState(evento, estado);
		}

	}

	// --------------------------------------------------------------------------------

	private void estadoEliendoCartas(TipoDeEvento evento,
			Map<String, Object> ctx, Map<String, Object> par, int id)
			throws UnknowEventForState, IdCreatorInvalid {

		switch (evento) {

		case creadorRdy:
			if (id == this.idCreador) {
				estado = TipoDeEstado.JUGADOR_PRINCIPAL_RDY;
			} else {
				throw new IdCreatorInvalid(id);
			}
			break;

		case InvitadoRdy:

			// quitar esto se debe validar desde partida segun el id q llegan
			// por el socket
			if (id == this.idInvitado) {
				partida.enviarHiloAzul("El_Invitado_Esta_Listo");
				estado = TipoDeEstado.JUGADOR_INVITADO_RDY;

			} else {
				throw new IdCreatorInvalid(id);
			}
			break;

		case creadorDesconectado:
			partida.desconectarCreadorRoom();
			estado = TipoDeEstado.ESTADO_FINAL;
			break;
		case invitadoDesconectado:
			// se envia para q el invitado sepa que fue desconectado
			Command comando = new JugDesconectadoRoomCommand(idInvitado);
			partida.enviarHiloRojo(comando);

			partida.desconectarInvitadoRoom();
			estado = TipoDeEstado.PARTIDA_CREADA;
			break;
		default:
			throw new UnknowEventForState(evento, estado);
		}

	}

	// --------------------------------------------------------------------------------

	private void estadoJugadorInvitadoRdy(TipoDeEvento evento,
			Map<String, Object> ctx, Map<String, Object> par, int id)
			throws UnknowEventForState, IdCreatorInvalid {

		switch (evento) {

		case creadorRdy:
			Command comanso3 = new EmpezoPartidaCommand(id);
			partida.enviarMsjAmbosJug(comanso3);
			System.out.println(tablero.getElemento());
			Command cambioTableroCommand = new CambioTableroCommand(
					tablero.getElemento(), true);
			partida.enviarMsjAmbosJug(cambioTableroCommand);
			partida.enviarHiloAzul(partida.getIdCartasAzules());
			partida.enviarHiloRojo(partida.getIdCartasRojas());

			double rand1 = Math.random();
			if (rand1 <= 0.5) {

				disparaEvento(TipoDeEvento.random_a, ctx, par, id);

				partida.setIdHiloEnTurno(partida.getIdHiloAzul());
				partida.setIdHiloEnEspera(partida.getIdHiloRojo());
				Command command = new TurnoCommand(partida.getIdHiloAzul());
				partida.enviarMsjAmbosJug(command);

			} else {
				disparaEvento(TipoDeEvento.random_r, ctx, par, id);

				partida.setIdHiloEnTurno(partida.getIdHiloRojo());
				Command command = new TurnoCommand(partida.getIdHiloRojo());
				partida.enviarMsjAmbosJug(command);
				partida.setIdHiloEnEspera(partida.getIdHiloAzul());
			}
			estado = TipoDeEstado.RANDOM_SELECT;
			break;

		case creadorDesconectado:
			partida.desconectarCreadorRoom();
			estado = TipoDeEstado.ESTADO_FINAL;
			break;

		case invitadoDesconectado:
			// se envia para q el invitado sepa que fue desconectado
			Command comando = new JugDesconectadoRoomCommand(idInvitado);
			partida.enviarHiloRojo(comando);

			partida.desconectarInvitadoRoom();
			estado = TipoDeEstado.PARTIDA_CREADA;
			break;

		default:
			partida.enviar("Error!!!! accion(evento) no valida...(estado)", id);
			break;
		}

	}

	// --------------------------------------------------------------------------------

	private void estadoJugadorPrincipalRdy(TipoDeEvento evento,
			Map<String, Object> ctx, Map<String, Object> par, int id)
			throws UnknowEventForState, IdCreatorInvalid {

		switch (evento) {

		case InvitadoRdy:
			partida.enviarHiloAzul("El_Invitado_Esta_Listo");
			Command comanso3 = new EmpezoPartidaCommand(id);
			partida.enviarMsjAmbosJug(comanso3);
			Command cambioTableroCommand = new CambioTableroCommand(
					tablero.getElemento(), true);
			partida.enviarMsjAmbosJug(cambioTableroCommand);
			partida.enviarHiloAzul(partida.getIdCartasAzules());
			partida.enviarHiloRojo(partida.getIdCartasRojas());
			double rand1 = Math.random();
			if (rand1 <= 0.5) {

				disparaEvento(TipoDeEvento.random_a, ctx, par, id);
				partida.setIdHiloEnTurno(partida.getIdHiloAzul());
				Command command = new TurnoCommand(partida.getIdHiloAzul());
				partida.enviarMsjAmbosJug(command);
				partida.setIdHiloEnEspera(partida.getIdHiloRojo());

			} else {

				disparaEvento(TipoDeEvento.random_r, ctx, par, id);

				partida.setIdHiloEnTurno(partida.getIdHiloRojo());
				Command command = new TurnoCommand(partida.getIdHiloRojo());
				partida.enviarMsjAmbosJug(command);
				partida.setIdHiloEnEspera(partida.getIdHiloAzul());

			}
			estado = TipoDeEstado.RANDOM_SELECT;

			break;

		case creadorDesconectado:
			partida.desconectarCreadorRoom();
			estado = TipoDeEstado.ESTADO_FINAL;
			break;

		case invitadoDesconectado:
			partida.desconectarInvitadoRoom();
			estado = TipoDeEstado.PARTIDA_CREADA;
			break;

		default:
			throw new UnknowEventForState(evento, estado);

		}

	}

	private void estadoRandomSelect(TipoDeEvento evento,
			Map<String, Object> ctx, Map<String, Object> par, int id)
			throws UnknowEventForState, IdCreatorInvalid {
		switch (evento) {
		case random_r:
			startTimerInvi();
			estado = TipoDeEstado.TURNR;
			break;

		case random_a:
			startTimerCreador();
			estado = TipoDeEstado.TURNA;
			break;

		case creadorDesconectado:
			partida.procesarDesconexionJugandoCreador();
			disparaEvento(TipoDeEvento.creadorDesconectado, null, null, id);
			// meter el partida.desconectarCreadorRoom(); dentro del estado
			// estado = TipoDeEstado.ROJO_GANADOR;
			// por q el socket desaparece cuando llamo esta funcion

			estado = TipoDeEstado.ROJO_GANADOR;
			break;

		case invitadoDesconectado:
			partida.procesarDesconexionJugadorInvitado();
			disparaEvento(TipoDeEvento.invitadoDesconectado, null, null, id);
			// meter el partida.desconectarInvitadoRoom(); dentro del estado
			// estado = TipoDeEstado.AZUL_GANADOR;
			// por q el socket desaparece cuando llamo esta funcion
			estado = TipoDeEstado.AZUL_GANADOR;
			break;

		default:
			throw new UnknowEventForState(evento, estado);
		}// fin de switch event
	}// fin de el metodo privado estodIncial

	// *********************************************************************//

	private void estadoTurnA(TipoDeEvento evento, Map<String, Object> ctx,
			Map<String, Object> par, int id) throws UnknowEventForState,
			IdCreatorInvalid {

		switch (evento) {

		case finPartida:
			disparaEvento(TipoDeEvento.finPartida, null, null, 0);
			estado = TipoDeEstado.ESTADO_FINAL;
			break;

		case juega_a:
			if (this.idCreador == id && partida.getIdHiloEnTurno() == id) {
				int x = (Integer) par.get("row");
				int y = (Integer) par.get("colum");

				Card carta = new Card();
				carta = (Card) par.get("carta");
				tablero = (TableGame) ctx.get("tablero");
				if (tablero.setCard(x, y, carta)) {
					Command cartaComando = tablero.crearCartaCommand(carta);

					Command comando = new JugarCommand(getIdInvitado(),
							carta.getIdCarta(), x, y, cartaComando);

					partida.enviarMsjJugEspera(comando);
					if (tablero.isFull()) {
						disparaEvento(TipoDeEvento.finPartida, ctx, null, 0);
					} else if (tablero.cambio()) {
						disparaEvento(TipoDeEvento.cambioTablero, ctx, null, 0);
					} else {
						disparaEvento(TipoDeEvento.jugadaOK, null, null,
								idInvitado);
					}
				} else {
					// Jugada Invalida repetida
					partida.enviarHiloAzul("jugadaIvalidaCommand");
					disparaEvento(TipoDeEvento.jugadaInvalid, ctx, par, 0);
				}
			}// fin del if id
			estado = TipoDeEstado.VALIDAJUGADA_A;

			break;

		case timeOut:
			disparaEvento(TipoDeEvento.timeOut, ctx, par, id);
			estado = TipoDeEstado.ROJO_GANADOR;
			break;

		case creadorDesconectado:
			partida.procesarDesconexionJugandoCreador();
			disparaEvento(TipoDeEvento.creadorDesconectado, null, null, id);
			estado = TipoDeEstado.ROJO_GANADOR;
			break;

		case invitadoDesconectado:
			partida.procesarDesconexionJugadorInvitado();
			disparaEvento(TipoDeEvento.invitadoDesconectado, null, null, id);
			estado = TipoDeEstado.AZUL_GANADOR;
			break;
		default:
			partida.enviar("Error!!!! accion(evento) no valida...(estado) ", id);
			break;
		}// fin del switch evento TURNA
	}// fin del metodo privado estado TURNA

	// --------------------------------------------------------------------------------
	// validacion mediance un arrojo de expecion si no coincide el idInvitado
	// como coloco otro condicion de exepcion para el caso de evento ilegane en
	// el estado

	private void estadoValidarJugada_A(TipoDeEvento evento,
			Map<String, Object> ctx, Map<String, Object> par, int id)
			throws IdCreatorInvalid, UnknowEventForState {

		switch (evento) {
		// **********************************************//

		case finPartida:
			disparaEvento(TipoDeEvento.finPartida, null, null, 0);
			estado = TipoDeEstado.ESTADO_FINAL;
			break;
		case jugadaInvalid:
			estado = TipoDeEstado.TURNA;
			break;

		case creadorDesconectado:
			partida.procesarDesconexionJugandoCreador();
			disparaEvento(TipoDeEvento.creadorDesconectado, null, null, id);
			estado = TipoDeEstado.ROJO_GANADOR;
			break;

		case invitadoDesconectado:
			partida.procesarDesconexionJugadorInvitado();
			disparaEvento(TipoDeEvento.invitadoDesconectado, null, null, id);
			estado = TipoDeEstado.AZUL_GANADOR;
			break;

		case cambioTablero:
			estado = TipoDeEstado.ELIGUIENDO_TABLERO_AZUL;
			break;

		case jugadaOK:
			tablero.actualizarTablero();
			tablero.enviarActualizacoinTablero();
			partida.switchTurn();
			Command command = new TurnoCommand(partida.getIdHiloEnTurno());
			partida.enviarMsjAmbosJug(command);
			estado = TipoDeEstado.TURNR;
			startTimerInvi();
			break;

		case timeOut:
			disparaEvento(TipoDeEvento.timeOut, ctx, par, id);
			estado = TipoDeEstado.ROJO_GANADOR;
			break;

		default:
			throw new UnknowEventForState(evento, estado);

		}// fin del switch eventoA
	}// fin del metodo provivado estadoValidarJugadaA

	// *********************************************************************//

	private void startTimerInvi() {
		if (timer != null) {
			timer.stopTimer();
		}

		System.out.println("PRE---WTF!!! startTimerInvi: " + timer);
		timer = new GuiTestTimer(this, partida, idInvitado);
		System.out.println("POS---WTF!!! startTimerInvi: " + timer);
	}

	private void startTimerCreador() {
		if (timer != null) {
			timer.stopTimer();
		}
		timer = new GuiTestTimer(this, partida, idCreador);
	}

	private void estadoTurnR(TipoDeEvento evento, Map<String, Object> ctx,
			Map<String, Object> par, int id) throws UnknowEventForState,
			IdCreatorInvalid {

		switch (evento) {

		case finPartida:
			disparaEvento(TipoDeEvento.finPartida, null, null, 0);
			estado = TipoDeEstado.ESTADO_FINAL;
			break;

		case jugadaInvalid:
			estado = TipoDeEstado.TURNR;
			break;

		case jugadaOK:
			estado = TipoDeEstado.TURNA;
			break;

		case juega_r:
			if (this.idInvitado == id && partida.getIdHiloEnTurno() == id) {

				int x = (Integer) par.get("row");
				int y = (Integer) par.get("colum");
				Card carta = new Card();

				carta = (Card) par.get("carta");
				tablero = (TableGame) ctx.get("tablero");

				if (tablero.setCard(x, y, carta)) {
					System.out.println("11");
					Command cartaComando = tablero.crearCartaCommand(carta);
					Command comando = new JugarCommand(getIdCreador(),
							carta.getIdCarta(), x, y, cartaComando);
					partida.enviarMsjJugEspera(comando);
					if (tablero.isFull()) {
						disparaEvento(TipoDeEvento.finPartida, ctx, null, 0);
					} else if (tablero.cambio()) {
						System.out.println("33");
						System.out.println("Cambio tablero: "
								+ tablero.cambio());
						disparaEvento(TipoDeEvento.cambioTablero, ctx, null, 0);
						System.out.println("44");
					} else {
						System.out.println("J  OK   ");
						disparaEvento(TipoDeEvento.jugadaOK, null, null, id);
					}

				} else {
					partida.enviarHiloRojo("jugadaIvalidaCommand");
					disparaEvento(TipoDeEvento.jugadaInvalid, ctx, par, id);
				}
			}
			System.out.println("55");
			estado = TipoDeEstado.VALIDAJUGADA_R;
			System.out.println("66");
			break;

		case timeOut:
			disparaEvento(TipoDeEvento.timeOut, null, null, id);
			estado = TipoDeEstado.AZUL_GANADOR;
			break;

		case creadorDesconectado:
			partida.procesarDesconexionJugandoCreador();
			disparaEvento(TipoDeEvento.creadorDesconectado, null, null, id);
			estado = TipoDeEstado.ROJO_GANADOR;
			break;

		case invitadoDesconectado:
			partida.procesarDesconexionJugadorInvitado();
			disparaEvento(TipoDeEvento.invitadoDesconectado, null, null, id);
			estado = TipoDeEstado.AZUL_GANADOR;
			break;

		default:
			partida.enviar("Error!!!! accion(evento) no valida...(estado) ", id);
			// estadoIlegal(TipoDeEstado.TURNR, evento);
			break;

		}// fin del switch evenetoTURNB

	}// fin del metodo privado estoTURNB
		// *********************************************************************//

	private void estadoValidarJugada_R(TipoDeEvento evento,
			Map<String, Object> ctx, Map<String, Object> par, int id)
			throws IdCreatorInvalid, UnknowEventForState {

		switch (evento) {
		case finPartida:
			disparaEvento(TipoDeEvento.finPartida, null, null, 0);
			estado = TipoDeEstado.ESTADO_FINAL;
			break;

		case jugadaInvalid:
			estado = TipoDeEstado.TURNR;
			break;

		case jugadaOK:
			tablero.actualizarTablero();
			tablero.enviarActualizacoinTablero();
			partida.switchTurn();
			Command command = new TurnoCommand(partida.getIdHiloEnTurno());
			partida.enviarMsjAmbosJug(command);
			estado = TipoDeEstado.TURNA;
			startTimerCreador();
			break;
		case cambioTablero:
			estado = TipoDeEstado.ELIGUIENDO_TABLERO_ROJO;
			break;

		case timeOut:
			disparaEvento(TipoDeEvento.timeOut, null, null, id);
			estado = TipoDeEstado.AZUL_GANADOR;
			break;

		case creadorDesconectado:
			partida.procesarDesconexionJugandoCreador();
			disparaEvento(TipoDeEvento.creadorDesconectado, null, null, id);
			estado = TipoDeEstado.ROJO_GANADOR;
			break;

		case invitadoDesconectado:
			partida.procesarDesconexionJugadorInvitado();
			disparaEvento(TipoDeEvento.invitadoDesconectado, null, null, id);
			estado = TipoDeEstado.AZUL_GANADOR;
			break;

		default:
			partida.enviar("Error!!!! accion(evento) no valida...(estado) ", id);

		}// fin del switch evento

	}// fin del metodo provivado estadoValidarJugadaB
		// --------------------------------------------------------------------------------

	private void estadoEligiendoTableroAzul(TipoDeEvento evento,
			Map<String, Object> ctx2, Map<String, Object> par2, int id)
			throws UnknowEventForState, IdCreatorInvalid {

		switch (evento) {
		case TableroElegido:
			disparaEvento(TipoDeEvento.jugadaOK, ctx2, par2, id);
			estado = TipoDeEstado.VALIDAJUGADA_A;
			break;
		case timeOut:
			disparaEvento(TipoDeEvento.timeOut, ctx, par, id);
			estado = TipoDeEstado.ROJO_GANADOR;
			break;
		default:
			partida.enviar("Error!!!! accion(evento) no valida...(estado) ", id);
		}

	}

	private void estadoEligiendoTableroRojo(TipoDeEvento evento,
			Map<String, Object> ctx2, Map<String, Object> par2, int id)
			throws UnknowEventForState, IdCreatorInvalid {
		switch (evento) {
		case TableroElegido:
			disparaEvento(TipoDeEvento.jugadaOK, ctx2, par2, id);
			estado = TipoDeEstado.VALIDAJUGADA_R;
			break;

		case timeOut:
			disparaEvento(TipoDeEvento.timeOut, null, null, 0);
			estado = TipoDeEstado.AZUL_GANADOR;
			break;
		default:
			partida.enviar("Error!!!! accion(evento) no valida...(estado) ", id);
		}

	}

	// --------------------------------------------------------------------------------
	private void estadoRojoGanador(TipoDeEvento evento,
			Map<String, Object> ctx, Map<String, Object> par, int idCreador)
			throws IdCreatorInvalid, UnknowEventForState {

		switch (evento) {
		case creadorDesconectado:
			timer.stopTimer();
			partida.guardarPartida(idInvitado, idCreador, tablero.getRojoPts(),
					tablero.getRojoPts());
			Command desconecta = new FinJuegoPorDesconexion(idInvitado, 0, 0);
			partida.enviarHiloRojo(desconecta);
			break;

		case timeOut:
			Command tiempo = new TimeOutCommand(this.idCreador);
			partida.enviarMsjAmbosJug(tiempo);
			partida.procesarFinDeJuego();
			partida.guardarPartida(partida.getIdHiloRojo(),
					partida.getIdHiloAzul(), tablero.getRojoPts(),
					tablero.getAzulPts());
			break;
		}

	}// fin del metodo provivado estadoValidarJugadaB
		// --------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------

	private void estadoAzulGanador(TipoDeEvento evento,
			Map<String, Object> ctx, Map<String, Object> par, int idCreador)
			throws IdCreatorInvalid, UnknowEventForState {

		switch (evento) {

		case invitadoDesconectado:
			timer.stopTimer();
			partida.guardarPartida(idCreador, idInvitado, tablero.getAzulPts(),
					tablero.getRojoPts());
			Command desconecta = new FinJuegoPorDesconexion(this.idCreador, 0,
					0);
			partida.enviarHiloAzul(desconecta);
			partida.desconectarInvitadoRoom();
			break;

		case timeOut:
			Command tiempo = new TimeOutCommand(this.idInvitado);
			partida.enviarMsjAmbosJug(tiempo);
			partida.procesarFinDeJuego();
			partida.guardarPartida(partida.getIdHiloAzul(),
					partida.getIdHiloRojo(), tablero.getAzulPts(),
					tablero.getRojoPts());
			break;
		}
	}// fin del metodo provivado estadoValidarJugadaB

	// --------------------------------------------------------------------------------

	private void estadoFinPartida(TipoDeEvento evento, int id)
			throws UnknowEventForState, IdCreatorInvalid {
		// como cierro la maquina de estados?
		switch (evento) {
		case finPartida:
			timer.stopTimer();
			tablero.winner();
			/** timer de revancha a un segundo como no tiene el la IU para revancha de lo contrarrio seria 20000 =  20 seg.*/
			timerRevancha = new GuiTestTimer(this, partida, 0,1000);
			guardarPartidaBD();
			break;
		case timeOut:
			partida.procesarFinDeJuego();
			break;
		case revanchaRojoOk:
			estado = TipoDeEstado.PROCESAR_REVANCHA_ROJO;
			break;
		case revanchaAzulOk:
			//se puede isar el id del comando
			estado = TipoDeEstado.PROCESAR_REVANCHA_AZUL;
			break;
		case NoRevancha:
			partida.procesarFinDeJuego();
			break;
		default:
			partida.enviar("Error!!!! accion(evento) no valida...(estado) ", id);
			break;
		}

	}// fin del estadoFinal

	private void estadoProcesarRevanchaRojo(TipoDeEvento evento, int id)
			throws UnknowEventForState, IdCreatorInvalid {
		// como cierro la maquina de estados?
		switch (evento) {
		case revanchaAzulOk:
			disparaEvento(TipoDeEvento.Revancha, null, null, 0);
			estado = TipoDeEstado.ELIGIENDO_CARTAS;
			timerRevancha.stopTimer();
			break;
			
		case NoRevancha:
			timerRevancha.stopTimer();
			partida.procesarFinDeJuego();
			break;
		case timeOut:
			partida.procesarFinDeJuego();
			break;
		
			
		default:
			partida.enviar("Error!!!! accion(evento) no valida...(estado) ", id);
			break;
		}

	}// fin procesarRecanchaRojo

	private void estadoProcesarRevanchaAzul(TipoDeEvento evento, int id)
			throws UnknowEventForState, IdCreatorInvalid {
		// como cierro la maquina de estados?
		switch (evento) {
		case revanchaRojoOk:
			disparaEvento(TipoDeEvento.Revancha, null, null, 0);
			estado = TipoDeEstado.ELIGIENDO_CARTAS;
			timerRevancha.stopTimer();
			break;
		case NoRevancha:
			timerRevancha.stopTimer();
			partida.procesarFinDeJuego();
			break;
		case timeOut:
			partida.procesarFinDeJuego();
			break;
		default:
			partida.enviar("Error!!!! accion(evento) no valida...(estado) ", id);
			break;
		}

	}// fin del estadoFinal

	private void guardarPartidaBD() {

		// rojo ganador
		if (tablero.getAzulPts() < tablero.getRojoPts()) {
			partida.guardarPartida(partida.getIdHiloRojo(),
					partida.getIdHiloAzul(), tablero.getRojoPts(),
					tablero.getAzulPts());
		} else if (tablero.getAzulPts() > tablero.getRojoPts()) {
			partida.guardarPartida(partida.getIdHiloAzul(),
					partida.getIdHiloRojo(), tablero.getAzulPts(),
					tablero.getRojoPts());
		} else {
			partida.guardarPartidaEmpatada(idCreador, idInvitado,
					tablero.getAzulPts(), tablero.getRojoPts());
			// Empate!!
		}

	}

	// *********************************************************************//

	// *********************************************************************//
	// ** INTERFAZ **//

	// *********************************************************************//
	// Funcion que valida el intento de jugada de Azul o rojo
	// Funcion privada que valida la jugara en la matriz de cartas[4][4] returna
	// verdadero
	// si la carta revisada en su atributo fuerzaE es =0 por que inicialize la
	// matriz con cartas de eso tipo
	// en la funcion inicializar matriz
	// *********************************************************************//
	private void estadoIlegal(TipoDeEstado estado, TipoDeEvento evento) {
		System.out.println("Estado Ilegal: " + estado + " por el evento:  "
				+ evento);

	}
	// *********************************************************************//

}// fin de la clase maquinaDeEStados