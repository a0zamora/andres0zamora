package union;

/**
 * Clase que se encarga de transformar el texto que se le envia a la maquina de
 * estados y tambien se encarga de traducir la respuesta a la clase partida
 * 
 * @author Rafa
 * 
 */
public class Comunication {

	//private static String rojoJuega;

	private String cartaVolteada1;

	private String cartaVolteada2;

	private String cartaVolteada3;

	private String cartaVolteada4;

	private boolean cambioTablero;

	private int puntosAzul;
	
	private int puntosRojo;
	
	private int puntos;

	private String txt;

	// *******************************************************************************//
	public Comunication(String txt) {
		cambioTablero = false;
		this.txt = txt;
		cartaVolteada1 = "Nada";
		cartaVolteada2 = "Nada";
		cartaVolteada3 = "Nada";
		cartaVolteada4 = "Nada";
		puntos = 0;
	};

	// *******************************************************************************//
	public void clear() {
		this.txt = "";
	}

	// *******************************************************************************//
//	public void setRojoJuega(String str) {
//		this.rojoJuega = str;
//	}
//
//	// *******************************************************************************//
//	public static String getRojoJuega() {
//		return rojoJuega;
//	}

	// *******************************************************************************//
	public void setText(String txt) {
		this.txt = txt;
	}

	// *******************************************************************************//
	public String getEvent() {

		if (txt.startsWith("0")) {
			return "inicio";
		}

		if (txt.startsWith("2")) {
			return "jugada_a";
		}
		if (txt.startsWith("1")) {
			return "jugada_r";
		}

		return "default";
	}

	// *******************************************************************************//
	public int getRow() {
		int row = Integer.parseInt(txt.substring(2, 3));
		return row;
	}

	// *******************************************************************************//
	public int getColum() {
		int colum = Integer.parseInt(txt.substring(4, 5));
		return colum;
	}

	// *******************************************************************************//
	public int getFuerN() {
		int fuerN = Integer.parseInt(txt.substring(6, 7));
		return fuerN;
	}

	// *******************************************************************************//
	public int getFuerS() {
		int fuerS = Integer.parseInt(txt.substring(8, 9));
		return fuerS;
	}

	// *******************************************************************************//
	public int getFuerE() {
		int fuerE = Integer.parseInt(txt.substring(10, 11));
		return fuerE;
	}

	// *******************************************************************************//
	public int getFuerO() {
		int fuerO = Integer.parseInt(txt.substring(12, 13));
		return fuerO;
	}

	// *******************************************************************************//
	public int getElemento() {
		int elemento = Integer.parseInt(txt.substring(14, 15));
		return elemento;
	}

	// *******************************************************************************//
	public void setVoltearCarta1(String cartaVolteada) {
		cartaVolteada1 = cartaVolteada;
	}

	// *******************************************************************************//
	public String getVotearCarta1() {
		return cartaVolteada1;
	}

	// *******************************************************************************//
	public void setVoltearCarta2(String cartaVolteada) {
		cartaVolteada2 = cartaVolteada;
	}

	// *******************************************************************************//
	public String getVotearCarta2() {
		return cartaVolteada2;
	}

	// *******************************************************************************//
	public void setVoltearCarta3(String cartaVolteada) {
		cartaVolteada3 = cartaVolteada;
	}

	// *******************************************************************************//
	public String getVotearCarta3() {
		return cartaVolteada3;
	}

	// *******************************************************************************//
	public void setVoltearCarta4(String cartaVolteada) {
		cartaVolteada4 = cartaVolteada;
	}

	// *******************************************************************************//
	public String getVotearCarta4() {
		return cartaVolteada4;
	}

	// *******************************************************************************//
	public void setCambio(boolean cambioTablero) {
		this.cambioTablero = cambioTablero;
	}

	// *******************************************************************************//
	public boolean getCambio() {
		return cambioTablero;
	}

	// *******************************************************************************//
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	// *******************************************************************************//
	public String getPuntos() {
		return Integer.toString(puntos);
	}
	//*******************************************************************************//
	public int getPuntosAzul() {
		return puntosAzul;
	}
	//*******************************************************************************//
	public void setPuntosAzul(int perdedor) {
		this.puntosAzul = perdedor;
	}
	//*******************************************************************************//
	public int getPuntosRojo() {
		return puntosRojo;
	}
	//*******************************************************************************//
	public void setPuntosRojo(int ganador) {
		this.puntosRojo = ganador;
	}
	//*******************************************************************************//
}// fin de la clase comunicasion