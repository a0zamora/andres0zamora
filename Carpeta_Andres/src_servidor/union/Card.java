package union;

import constants.Elemento;
import constants.TipoColor;

/**
 * Clase para el objeto carta
 * @author Rafa
 *
 */
public class Card {

	
	//*********************************************************************//
	//**							ATRIBUTOS							 **//
	//*********************************************************************//
	/** Fuerzas de las cartas */
	private int fuerzaN, fuerzaS, fuerzaE, fuerzaO;
	private int idCarta;
	
	protected TipoColor color;
	private Elemento element;
	//*********************************************************************//
	/** Constructor por defecto del objeto Carta */
	//*********************************************************************//
	public Card() {
//		fuerzaE = 0;
//		fuerzaN = 0;
//		fuerzaO = 0;
//		fuerzaS = 0;
//		element = null;
//		color = null;
//		element=elemento.agua;
	}
	//*********************************************************************//
	/** Constructor parametrico solo con las fuerzas de la carta y elemento*/
	//*********************************************************************//
	public Card(int fuerN, int fuerS, int fuerE, int fuerO, int element) {
		this.fuerzaE = fuerE;
		this.fuerzaN = fuerN;
		this.fuerzaS = fuerS;
		this.fuerzaO = fuerO;
		switch (element) {
		case 0:
			System.out.println("agua");
			this.element = Elemento.agua;
			break;
		case 1:
			System.out.println("fuego");
			this.element = Elemento.fuego;
			break;
		case 2:
			System.out.println("aire");
			this.element = Elemento.aire;
			break;
		case 3:
			System.out.println("tierra");
			this.element = Elemento.tierra;
			break;
			default:
				throw  new IllegalArgumentException();
		}

	}
	
	
	//*********************************************************************//
	/** Constructor parametrico  con idCarta, las fuerzas de la carta y el elemento*/
	//*********************************************************************//
	
	public Card(int idCarta, int fuerN, int fuerS, int fuerE, int fuerO, int element) {
		
		this.idCarta = idCarta;
		this.fuerzaE = fuerE;
		this.fuerzaN = fuerN;
		this.fuerzaS = fuerS;
		this.fuerzaO = fuerO;
		switch (element) {
		case 0:
			System.out.println("agua");
			this.element = Elemento.agua;
			break;
		case 1:
			System.out.println("fuego");
			this.element = Elemento.fuego;
			break;
		case 2:
			System.out.println("aire");
			this.element = Elemento.aire;
			break;
		case 3:
			System.out.println("tierra");
			this.element = Elemento.tierra;
			break;
			default:
				throw  new IllegalArgumentException();
		}

	}
	
	
	//*********************************************************************//
	/** Constructor parametrico con las fuerzas de las cartas y con el color del jugador */
	//*********************************************************************//
	public Card(int fuerN, int fuerS, int fuerE, int fuerO, TipoColor color) {
		this.fuerzaE = fuerE;
		this.fuerzaN = fuerN;
		this.fuerzaS = fuerS;
		this.fuerzaO = fuerO;
		this.color = color;

	}
	
	public Card(int idCarta, int fuerN, int fuerS, int fuerE, int fuerO, TipoColor color) {
		this.idCarta = idCarta;
		this.fuerzaE = fuerE;
		this.fuerzaN = fuerN;
		this.fuerzaS = fuerS;
		this.fuerzaO = fuerO;
		this.color = color;

	}
	
	public Card(int fuerN, int fuerS, int fuerE, int fuerO, TipoColor color,Elemento element) {
		this.fuerzaE = fuerE;
		this.fuerzaN = fuerN;
		this.fuerzaS = fuerS;
		this.fuerzaO = fuerO;
		this.color = color;
		this.element=element;

	}

	
	
	//*********************************************************************//
	/** Constructor por copia */
	//*********************************************************************//
	public Card(TipoColor c) {
		if (c == TipoColor.AZUL)
			this.color = TipoColor.AZUL;
		else {

			this.color = TipoColor.ROJA;
		}

	}
	
	
	//*********************************************************************//
	//**							INTERFAZ							 **//
	//*********************************************************************//
	
	public int getIdCarta() {
		return idCarta;
	}
	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}

	
	public void setFuerzaE(int f) {
		this.fuerzaE = f;
	}
	//*********************************************************************//
	public void setFuerzaN(int f) {
		this.fuerzaN = f;
	}
	//*********************************************************************//
	public void setFuerzaO(int f) {
		this.fuerzaO = f;
	}
	//*********************************************************************//
	public void setFuerzaS(int f) {
		this.fuerzaS = f;
	}
	//*********************************************************************//
	public void setColor(TipoColor color) {
		this.color = color;
	}
	//*********************************************************************//
	public int getFuerzaE() {
		return fuerzaE;
	}
	//*********************************************************************//
	public int getFuerzaN() {
		return fuerzaN;
	}
	//*********************************************************************//
	public int getFuerzaO() {
		return fuerzaO;
	}
	//*********************************************************************//
	public int getFuerzaS() {
		return fuerzaS;
	}
	//*********************************************************************//
	public TipoColor getColor() {
		return color;
	}
	//*********************************************************************//
	public Elemento getElemento() {
		return element;
	}
	
//	public boolean operator == (final Card carta){
//		return this.
//		
//	}
	//*********************************************************************//
}// fin de la clase carta