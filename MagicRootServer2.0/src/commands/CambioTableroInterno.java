package commands;

import java.util.regex.Pattern;

import constants.Elemento;
import constants.TipoColor;
import constants.TipoDeEvento;


public class CambioTableroInterno implements Command {

	public static final String CADENA_COMANDO = "CambioTableroInterno";
	
	public static final TipoDeEvento TIPO_DE_EVENTO = TipoDeEvento.cambioTablero;
	
	private int id=0;
	private int elemento;
	
	public CambioTableroInterno(String str) {
		
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 3) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}

		try { 
			id = Integer.parseInt( partes[1]);
			elemento = Integer.parseInt( partes[2]);
			 
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}
	// --------------------------------------------------------------------------------

	
	/**
	 * Constructor parametrico 
	 * @author Rafael
	 * @param elemento 
	 * 			enum Elemento, 
	 * @param color
	 * 			enum TipoColor el color de el jugador que cambio el tablero
	 * @param ramdon 
	 * 			booleano vale true si se envia desde el servidor al cliente en en ramdon select del elemento,
	 * 			vale false si es el cambio o cambios de tablero por una linea o diagonal
	 */

	
	public CambioTableroInterno( Elemento elemento ,TipoColor color) {
		
		
		switch (elemento) {
		
		case agua:
			System.out.println("agua");
			this.elemento = 0;
			break;
		case fuego:
			System.out.println("fuego");
			this.elemento = 1;
			break;
		case aire:
			System.out.println("aire");
			this.elemento = 2;
			break;
		case tierra:
			System.out.println("tierra");
			this.elemento = 3;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Constructor parametrico 
	 * @author Rafael
	 * @param elemento 
	 * 			de Tipo enum Elemento, 
	 * @param ramdon 
	 * 			booleano vale true si se envia desde el servidor al cliente en en ramdon select del elemento,
	 * 			vale false si es el cambio o cambios de tablero por una linea o diagonal
	 */	

	public CambioTableroInterno( Elemento elemento ) {

		switch (elemento) {
		
		case agua:
			System.out.println("agua");
			this.elemento = 0;
			break;
		case fuego:
			System.out.println("fuego");
			this.elemento = 1;
			break;
		case aire:
			System.out.println("aire");
			this.elemento = 2;
			break;
		case tierra:
			System.out.println("tierra");
			this.elemento = 3;
			break;
		default:
			throw new IllegalArgumentException();
		}
}
	
	/**
	 *returna un valor int de 0 a 3 correspondiente a los elementos de los tableros 
	 * @author rafael
	 * @return 0 = agua, 1 = fuego, 2 = aire , 3 = tierra
	 */
	public int getElemento(){
		return elemento;
	}
	
	@Override
	public TipoDeEvento getTipoDeEvento() {
		return TIPO_DE_EVENTO;
	}

	@Override
	public int getId() {

		return id;
	}
	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " "
				+ //
				id+ " " + elemento ; 
	}

	
}
