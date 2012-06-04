package com.andresoftware.tesis.server;

import java.util.ArrayList;
import java.util.List;

import commands.ActualizarTablero;
import commands.CartaCommand;
import commands.CartasVolteadaCommand;
import commands.Command;
import commands.EleguirTableroCommand;
import commands.GameOver;
import commands.PosicionBean;
import commands.PuntosCommand;

import constants.Elemento;
import constants.TipoColor;

/**
 * Esta clase se encarga de llevar la logica del juego!!! xD
 * 
 * @author rafael_andres_y_jesus
 * 
 */
public class Tablero extends Card {

	private Card[][] tablero;

	private int azulPts, rojoPts;
	private int azulNumeroLineas;
	private int rojoNumeroLineas;
	private int azulNumeroDiagonales;
	private int rojoNumeroDiagonales;
	private Command comandoGameOver;
	private Command comandoEmpate;
	List<PosicionBean> listaPosicion;

	private Partida partida;
	public Elemento elemento;

	boolean cambio;

	// *******************************************************************************//

	public Tablero(Partida partida) {

		this.partida = partida;
		tablero = new Card[4][4];
		setAzulPts(0);
		azulNumeroDiagonales = 0;
		azulNumeroDiagonales = 0;
		listaPosicion = new ArrayList<PosicionBean>();

		setRojoPts(0);
		rojoNumeroDiagonales = 0;
		rojoNumeroDiagonales = 0;

		double rand = Math.random();
		double r = rand * 100;
		int e = (int) (r % 4);
		randomElemento(e);

	}

	// *******************************************************************************//
	/**
	 * funcion para introducir un carta en el tablero retorna true si es posible
	 * y false de lo contrario
	 * 
	 * @param row
	 *            Fila
	 * @param colum
	 *            Columna
	 * @param c
	 *            Carta
	 * @param comunicacion
	 *            Comunicacion
	 * @author rafael
	 * @return boolean
	 * */
	// *******************************************************************************//
	public boolean setCard(int row, int colum, Card c)

	throws IllegalArgumentException {

		if (row <= 3 && row >= 0 && colum <= 3 && colum >= 0) {
			Card cartaAux = new Card();
			cartaAux = tablero[row][colum];
			if (cartaAux == null) {
				tablero[row][colum] = c;
				battle(row, colum);
				return true;
			}
			System.err.println("Jugada repetida coloca tu carta en otro sitio");
			return false;
		}

		System.err.println(" parametros ilegales de x y  ");
		return false;

	}

	// *******************************************************************************//
	/**
	 * Devuelve una carta
	 * 
	 * @param x
	 *            Fila
	 * @param y
	 *            Columna
	 * @return Carta
	 */
	// *******************************************************************************//
	public Card getCard(int x, int y) {
		Card cartaAux = new Card();
		cartaAux = tablero[x][y];
		if (cartaAux == null) {
			return null;
		} else
			return cartaAux;

	}

	// *******************************************************************************//
	/**
	 * Este metodo sirve para saber si el tablero esta ful
	 * 
	 * @return boolean
	 */
	// *******************************************************************************//
	public boolean isFull() {
		Card cAux = new Card();
		int count = 0;
		for (int k = 0; k <= 3; k++) {
			for (int j = 0; j <= 3; j++) {
				cAux = tablero[k][j];
				if (cAux != null)
					count++;
			}
		}
		if (count == 16) {
			return true;
		} else
			return false;
	}

	// *******************************************************************************//
	/**
	 * Este metodo sirve para saber quien es el ganador
	 */
	// *******************************************************************************//
	public void winner() {

		if (getAzulPts() > getRojoPts()) {
			// partida.guardarPartida(partida.getIdHiloAzul(),
			// partida.getIdHiloRojo(), azulPts, rojoPts);
			comandoGameOver = new GameOver(partida.getIdHiloAzul(),
					getAzulPts(), getRojoPts());
			partida.enviarMsjAmbosJug(comandoGameOver);
			return;
		} else if (getAzulPts() < getRojoPts()) {
			// partida.guardarPartida(partida.getIdHiloRojo(),
			// partida.getIdHiloAzul(), rojoPts, azulPts );
			comandoGameOver = new GameOver(partida.getIdHiloRojo(),
					getRojoPts(), getAzulPts());
			partida.enviarMsjAmbosJug(comandoGameOver);
			return;
		} else {
			comandoEmpate = new GameOver(getAzulPts(), getRojoPts());
			partida.enviarMsjAmbosJug(comandoEmpate);

			return;
		}
	}

	// *******************************************************************************//
	/**
	 * Esta Funcion sirve para verificar si existen batallas por los costados de
	 * la carta que se ha puesto.
	 * 
	 * @param row
	 *            Fila
	 * @param colum
	 *            Columna
	 */
	// *******************************************************************************//
	private void battle(int row, int colum) {

		Card card = tablero[row][colum];
		color = card.getColor();
		// color de la carta recien colocada
		switch (color) {
		case AZUL:
			cambio = verificarCombos(row, colum, "AZUL");
			// Caso batalla por el Norte
			if (row != 0) {
				if (nortABattleA(row, colum, card)) {
					setAzulPts(getAzulPts() + 1);
					Command comando2 = new PuntosCommand(getAzulPts(),
							getRojoPts());
					partida.enviarMsjAmbosJug(comando2);
					cambio = verificarCombos(row - 1, colum, "AZUL");
				}
			}
			// Caso batalla por el Sur
			if (row != 3) {
				if (southBattleA(row, colum, card)) {
					setAzulPts(getAzulPts() + 1);
					Command comando2 = new PuntosCommand(getAzulPts(),
							getRojoPts());
					partida.enviarMsjAmbosJug(comando2);
					cambio = verificarCombos(row + 1, colum, "AZUL");
				}
			}
			// Caso batalla por el Este aux es la carta adyacente por Este
			if (colum != 3) {
				if (eastbattleA(row, colum, card)) {
					setAzulPts(getAzulPts() + 1);
					Command comando2 = new PuntosCommand(getAzulPts(),
							getRojoPts());
					partida.enviarMsjAmbosJug(comando2);
					cambio = verificarCombos(row, colum + 1, "AZUL");
				}
			}
			// Caso batalla por el Oeste
			if (colum != 0) {
				if (westbattleA(row, colum, card)) {
					setAzulPts(getAzulPts() + 1);
					Command comando2 = new PuntosCommand(getAzulPts(),
							getRojoPts());
					partida.enviarMsjAmbosJug(comando2);
					cambio = verificarCombos(row, colum - 1, "AZUL");
				}
			}
			// comunicacion.setPuntos(azulPts);
			pintarTablero();
			break;
		case ROJA:
			cambio = verificarCombos(row, colum, "ROJO");
			// Revisando si el jugador rojo lleno una linea
			// Caso batalla por el Norte
			if (row != 0) {
				if (nortbattleR(row, colum, card)) {
					setRojoPts(getRojoPts() + 1);
					Command comando2 = new PuntosCommand(getAzulPts(),
							getRojoPts());
					partida.enviarMsjAmbosJug(comando2);
					cambio = verificarCombos(row - 1, colum, "ROJO");
				}
			}
			// Caso batalla por el Sur
			if (row != 3) {
				if (southbattleR(row, colum, card)) {
					setRojoPts(getRojoPts() + 1);
					Command comando2 = new PuntosCommand(getAzulPts(),
							getRojoPts());
					partida.enviarMsjAmbosJug(comando2);
					cambio = verificarCombos(row + 1, colum, "ROJO");
				}
			}
			// caso batalla por el Este
			if (colum != 3) {
				if (eastbattleR(row, colum, card)) {
					setRojoPts(getRojoPts() + 1);
					Command comando2 = new PuntosCommand(getAzulPts(),
							getRojoPts());
					partida.enviarMsjAmbosJug(comando2);
					cambio = verificarCombos(row, colum + 1, "ROJO");
				}
			}
			// Caso batalla por el Oeste
			if (colum != 0) {
				if (westbattleR(row, colum, card)) {
					setRojoPts(getRojoPts() + 1);
					Command comando2 = new PuntosCommand(getAzulPts(),
							getRojoPts());
					partida.enviarMsjAmbosJug(comando2);
					cambio = verificarCombos(row, colum - 1, "ROJO");
				}
			}
			pintarTablero();
			break;
		default:
			System.out.println("entro en default");
			break;
		}// fin del switch
	}

	// *******************************************************************************//
	/**
	 * Atiende la batalla de una carta azul por el Norte
	 * 
	 * @param row
	 *            Fila
	 * @param colum
	 *            Columna
	 * @param card
	 *            Carta
	 * @return boolean
	 */
	// *******************************************************************************//
	private boolean nortABattleA(int row, int colum, Card card) {
		Card aux = new Card();
		int rowAux = row - 1;
		aux = tablero[rowAux][colum];
		System.out.println("Norte   fila: " + rowAux + " columna:" + colum);
		if (aux != null && aux.getColor().equals(TipoColor.ROJA)) {

			if (card.getElemento().equals(elemento)
					&& aux.getElemento().equals(elemento)
					|| !card.getElemento().equals(elemento)
					&& !aux.getElemento().equals(elemento)) {

				if (card.getFuerzaN() > aux.getFuerzaS()) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Norte");
					return true;
				}
			} else if (aux.getElemento().equals(elemento)) {
				if (card.getFuerzaN() > aux.getFuerzaS() + 1) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Norte");
					return true;
				}
			} else if (card.getElemento().equals(elemento)) {
				if (card.getFuerzaN() + 1 > aux.getFuerzaS()) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Norte");
					return true;
				}

			}
		}
		return false;
	}

	// *******************************************************************************//
	/**
	 * Atiende la batalla de una carta azul por el Sur
	 * 
	 * @param row
	 *            Fila
	 * @param colum
	 *            Columna
	 * @param card
	 *            Carta
	 * @return boolean
	 */
	// *******************************************************************************//
	private boolean southBattleA(int row, int colum, Card card) {
		Card aux = new Card();
		int rowAux = row + 1;
		aux = tablero[rowAux][colum];

		if (aux != null) {
			System.out.println("Carta Rival FuerzaNorte : " + aux.getFuerzaN());
			System.out.println("Elemento Carta RIVAL " + aux.getElemento());
			System.out.println("COLOR Carta RIVAL:" + aux.getColor());
		}
		System.out.println("Sur  fila: " + rowAux + " columna:" + colum);
		if (aux != null && aux.getColor().equals(TipoColor.ROJA)) {
			if (card.getElemento().equals(elemento)
					&& aux.getElemento().equals(elemento)
					|| (!card.getElemento().equals(elemento) && !aux
							.getElemento().equals(elemento))) {
				if (card.getFuerzaS() > aux.getFuerzaN()) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Sur");
					return true;
				}
			} else if (aux.getElemento().equals(elemento)) {
				if (card.getFuerzaS() > aux.getFuerzaN() + 1) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Sur");
					return true;
				}

			}else if (card.getElemento().equals(elemento)) {
				int FSplus = card.getFuerzaS() + 1;
				if (FSplus > aux.getFuerzaN()) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Sur");
					return true;
				}

			}
		}
		return false;
	}

	// *******************************************************************************//
	/**
	 * Atiende la batalla de una carta azul por el Este
	 * 
	 * @param row
	 *            Fila
	 * @param colum
	 *            Columna
	 * @param card
	 *            Carta
	 * @return boolean
	 */
	// *******************************************************************************//
	private boolean eastbattleA(int row, int colum, Card card) {
		Card aux = new Card();
		int columAux = colum + 1;
		aux = tablero[row][columAux];
		System.out.println("Este fila: " + row + " columna: " + columAux);
		// si la carta no es nula comparo las fuerza por el este de card y por
		// Oeste de aux
		if (aux != null && aux.getColor().equals(TipoColor.ROJA)) {
			// Si la carta recien colocada es mayor que la adyacente entonces la
			// cambio de color
			if (card.getElemento().equals(elemento)
					&& aux.getElemento().equals(elemento)
					|| !card.getElemento().equals(elemento)
					&& !aux.getElemento().equals(elemento)) {
				if (card.getFuerzaE() > aux.getFuerzaO()) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Este");
					return true;
				}
			} else if (aux.getElemento().equals(elemento)) {

				if (card.getFuerzaE() > aux.getFuerzaO() + 1) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Este");
					return true;
				}
			} else if (card.getElemento().equals(elemento)) {

				if (card.getFuerzaE() + 1 > aux.getFuerzaO()) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Este");
					return true;
				}
			}
		}
		return false;
	}

	// *******************************************************************************//
	/**
	 * Atiende la batalla de una carta azul por el Oeste Billy The Kid xD
	 * 
	 * @param row
	 *            Fila
	 * @param colum
	 *            Columna
	 * @param card
	 *            Carta
	 * @return boolean
	 */
	// *******************************************************************************//
	private boolean westbattleA(int row, int colum, Card card) {
		int columAux = colum - 1;
		Card aux = new Card();
		aux = tablero[row][columAux];
		System.out.println("Oeste fila: " + row + " columna: " + columAux);
		if (aux != null && aux.getColor().equals(TipoColor.ROJA)) {

			if (card.getElemento().equals(elemento)
					&& aux.getElemento().equals(elemento)
					|| !card.getElemento().equals(elemento)
					&& !aux.getElemento().equals(elemento)) {
				if (card.getFuerzaO() > aux.getFuerzaE()) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Oeste");
					return true;
				}
			} else if (aux.getElemento().equals(elemento)) {

				if (card.getFuerzaO() > aux.getFuerzaE() + 1) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Oeste");
					return true;

				}
			} else if (card.getElemento().equals(elemento)) {

				if (card.getFuerzaO() + 1 > aux.getFuerzaE()) {
					aux.setColor(TipoColor.AZUL);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.AZUL,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.AZUL + " gano batalla Oeste");
					return true;
				}
			}
		}
		return false;
	}

	// *******************************************************************************//
	/**
	 * Atiende la batalla de una carta Roja por el Norte
	 * 
	 * @param row
	 *            Fila
	 * @param colum
	 *            Columna
	 * @param card
	 *            Carta
	 * @return boolean
	 */
	// *******************************************************************************//
	private boolean nortbattleR(int row, int colum, Card card) {
		Card aux = new Card();
		int rowAux = row - 1;
		aux = tablero[rowAux][colum];
		System.out.println("Norte   fila: " + rowAux + " columna:" + colum);
		if (aux != null && aux.getColor().equals(TipoColor.AZUL)) {
			if (card.getElemento().equals(elemento)
					&& aux.getElemento().equals(elemento)
					|| !card.getElemento().equals(elemento)
					&& !aux.getElemento().equals(elemento)) {

				if (card.getFuerzaN() > aux.getFuerzaS()) {
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);

					System.out.println(TipoColor.ROJA + " gano batalla Norte");
					return true;
				}
			} else if (aux.getElemento().equals(elemento)) {

				if (card.getFuerzaN() > aux.getFuerzaS() + 1) {
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);

					System.out.println(TipoColor.ROJA + " gano batalla Norte");
					return true;
				}

			} else if (card.getElemento().equals(elemento)) {
				if (card.getFuerzaN() + 1 > aux.getFuerzaS()) {
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);

					System.out.println(TipoColor.ROJA + " gano batalla Norte");
					return true;
				}
			}
		}// azul
		return false;
	}

	// *******************************************************************************//
	/**
	 * Atiende la batalla de una carta Roja por el Sur
	 * 
	 * @param row
	 *            Fila
	 * @param colum
	 *            Columna
	 * @param card
	 *            Carta
	 * @return boolean
	 */
	// *******************************************************************************//
	private boolean southbattleR(int row, int colum, Card card) {
		Card aux = new Card();
		int rowAux = row + 1;
		aux = tablero[rowAux][colum];

		System.out.println("-------");
		System.out.println("Entro en Batalla Sur rojo:");
		System.out.println("Elemento tablero:" + getElemento());
		System.out.println("Elemento Carta recien Colocada:"
				+ card.getElemento());
		System.out.println("COLOR Carta recien Colocada:" + card.getColor());
		if (aux != null) {
			System.out.println("Elemento Carta RIVAL " + aux.getElemento());
		}
		System.out.println("-------");
		System.out.println("Sur  fila: " + rowAux + " columna:" + colum);
		if (aux != null && aux.getColor().equals(TipoColor.AZUL)) {
			System.out.println("1er if");
			if (card.getElemento().equals(elemento)
					&& aux.getElemento().equals(elemento)
					|| !card.getElemento().equals(elemento)
					&& !aux.getElemento().equals(elemento)) {
				System.out.println("Carta reciend colocada FuerzaSur: "
						+ card.getFuerzaS());
				System.out.println("Carta Rival FuerzaNorte :"
						+ aux.getFuerzaN());
				if (card.getFuerzaS() > aux.getFuerzaN()) {
					System.out.println("3er if");
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.ROJA + " gano batalla Sur");
					return true;
				}

			} else if (aux.getElemento().equals(elemento)) {
				System.out.println("Carta reciend colocada FuerzaSur: "
						+ card.getFuerzaS());
				System.out.println("Carta Rival FuerzaNorte +1:"
						+ aux.getFuerzaN());
				if (card.getFuerzaS() > aux.getFuerzaN() + 1) {
					System.out.println("5to if");
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.ROJA + " gano batalla Sur");
					return true;
				}

			} else if (card.getElemento().equals(elemento)) {
				System.out.println("Carta reciend colocada FuerzaSur: +1"
						+ card.getFuerzaS());
				System.out.println("Carta Rival FuerzaNorte :"
						+ aux.getFuerzaN());
				if (card.getFuerzaS() + 1 > aux.getFuerzaN()) {
					System.out.println("7mo if");
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), rowAux, colum, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.ROJA + " gano batalla Sur");
					return true;
				}

			}
		}// fin azul
		return false;
	}// fin del metodo privado battle south
		// *******************************************************************************//

	/**
	 * Atiende la batalla de una carta Roja por el Este
	 * 
	 * @param row
	 *            Fila
	 * @param colum
	 *            Columna
	 * @param card
	 *            Carta
	 * @return boolean
	 */
	// *******************************************************************************//
	private boolean eastbattleR(int row, int colum, Card card) {

		Card aux = new Card();
		int columAux = colum + 1;
		aux = tablero[row][columAux];
		System.out.println("Este   fila: " + row + " columna: " + columAux);
		// si la carta no es nula comparo las fuerza por el este card y Oeste de
		// aux
		if (aux != null && aux.getColor().equals(TipoColor.AZUL)) {
			// Si la carta recien colocada es mayor que la adyacente entonces
			// la cambio de color
			if (card.getElemento().equals(elemento)
					&& aux.getElemento().equals(elemento)
					|| !card.getElemento().equals(elemento)
					&& !aux.getElemento().equals(elemento)) {

				if (card.getFuerzaE() > aux.getFuerzaO()) {
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.ROJA + " gano batalla Este");
					return true;
				}
			} else if (aux.getElemento().equals(elemento)) {

				if (card.getFuerzaE() > aux.getFuerzaO() + 1) {
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.ROJA + " gano batalla Este");
					return true;
				}

			} else if (card.getElemento().equals(elemento)) {

				if (card.getFuerzaE() + 1 > aux.getFuerzaO()) {
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.ROJA + " gano batalla Este");
					return true;
				}
			}
		}// fin azul
		return false;
	}// fin del metodo privado battle East
		// *******************************************************************************//

	/**
	 * Atiende la batalla de una carta Roja por el Oeste Billy The Kid
	 * 
	 * @param row
	 *            Fila
	 * @param colum
	 *            Columna
	 * @param card
	 *            Carta
	 * @return boolean
	 */
	// *******************************************************************************//
	private boolean westbattleR(int row, int colum, Card card) {
		int columAux = colum - 1;
		Card aux = new Card();
		aux = tablero[row][columAux];
		System.out.println("Oeste fila: " + row + " columna: " + columAux);
		if (aux != null && aux.getColor().equals(TipoColor.AZUL)) {

			if (card.getElemento().equals(elemento)
					&& aux.getElemento().equals(elemento)
					|| !card.getElemento().equals(elemento)
					&& !aux.getElemento().equals(elemento)) {

				if (card.getFuerzaO() > aux.getFuerzaE()) {
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.ROJA + " gano batalla Oeste");
					return true;
				}
			} else if (aux.getElemento().equals(elemento)) {
				if (card.getFuerzaO() > aux.getFuerzaE() + 1) {
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.ROJA + " gano batalla Oeste");
					return true;
				}

			} else if (card.getElemento().equals(elemento)) {

				if (card.getFuerzaO() + 1 > aux.getFuerzaE()) {
					aux.setColor(TipoColor.ROJA);
					Command cartaComando = crearCartaCommand(aux);
					Command comando = new CartasVolteadaCommand(
							aux.getIdCarta(), row, columAux, TipoColor.ROJA,
							cartaComando);
					partida.enviarMsjAmbosJug(comando);
					System.out.println(TipoColor.ROJA + " gano batalla Oeste");
					return true;
				}
			}
		}// fin azul
		return false;
	}

	// *******************************************************************************//
	/**
	 * Este metodo se encarga de verificar los distintos combos que surgen al
	 * introducir una carta al tablero.
	 * 
	 * @param row
	 *            Fila
	 * @param colum
	 *            Columna
	 * @author andres & jesus
	 */
	// *******************************************************************************//
	private boolean verificarCombos(int row, int colum, String color) {
		Card carta = tablero[row][colum];
		boolean linea = true;
		boolean diagonalElemento = true;
		boolean retorno = false;
		int count = 0;

		// verifica la daginal de izquerda a derecha
		if ((row == 0 && colum == 0) || (row == 1 && colum == 1)
				|| (row == 2 && colum == 2) || (row == 3 && colum == 3)) {
			for (int i = 0; i < 4; i++) {
				Card aux = tablero[i][i];
				if (aux != null) {
					if ((aux.getElemento().equals(carta.getElemento()))
							&& (aux.getColor().equals(carta.getColor()))
							&& diagonalElemento) {
						count++;
						if ((count == 4) && color.equals("AZUL")) {
							setAzulPts(getAzulPts() + 7);
							azulNumeroDiagonales++;
							Command comando = new EleguirTableroCommand(
									elemento, TipoColor.AZUL, false);
							Command comando2 = new PuntosCommand(getAzulPts(),
									getRojoPts());
							partida.enviarMsjAmbosJug(comando2);
							partida.enviarMsjJugTurno(comando);
							retorno = true;
						}
						if (count == 4 && color.equals("ROJO")) {
							setRojoPts(getRojoPts() + 7);
							rojoNumeroDiagonales++;
							Command comando = new EleguirTableroCommand(
									elemento, TipoColor.ROJA, false);
							Command comando2 = new PuntosCommand(getAzulPts(),
									getRojoPts());
							partida.enviarMsjAmbosJug(comando2);
							partida.enviarMsjJugTurno(comando);
							retorno = true;

						}
					} else if ((aux.getColor().equals(carta.getColor()))) {
						count++;
						diagonalElemento = false;
						if (count == 4 && color.equals("AZUL")) {
							setAzulPts(getAzulPts() + 5);
							azulNumeroDiagonales++;
							Command comando = new EleguirTableroCommand(
									elemento, TipoColor.AZUL, false);
							Command comando2 = new PuntosCommand(getAzulPts(),
									getRojoPts());
							partida.enviarMsjAmbosJug(comando2);
							partida.enviarMsjJugTurno(comando);

							retorno = true;

						}
						if (count == 4 && color.equals("ROJO")) {
							setRojoPts(getRojoPts() + 5);
							rojoNumeroDiagonales++;
							Command comando = new EleguirTableroCommand(
									elemento, TipoColor.ROJA, false);
							Command comando2 = new PuntosCommand(getAzulPts(),
									getRojoPts());
							partida.enviarMsjAmbosJug(comando2);
							partida.enviarMsjJugTurno(comando);
							retorno = true;
						}
					} else {
						i = 4;
					}
				} else
					i = 4;
			}
		}// fin daginal de izquerda a derecha

		// verifica la diagonal de derecha a izquierda
		else if ((row == 0 && colum == 3) || (row == 1 && colum == 2)
				|| (row == 2 && colum == 1) || (row == 3 && colum == 0)) {
			int j = 3;
			for (int i = 0; i < 4; i++) {
				Card aux = tablero[i][j];
				if (aux != null) {
					if ((aux.getElemento().equals(carta.getElemento()))
							&& (aux.getColor().equals(carta.getColor()))
							&& diagonalElemento) {
						count++;
						if (count == 4 && color.equals("AZUL")) {
							setAzulPts(getAzulPts() + 7);
							azulNumeroDiagonales++;
							Command comando = new EleguirTableroCommand(
									elemento, TipoColor.AZUL, false);
							Command comando2 = new PuntosCommand(getAzulPts(),
									getRojoPts());
							partida.enviarMsjAmbosJug(comando2);
							partida.enviarMsjJugTurno(comando);
							retorno = true;
						}
						if (count == 4 && color.equals("ROJO")) {
							setRojoPts(getRojoPts() + 7);
							rojoNumeroDiagonales++;
							Command comando = new EleguirTableroCommand(
									elemento, TipoColor.ROJA, false);
							Command comando2 = new PuntosCommand(getAzulPts(),
									getRojoPts());
							partida.enviarMsjAmbosJug(comando2);
							partida.enviarMsjJugTurno(comando);
							retorno = true;
						}
					} else if ((aux.getColor().equals(carta.getColor()))) {
						count++;
						diagonalElemento = false;
						if (count == 4 && color.equals("AZUL")) {
							setAzulPts(getAzulPts() + 5);
							azulNumeroDiagonales++;
							Command comando = new EleguirTableroCommand(
									elemento, TipoColor.AZUL, false);
							Command comando2 = new PuntosCommand(getAzulPts(),
									getRojoPts());
							partida.enviarMsjAmbosJug(comando2);
							partida.enviarMsjJugTurno(comando);
							retorno = true;
						}
						if (count == 4 && color.equals("ROJO")) {
							setRojoPts(getRojoPts() + 5);
							rojoNumeroDiagonales++;
							Command comando = new EleguirTableroCommand(
									elemento, TipoColor.ROJA, false);
							Command comando2 = new PuntosCommand(getAzulPts(),
									getRojoPts());
							partida.enviarMsjAmbosJug(comando2);
							partida.enviarMsjJugTurno(comando);
							retorno = true;
						}
					} else {
						i = 4;
					}
					j--;
				} else
					i = 4;

			}

		}// fin de diagonal de derecha a izquierda
		count = 0;

		// valida linea verticales
		for (int i = 0; i < 4; i++) {
			Card aux = tablero[i][colum];
			if (aux != null) {
				if (carta.getElemento().equals(aux.getElemento())
						&& (aux.getColor().equals(carta.getColor())) && linea) {
					count++;
					if (count == 4 && color.equals("AZUL")) {
						setAzulPts(getAzulPts() + 5);
						azulNumeroLineas++;
						System.err
								.println("Columna " + Integer.toString(colum));

						Command comando = new EleguirTableroCommand(elemento,
								TipoColor.AZUL, false);
						Command comando2 = new PuntosCommand(getAzulPts(),
								getRojoPts());
						partida.enviarMsjAmbosJug(comando2);
						partida.enviarMsjJugTurno(comando);
						retorno = true;
					}
					if (count == 4 && color.equals("ROJO")) {
						System.err
								.println("Columna " + Integer.toString(colum));
						setRojoPts(getRojoPts() + 5);
						rojoNumeroLineas++;
						Command comando = new EleguirTableroCommand(elemento,
								TipoColor.ROJA, false);
						Command comando2 = new PuntosCommand(getAzulPts(),
								getRojoPts());
						partida.enviarMsjAmbosJug(comando2);
						partida.enviarMsjJugTurno(comando);
						retorno = true;
					}
				} else if ((aux.getColor().equals(carta.getColor()))) {
					count++;
					linea = false;
					if (count == 4 && color.equals("AZUL")) {
						setAzulPts(getAzulPts() + 3);
						azulNumeroLineas++;
						System.err
								.println("Columna " + Integer.toString(colum));

						Command comando = new EleguirTableroCommand(elemento,
								TipoColor.AZUL, false);
						Command comando2 = new PuntosCommand(getAzulPts(),
								getRojoPts());
						partida.enviarMsjAmbosJug(comando2);
						partida.enviarMsjJugTurno(comando);
						retorno = true;
					}
					if (count == 4 && color.equals("ROJO")) {
						setRojoPts(getRojoPts() + 3);
						rojoNumeroLineas++;
						System.err
								.println("Columna " + Integer.toString(colum));

						Command comando = new EleguirTableroCommand(elemento,
								TipoColor.ROJA, false);
						Command comando2 = new PuntosCommand(getAzulPts(),
								getRojoPts());
						partida.enviarMsjAmbosJug(comando2);
						partida.enviarMsjJugTurno(comando);
						retorno = true;
					}
				} else
					i = 4;
			}
		}
		linea = true;
		count = 0;
		linea = true;
		//valida lineas horizontales
		for (int i = 0; i < 4; i++) {
			Card aux = tablero[row][i];
			if (aux != null) {
				if (carta.getElemento().equals(aux.getElemento())
						&& carta.getColor().equals(aux.getColor()) && linea) {
					count++;
					if (count == 4 && color.equals("AZUL")) {
						setAzulPts(getAzulPts() + 5);
						azulNumeroLineas++;

						System.err.println("Fila " + Integer.toString(row));

						Command comando = new EleguirTableroCommand(elemento,
								TipoColor.AZUL, false);
						Command comando2 = new PuntosCommand(getAzulPts(),
								getRojoPts());
						partida.enviarMsjAmbosJug(comando2);
						partida.enviarMsjJugTurno(comando);
						retorno = true;
					}
					if (count == 4 && color.equals("ROJO")) {
						System.err.println("Fila " + Integer.toString(row));
						setRojoPts(getRojoPts() + 5);
						rojoNumeroLineas++;

						Command comando = new EleguirTableroCommand(elemento,
								TipoColor.ROJA, false);
						Command comando2 = new PuntosCommand(getAzulPts(),
								getRojoPts());
						partida.enviarMsjAmbosJug(comando2);
						partida.enviarMsjJugTurno(comando);
						retorno = true;
					}

				} else if ((aux.getColor().equals(carta.getColor()))) {
					count++;
					linea = false;
					if (count == 4 && color.equals("AZUL")) {
						setAzulPts(getAzulPts() + 3);
						azulNumeroLineas++;

						System.err.println("Fila " + Integer.toString(row));

						Command comando = new EleguirTableroCommand(elemento,
								TipoColor.AZUL, false);
						Command comando2 = new PuntosCommand(getAzulPts(),
								getRojoPts());
						partida.enviarMsjAmbosJug(comando2);
						partida.enviarMsjJugTurno(comando);
						retorno = true;
					}
					if (count == 4 && color.equals("ROJO")) {
						setRojoPts(getRojoPts() + 3);
						rojoNumeroLineas++;
						Command comando = new EleguirTableroCommand(elemento,
								TipoColor.ROJA, false);
						Command comando2 = new PuntosCommand(getAzulPts(),
								getRojoPts());
						partida.enviarMsjAmbosJug(comando2);
						partida.enviarMsjJugTurno(comando);
						retorno = true;
					}
				}

				else
					i = 4;
			} else
				i = 4;
		}
		return retorno;
	}

	// --------------------------------------------------------------------------------
	public void actualizarTablero() {
		PosicionBean posicion;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (tablero[i][j] != null) {
					if (tablero[i][j].getElemento().equals(this.elemento)) {
						posicion = new PosicionBean(0, i, j, true);
					} else {
						posicion = new PosicionBean(0, i, j, false);
					}
				} else {
					posicion = new PosicionBean(0, i, j, false);
				}
				listaPosicion.add(posicion);
			}
		}

	}// fin del metodo

	public void enviarActualizacoinTablero() {
		Command comandoActTable = new ActualizarTablero(listaPosicion);
		partida.enviarMsjAmbosJug(comandoActTable);
		limpiarLista();
	}

	public void limpiarLista() {
		listaPosicion.clear();
	}

	// --------------------------------------------------------------------------------
	private void pintarTablero() {

		System.out
				.println("-----------------------TABLERO---------------------------\n");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (tablero[i][j] != null) {
					if (tablero[i][j].getColor().equals(TipoColor.ROJA)) {

						System.out.println("carta en la fila "
								+ Integer.toString(i + 1) + " columna "
								+ Integer.toString(j + 1) + " fuerzas "
								+ Integer.toString(tablero[i][j].getFuerzaN())
								+ " "
								+ Integer.toString(tablero[i][j].getFuerzaS())
								+ " "
								+ Integer.toString(tablero[i][j].getFuerzaE())
								+ " "
								+ Integer.toString(tablero[i][j].getFuerzaO())
								+ " Color Rojo");
					}
					if (tablero[i][j].getColor().equals(TipoColor.AZUL)) {
						System.out.println("carta en la fila "
								+ Integer.toString(i + 1) + " columna "
								+ Integer.toString(j + 1) + " fuerzas "
								+ Integer.toString(tablero[i][j].getFuerzaN())
								+ " "
								+ Integer.toString(tablero[i][j].getFuerzaS())
								+ " "
								+ Integer.toString(tablero[i][j].getFuerzaE())
								+ " "
								+ Integer.toString(tablero[i][j].getFuerzaO())
								+ " Color Azul");
					}

				}
			}
		}
		System.out.println("Elemento del Tablero: " + getElemento());
		System.out
				.println("-----------------------FIN DEL TABLERO---------------------------\n");

	}// fin de pintar tablero
		// *******************************************************************************//
		// *******************************************************************************//
		// ** FIN **//
		// *******************************************************************************//

	private void randomElemento(int r) {
		Command comando;

		switch (r) {
		case 0:
			this.elemento = Elemento.agua;
			break;
		case 1:
			this.elemento = Elemento.fuego;
			break;
		case 2:
			this.elemento = Elemento.aire;
			break;
		case 3:
			this.elemento = Elemento.tierra;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	public void setCambioTablero(int elemento) {
		switch (elemento) {
		case 0:
			this.elemento = Elemento.agua;
			break;
		case 1:
			this.elemento = Elemento.fuego;
			break;
		case 2:
			this.elemento = Elemento.aire;
			break;
		case 3:
			this.elemento = Elemento.tierra;
			break;
		}

	}

	public Elemento getElemento() {
		return elemento;
	}

	public void setAzulPts(int azulPts) {
		this.azulPts = azulPts;
	}

	public int getAzulPts() {
		return azulPts;
	}

	public void setRojoPts(int rojoPts) {
		this.rojoPts = rojoPts;
	}

	public int getRojoPts() {
		return rojoPts;
	}

	public boolean cambio() {
		return cambio;
	}

	public CartaCommand crearCartaCommand(Card carta) {
		CartaCommand ret = new CartaCommand(
				carta.getIdCarta(), //
				carta.getFuerzaN(), carta.getFuerzaS(), carta.getFuerzaE(),
				carta.getFuerzaO(), //
				carta.getElemento().ordinal());

		return ret;

	}

}// fin fe la calse Tablero